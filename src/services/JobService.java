package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import pojo.EMPLOYEES;
import pojo.JOB;

import java.util.List;
import java.util.Locale;

/**
 * Created by Fadeev on 4/18/2016.
 */
public class JobService {
    AnnotationConfiguration aconf;
    Configuration conf;
    private SessionFactory factory;

    private Session session;

    public JobService(){
        this.aconf = new AnnotationConfiguration().addAnnotatedClass(JOB.class);
        this.conf = aconf.configure();
        this.factory = conf.buildSessionFactory();
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        return session;
    }


    public List<JOB> getAll(){
        Session session = getSession();

        Query query = session.createQuery("FROM JOB");

        return query.list();
    }
}
