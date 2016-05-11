package services;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Directory;
import pojo.LOCATE;
import services.interfaces.DirectoryService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fadeev on 4/18/2016.
 */
public class LocateService extends SessionService implements DirectoryService {

    private Logger logger = Logger.getLogger(LocateService.class.toString());

    public LocateService() {
        super(LOCATE.class);
    }


    public List<LOCATE> getAll() {
        Session session = getSession();
        String tableName = "LOCATE";

        Query query = session.createQuery("FROM " + tableName);
        try {
            List<LOCATE> locates = query.list();
            return locates;
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.info("Get all failed!");
        } finally {
            close();
        }
        return null;
    }

    public void add(Directory locate) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(locate);
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
            LOCATE loc = (LOCATE) session.get(LOCATE.class, name);
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
