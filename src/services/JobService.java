package services;

import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Directory;
import pojo.JOB;
import services.interfaces.DirectoryService;

import java.util.List;

/**
 * Created by Fadeev on 4/18/2016.
 */
public class JobService extends SessionService implements DirectoryService {


    public JobService() {
        super(JOB.class);
    }


    public List<JOB> getAll() {
        Session session = getSession();
        Query query = session.createQuery("FROM JOB");
        return query.list();
    }

    public void add(Directory job) {
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(job);
        session.getTransaction().commit();
    }

    public Directory get(String name) {
        Session session = getSession();
        session.beginTransaction();
        JOB loc = (JOB) session.get(JOB.class, name);
        session.getTransaction().commit();
        return loc;
    }
}
