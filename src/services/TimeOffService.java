package services;


import org.hibernate.*;

import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import pojo.TIMEOFF;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class TimeOffService extends SessionService {
    private Logger logger = Logger.getLogger(TimeOffService.class.toString());

    public TimeOffService() {
        super(TIMEOFF.class);
    }


    public List<TIMEOFF> getAll() {
        Session session = getSession();
        String tableName = "TIMEOFF";
        SQLQuery query = session.createSQLQuery("SELECT * FROM " + tableName);
        query.addEntity(TIMEOFF.class);
        try {
            List<TIMEOFF> timeoffs = query.list();
            return timeoffs;
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.info("Get all timeoffs failed!");
        } finally {
            close();
        }
        return null;
    }

    public void addTimeOff(TIMEOFF timeoff) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(timeoff);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

    }

    public void updateTimeOff(TIMEOFF timeoff) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.update(timeoff);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

    }

    public void removeTimeOff(TIMEOFF timeoff) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.delete(timeoff);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

    }

    public TIMEOFF get(Integer empNo) {
        Session session = getSession();

        try {
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM TIMEOFF WHERE EMPNO = " + empNo);
            sqlQuery.addEntity(TIMEOFF.class);
            TIMEOFF timeoff = (TIMEOFF) sqlQuery.list().get(0);
            session.getTransaction().commit();

            return timeoff;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }
        return null;
    }
}
