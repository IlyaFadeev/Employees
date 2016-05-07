package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import pojo.Directory;
import pojo.EMPLOYEES;
import pojo.LOCATE;
import pojo.TIMEOFFTYPES;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class TimeOffTypesService implements DirectoryService {
    Configuration configuration;
    private SessionFactory factory;
    private Session session;
    private ServiceRegistryBuilder serviceRegistryBuilder;
    private ServiceRegistry serviceRegistry;

    public TimeOffTypesService() {
        this.configuration = new Configuration().addAnnotatedClass(TIMEOFFTYPES.class);
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
