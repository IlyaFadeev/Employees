package services;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import pojo.DEPARTMENTS;
import pojo.EMPLOYEES;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class DepartmentService {
    Configuration configuration;
    private SessionFactory factory;
    private Session session;
    private ServiceRegistryBuilder serviceRegistryBuilder;
    private ServiceRegistry serviceRegistry;

    public DepartmentService(){
        this.configuration = new Configuration().addAnnotatedClass(DEPARTMENTS.class);
        configuration.configure();
        serviceRegistryBuilder = new ServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        this.serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
        this.factory = configuration.buildSessionFactory(serviceRegistry);
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        if (session!=null) session.close();
        session=factory.openSession();
        return session;
    }

    public List<DEPARTMENTS> getAll(){
        Session session = getSession();
        String tableName = "DEPARTMENTS";
        Query query = session.createQuery("FROM " + tableName);
        return query.list();
    }

    public DEPARTMENTS getDeptByNo(Integer deptno)
    {
        Session session=getSession();
        session.beginTransaction();
        DEPARTMENTS dept= (DEPARTMENTS) session.get(DEPARTMENTS.class, deptno);
        session.getTransaction().commit();
        return dept;
    }

    public List<EMPLOYEES> getEmpByDept(Integer deptno)
    {
        Session session=new EmployeesService().getSession();
        session.beginTransaction();
        Criteria crit=session.createCriteria(EMPLOYEES.class);
        crit.add(Restrictions.eq("deptNo",deptno));
        List<EMPLOYEES> res=crit.list();
        session.getTransaction().commit();
        return res;
    }

    public void updateDept(DEPARTMENTS department){
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(department);
        session.getTransaction().commit();
    }

    public void removeDept(DEPARTMENTS department){
        Session session = getSession();
        session.beginTransaction();
        session.delete(department);
        session.getTransaction().commit();
    }
}
