package services;

import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Directory;
import pojo.TIMEOFFTYPES;
import services.interfaces.DirectoryService;

import java.util.List;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class TimeOffTypesService extends SessionService implements DirectoryService {

    public TimeOffTypesService() {
        super(TIMEOFFTYPES.class);
    }


    public List<TIMEOFFTYPES> getAll() {
        Session session = getSession();
        String tableName = "TIMEOFFTYPES";
        Query query = session.createQuery("FROM " + tableName);
        return query.list();
    }

    public void add(Directory type) {
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(type);
        session.getTransaction().commit();
    }

    public Directory get(String name) {
        Session session = getSession();
        session.beginTransaction();
        TIMEOFFTYPES loc = (TIMEOFFTYPES) session.get(TIMEOFFTYPES.class, name);
        session.getTransaction().commit();
        return loc;
    }
}
