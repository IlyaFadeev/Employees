package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import pojo.EMPLOYEES;
import pojo.LOCATE;
import pojo.TIMEOFF;
import pojo.TIMEOFFTYPES;

import java.util.List;
import java.util.Locale;

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
        return query.list();
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
}
