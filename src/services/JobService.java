package services;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Directory;
import pojo.JOB;
import services.interfaces.DirectoryService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fadeev on 4/18/2016.
 */
public class JobService extends SessionService implements DirectoryService {

    private Logger logger = Logger.getLogger(JobService.class.toString());

    public JobService() {
        super(JOB.class);
    }


    public List<JOB> getAll() {
        Session session = getSession();
        String tableName = "JOB";
        Query query = session.createQuery("FROM " + tableName);
        try {
            List<JOB> jobs = query.list();
            return jobs;
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.info(e.toString());
        } finally {
            close();
        }
        return null;
    }

    public void add(Directory job) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(job);
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
            JOB loc = (JOB) session.get(JOB.class, name);
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
