package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import services.interfaces.Service;

import java.util.Locale;

/**
 * Created by Fadeev on 5/8/2016.
 */
public class SessionService implements Service {
    Configuration configuration;
    private SessionFactory factory;
    private Session session;
    private ServiceRegistryBuilder serviceRegistryBuilder;
    private ServiceRegistry serviceRegistry;

    public SessionService(Class aClass) {
        this.configuration = new Configuration().addAnnotatedClass(aClass);
        configuration.configure();
        serviceRegistryBuilder = new ServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        this.serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
        this.factory = configuration.buildSessionFactory(serviceRegistry);
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        session = factory.openSession();
        return session;
    }

    public void close(){
        this.session.close();
    }
}
