package services;


import org.hibernate.*;

import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import pojo.TIMEOFF;

import java.util.List;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class TimeOffService extends SessionService {

    public TimeOffService() {
       super(TIMEOFF.class);
    }


    public List<TIMEOFF> getAll() {
        Session session = getSession();
        String tableName = "TIMEOFF";
        Query query = session.createQuery("FROM " + tableName);
        List<TIMEOFF> timeoffs = query.list();
        close();
        return timeoffs;
    }

    public void addTimeOff(TIMEOFF timeoff) {
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(timeoff);
        session.getTransaction().commit();
    }

    public void updateTimeOff(TIMEOFF timeoff) {
        Session session = getSession();
        session.beginTransaction();
        session.update(timeoff);
        session.getTransaction().commit();
    }

    public void removeTimeOff(TIMEOFF timeoff) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(timeoff);
        session.getTransaction().commit();
    }

    public TIMEOFF get(Integer empNo){
        Session session = getSession();

        session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT EMPNO, START_DATE, END_DATE, TYPENO FROM TIMEOFF WHERE EMPNO = " + empNo);
        sqlQuery.addEntity(TIMEOFF.class);
        TIMEOFF timeoff = (TIMEOFF)sqlQuery.list().get(0);
        session.getTransaction().commit();

        return timeoff;
    }
}
