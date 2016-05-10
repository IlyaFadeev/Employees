package services;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import pojo.EMPLOYEES;
import pojo.TIMEOFF;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

/**
 * Created by Fadeev on 4/17/2016.
 */
public class EmployeesService extends SessionService {

    public EmployeesService() {
        super(EMPLOYEES.class);
    }


    public List<EMPLOYEES> getAll() {
        Session session = getSession();
        String tableName = "EMPLOYEES";
        SQLQuery query = session.createSQLQuery("SELECT * FROM EMPLOYEES ORDER BY EMPNO");
        query.addEntity(EMPLOYEES.class);
        List<EMPLOYEES> employees = query.list();
        close();
        return employees;
    }

    public EMPLOYEES getEmp(Integer id) {
        Session session = getSession();

        session.beginTransaction();
        EMPLOYEES employee = (EMPLOYEES) session.get(EMPLOYEES.class, id);
        session.getTransaction().commit();
        close();
        return employee;
    }

    public void addEmp(EMPLOYEES employee, TIMEOFF timeoff) {
        Session session = getSession();

        session.beginTransaction();
        session.saveOrUpdate(employee);
        //Integer result = session.createSQLQuery("SELECT EMPNO FROM EMPLOYEES WHERE EMPNO = (SELECT MAX(EMPNO) FROM EMPLOYEES)").executeUpdate();
        Query sqlQuery = session.createSQLQuery("INSERT INTO TIMEOFF (EMPNO, START_DATE, END_DATE, TYPENO) VALUES ((SELECT MAX(EMPNO) FROM EMPLOYEES), :start, :end, :type)");
        sqlQuery.setParameter("start", timeoff.getStartdate());
        sqlQuery.setParameter("end", timeoff.getEnddate());
        sqlQuery.setParameter("type", timeoff.getType());
        sqlQuery.executeUpdate();
        session.getTransaction().commit();
        close();

    }


    public void addEmp(EMPLOYEES employee) {
        Session session = getSession();

        session.beginTransaction();
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
        close();

    }

    public void updateEmp(EMPLOYEES employee) {
        Session session = getSession();

        session.beginTransaction();
        session.update(employee);
        session.getTransaction().commit();
        close();
    }

    public void removeEmp(EMPLOYEES employee) {
        Session session = getSession();

        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        close();
    }


    public List<EMPLOYEES> getAllMgrForEmp(Integer empno) {
        Session session = getSession();


        SQLQuery query = session.createSQLQuery("SELECT EMPNO, FIRST_NAME, LAST_NAME, JOB, MGR, HIREDATE, SAL, DEPTNO\n" +
                "FROM EMPLOYEES\n" +
                "START WITH EMPNO = " + empno + "\n" +
                "CONNECT BY  PRIOR MGR = EMPNO");

        query.addEntity(EMPLOYEES.class);

        List<EMPLOYEES> employees = query.list();
        close();
        return employees;
    }

    public List<EMPLOYEES> getAllSubEmpForEmp(Integer empno) {
        Session session = getSession();

        SQLQuery query = session.createSQLQuery("SELECT EMPNO, FIRST_NAME, LAST_NAME, JOB, MGR, HIREDATE, SAL, DEPTNO\n" +
                "FROM EMPLOYEES\n" +
                "START WITH EMPNO = " + empno + "\n" +
                "CONNECT BY  PRIOR EMPNO = MGR");

        query.addEntity(EMPLOYEES.class);

        List<EMPLOYEES> employees = query.list();
        close();
        return employees;
    }


    public List<EMPLOYEES> search(boolean[] filters, String name, String surname, Date hDate, String job, String mgr, String sal) {
        Session session = getSession();
        List<EMPLOYEES> employees = null;
        session.beginTransaction();
        Criteria criteria = session.createCriteria(EMPLOYEES.class);
        if (filters[0] == true) {
            criteria.add(Restrictions.eq("firstName", name));
        }

        if (filters[1] == true) {
            criteria.add(Restrictions.eq("secondName", surname));
        }

        if (filters[2] == true) {
            criteria.add(Restrictions.eq("hireDate", hDate));
        }

        if (filters[3] == true) {
            criteria.add(Restrictions.eq("job", job));
        }

        if (filters[4] == true) {
            criteria.add(Restrictions.eq("mgr", Integer.parseInt(mgr)));
        }

        if (filters[5] == true) {
            criteria.add(Restrictions.eq("sal", Integer.parseInt(sal)));
        }
        employees = criteria.list();
        session.getTransaction().commit();

        close();
        return employees;
    }

}
