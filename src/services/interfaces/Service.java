package services.interfaces;

import org.hibernate.Session;


/**
 * Created by Fadeev on 5/8/2016.
 */
public interface Service {
    Session getSession();
    void close();
}
