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
import java.util.logging.Logger;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class DepartmentService extends SessionService {

    Logger logger = Logger.getLogger(DepartmentService.class.getName());

    public DepartmentService() {
        super(DEPARTMENTS.class);
    }


    public List<DEPARTMENTS> getAll() {
        Session session = getSession();
        String tableName = "DEPARTMENTS";
        Query query = session.createQuery("FROM " + tableName);
        try {
            List<DEPARTMENTS> departmentses = query.list();
            return departmentses;
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.info("Get all exception!");
        } finally {
            close();
        }

        return null;
    }

    public DEPARTMENTS getDeptByNo(Integer deptno) {
        Session session = getSession();
        try {
            session.beginTransaction();
            DEPARTMENTS dept = (DEPARTMENTS) session.get(DEPARTMENTS.class, deptno);
            session.getTransaction().commit();
            return dept;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

        return null;
    }

    public List<EMPLOYEES> getEmpByDept(Integer deptno) {
        Session session = new EmployeesService().getSession();
        try {
            session.beginTransaction();
            Criteria crit = session.createCriteria(EMPLOYEES.class);
            crit.add(Restrictions.eq("deptNo", deptno));
            List<EMPLOYEES> res = crit.list();
            session.getTransaction().commit();
            return res;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

        return null;
    }

    public void updateDept(DEPARTMENTS department) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(department);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }
    }

    public void removeDept(DEPARTMENTS department) {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.info("Transaction rollback!");
        } finally {
            close();
        }

    }
}
