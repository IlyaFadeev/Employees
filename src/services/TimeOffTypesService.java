package services;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Directory;
import pojo.TIMEOFFTYPES;
import services.interfaces.DirectoryService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class TimeOffTypesService extends SessionService implements DirectoryService {
    private Logger logger = Logger.getLogger(TimeOffTypesService.class.toString());

    public TimeOffTypesService() {
        super(TIMEOFFTYPES.class);
    }


    public List<TIMEOFFTYPES> getAll() {
        Session session = getSession();
        String tableName = "TIMEOFFTYPES";
        Query query = session.createQuery("FROM " + tableName);
        try {
            List<TIMEOFFTYPES> timeofftypes = query.list();
            return timeofftypes;
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.info("Get all time off types failed!");
        } finally {
            close();
        }
        return null;
    }

    public void add(Directory type) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(type);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

    }

    public Directory get(String name) {
        Session session = getSession();
        try {
            session.beginTransaction();
            TIMEOFFTYPES loc = (TIMEOFFTYPES) session.get(TIMEOFFTYPES.class, name);
            session.getTransaction().commit();
            return loc;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }
        return null;
    }
}
