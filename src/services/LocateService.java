package services;

import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Directory;
import pojo.LOCATE;
import services.interfaces.DirectoryService;

import java.util.List;

/**
 * Created by Fadeev on 4/18/2016.
 */
public class LocateService extends SessionService implements DirectoryService {


    public LocateService() {
        super(LOCATE.class);
    }


    public List<LOCATE> getAll() {
        Session session = getSession();
        String tableName = "LOCATE";

        Query query = session.createQuery("FROM " + tableName);

        return query.list();
    }

    public void add(Directory locate) {
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(locate);
        session.getTransaction().commit();
    }

    public Directory get(String name) {
        Session session = getSession();
        session.beginTransaction();
        LOCATE loc = (LOCATE) session.get(LOCATE.class, name);
        session.getTransaction().commit();
        return loc;
    }
}
