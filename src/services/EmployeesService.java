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
import java.util.logging.Logger;

/**
 * Created by Fadeev on 4/17/2016.
 */
public class EmployeesService extends SessionService {

    private Logger logger = Logger.getLogger(EmployeesService.class.getName());

    public EmployeesService() {
        super(EMPLOYEES.class);
    }


    public List<EMPLOYEES> getAll() {
        Session session = getSession();
        String tableName = "EMPLOYEES";
        SQLQuery query = session.createSQLQuery("SELECT * FROM EMPLOYEES ORDER BY FIRST_NAME");
        query.addEntity(EMPLOYEES.class);
        try {
            List<EMPLOYEES> employees = query.list();
            return employees;
        } catch (HibernateException e) {
            logger.info("Transaction rollback!");
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }

    public EMPLOYEES getEmp(Integer id) {
        Session session = getSession();

        try {
            session.beginTransaction();
            EMPLOYEES employee = (EMPLOYEES) session.get(EMPLOYEES.class, id);
            session.getTransaction().commit();
            return employee;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

        return null;

    }

    public void addEmp(EMPLOYEES employee, TIMEOFF timeoff) {
        Session session = getSession();

        try {
            session.beginTransaction();
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            close();
            logger.info("Transaction rollback!");
        }

        session = getSession();
        try {
            session.beginTransaction();
            Query sqlQuery = session.createSQLQuery("INSERT INTO TIMEOFF (ID, EMPNO, START_DATE, END_DATE, TYPENO) VALUES (NULL , :empno, :start, :end, :type)");
            sqlQuery.setParameter("empno", employee.getEmpNo());
            sqlQuery.setParameter("start", timeoff.getStartdate());
            sqlQuery.setParameter("end", timeoff.getEnddate());
            sqlQuery.setParameter("type", timeoff.getType());
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

    }


    /*public void addEmp(EMPLOYEES employee) {
        Session session = getSession();

        session.beginTransaction();
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
        close();

    }*/

    public void updateEmp(EMPLOYEES employee) {
        Session session = getSession();

        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }


    }

    public void removeEmp(EMPLOYEES employee) {
        Session session = getSession();

        try {
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

    }


    public List<EMPLOYEES> getAllMgrForEmp(Integer empno) {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("SELECT EMPNO, FIRST_NAME, LAST_NAME, JOB, MGR, HIREDATE, SAL, DEPTNO\n" +
                "FROM EMPLOYEES\n" +
                "START WITH EMPNO = " + empno + "\n" +
                "CONNECT BY NOCYCLE  PRIOR MGR = EMPNO");

        query.addEntity(EMPLOYEES.class);

        try {
            List<EMPLOYEES> employees = query.list();
            return employees;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }

    public List<EMPLOYEES> getAllSubEmpForEmp(Integer empno) {
        Session session = getSession();

        SQLQuery query = session.createSQLQuery("SELECT EMPNO, FIRST_NAME, LAST_NAME, JOB, MGR, HIREDATE, SAL, DEPTNO\n" +
                "FROM EMPLOYEES\n" +
                "START WITH EMPNO = " + empno + "\n" +
                "CONNECT BY NOCYCLE  PRIOR EMPNO = MGR");

        query.addEntity(EMPLOYEES.class);

        try {
            List<EMPLOYEES> employees = query.list();
            return employees;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }


    public List<EMPLOYEES> search(boolean[] filters, String name, String surname, Date hDate, String job, String mgr, String sal) {
        Session session = getSession();
        List<EMPLOYEES> employees = null;
        try {

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
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

        return employees;
    }


    public EMPLOYEES getEmpByName(String name) {
        Session session = getSession();
        String tableName = "EMPLOYEES";
        SQLQuery query = session.createSQLQuery("SELECT * FROM EMPLOYEES WHERE FIRST_NAME = " + name);
        query.addEntity(EMPLOYEES.class);
        try {
            EMPLOYEES employee = (EMPLOYEES)query.list().get(0);
            return employee;
        } catch (HibernateException e) {
            logger.info("Transaction rollback!");
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }

}
