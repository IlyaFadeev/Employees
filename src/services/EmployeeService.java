package services;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.ui.Model;
import pojo.EMPLOYEES;

import java.util.List;
import java.util.Locale;

/**
 * Created by Fadeev on 4/17/2016.
 */
public class EmployeeService {

    AnnotationConfiguration aconf;
    Configuration conf;
    private SessionFactory factory;

    private Session session;

    public EmployeeService(){
        this.aconf = new AnnotationConfiguration().addAnnotatedClass(EMPLOYEES.class);
        this.conf = aconf.configure();
        this.factory = conf.buildSessionFactory();
        this.session = factory.openSession();
        Locale.setDefault(Locale.US);
    }

    public Session getSession() {
        return session;
    }

    public List<EMPLOYEES> getAll(){
        Session session = getSession();
        String tableName = "EMPLOYEES";
        Query query = session.createQuery("FROM " + tableName);

        return query.list();
    }

    public EMPLOYEES getEmp(Integer id){
        Session session = getSession();

        session.beginTransaction();
        EMPLOYEES employee = (EMPLOYEES)session.get(EMPLOYEES.class, id);
        session.getTransaction().commit();

        return employee;
    }

    public void  addEmp(EMPLOYEES employee){
        Session session = getSession();

        session.beginTransaction();
        session.saveOrUpdate(employee);
        session.getTransaction().commit();

    }

    public void updateEmp(EMPLOYEES employee){
        Session session = getSession();

        session.beginTransaction();
        session.update(employee);
        session.getTransaction().commit();
    }

    public void removeEmp(EMPLOYEES employee){
        Session session = getSession();

        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
    }


    public List<EMPLOYEES> getAllMgrForEmp(Integer empno){
        Session session = getSession();


        SQLQuery query =  session.createSQLQuery("SELECT EMPNO, FIRST_NAME, LAST_NAME, JOB, MGR, HIREDATE, SAL, DEPTNO\n" +
                "FROM EMPLOYEES\n" +
                "START WITH EMPNO = " + empno + "\n"+
                "CONNECT BY  PRIOR MGR = EMPNO");

        query.addEntity(EMPLOYEES.class);

        return query.list();
    }

    public List<EMPLOYEES> getAllSubEmpForEmp(Integer empno){
        Session session = getSession();

        SQLQuery query =  session.createSQLQuery("SELECT EMPNO, FIRST_NAME, LAST_NAME, JOB, MGR, HIREDATE, SAL, DEPTNO\n" +
                "FROM EMPLOYEES\n" +
                "START WITH EMPNO = " + empno + "\n" +
                "CONNECT BY  PRIOR EMPNO = MGR");

        query.addEntity(EMPLOYEES.class);
        return query.list();
    }

}
