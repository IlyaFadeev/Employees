package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import pojo.Directory;
import pojo.LOCATE;
import pojo.TIMEOFFTYPES;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class TimeOffTypesService implements DirectoryService{
    AnnotationConfiguration aconf;
    Configuration conf;
    private SessionFactory factory;

    private Session session;

    public TimeOffTypesService(){
        this.aconf = new AnnotationConfiguration().addAnnotatedClass(TIMEOFFTYPES.class);
        this.conf = aconf.configure();
        this.factory = conf.buildSessionFactory();
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        return session;
    }

    public List<TIMEOFFTYPES> getAll(){
        Session session = getSession();
        String tableName = "TIMEOFFTYPES";
        Query query = session.createQuery("FROM " + tableName);
        return query.list();
    }

    public void add(Directory type)
    {
        Session session=getSession();
        session.beginTransaction();
        session.saveOrUpdate(type);
        session.getTransaction().commit();
    }

    public Directory get(String name)
    {
        Session session=getSession();
        session.beginTransaction();
        TIMEOFFTYPES loc= (TIMEOFFTYPES) session.get(TIMEOFFTYPES.class,name);
        session.getTransaction().commit();
        return loc;
    }
}
