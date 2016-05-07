package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.cglib.core.Local;
import pojo.Directory;
import pojo.EMPLOYEES;
import pojo.JOB;
import pojo.LOCATE;

import java.util.List;
import java.util.Locale;

/**
 * Created by Fadeev on 4/18/2016.
 */
public class LocateService implements DirectoryService {
    Configuration configuration;
    private SessionFactory factory;
    private Session session;
    private ServiceRegistryBuilder serviceRegistryBuilder;
    private ServiceRegistry serviceRegistry;

    public LocateService() {
        this.configuration = new Configuration().addAnnotatedClass(LOCATE.class);
        configuration.configure();
        serviceRegistryBuilder = new ServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        this.serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
        this.factory = configuration.buildSessionFactory(serviceRegistry);
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        return session;
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
