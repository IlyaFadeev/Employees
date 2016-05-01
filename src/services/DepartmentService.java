package services;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import pojo.DEPARTMENTS;
import pojo.EMPLOYEES;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class DepartmentService {
    AnnotationConfiguration aconf;
    Configuration conf;
    private SessionFactory factory;

    private Session session;

    public DepartmentService(){
        this.aconf = new AnnotationConfiguration().addAnnotatedClass(DEPARTMENTS.class);
        this.conf = aconf.configure();
        this.factory = conf.buildSessionFactory();
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        return session;
    }

    public List<DEPARTMENTS> getAll(){
        Session session = getSession();
        String tableName = "DEPARTMENTS";
        Query query = session.createQuery("FROM " + tableName);
        return query.list();
    }

    public DEPARTMENTS getDeptByNo(int deptno)
    {
        Session session=getSession();
        session.beginTransaction();
        DEPARTMENTS dept= (DEPARTMENTS) session.get(DEPARTMENTS.class, deptno);
        session.getTransaction().commit();
        return dept;
    }

    public List<EMPLOYEES> getEmpByDept(int deptno)
    {
        Session session=getSession();
        SQLQuery query=session.createSQLQuery("SELECT * FROM EMPLOYEES WHERE EMPLOYEES.DEPTNO=DEPARTMENTS.DEPTNO");
        query.addEntity(EMPLOYEES.class);
        return query.list();
    }

    public void addDept(DEPARTMENTS departments){
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(departments);
        session.getTransaction().commit();
    }

    public void updateDept(DEPARTMENTS department){
        Session session = getSession();
        session.beginTransaction();
        session.update(department);
        session.getTransaction().commit();
    }

    public void removeDept(DEPARTMENTS department){
        Session session = getSession();
        session.beginTransaction();
        session.delete(department);
        session.getTransaction().commit();
    }
}
