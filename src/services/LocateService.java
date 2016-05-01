package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import pojo.Directory;
import pojo.JOB;
import pojo.LOCATE;

import java.util.List;
import java.util.Locale;

/**
 * Created by Fadeev on 4/18/2016.
 */
public class LocateService implements DirectoryService{
    AnnotationConfiguration aconf;
    Configuration conf;
    private SessionFactory factory;

    private Session session;

    public LocateService(){
        this.aconf = new AnnotationConfiguration().addAnnotatedClass(LOCATE.class);
        this.conf = aconf.configure();
        this.factory = conf.buildSessionFactory();
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        return session;
    }


    public List<LOCATE> getAll(){
        Session session = getSession();
        String tableName = "LOCATE";

        Query query = session.createQuery("FROM " + tableName);

        return query.list();
    }

    public void add(Directory locate)
    {
        Session session=getSession();
        session.beginTransaction();
        session.saveOrUpdate(locate);
        session.getTransaction().commit();
    }
}
