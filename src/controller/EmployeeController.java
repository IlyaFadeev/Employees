package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.DEPARTMENTS;
import pojo.EMPLOYEES;
import pojo.JOB;
import pojo.LOCATE;
import services.DepartmentService;
import services.EmployeesService;
import services.JobService;
import services.LocateService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fadeev on 4/17/2016.
 */
@Controller
public class EmployeeController {
    private Logger logger = Logger.getLogger(EmployeeController.class.getName());
    private EmployeesService employeesService;
    private JobService jobService;
    private LocateService locateService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getPersons(Model model, @RequestParam(value="empno", required=true, defaultValue = "1450") Integer empno){
        logger.info("Creating employee service...");
        employeesService = new EmployeesService();
        logger.info("Employee service created.");
        logger.info("Getting all employees...");
        EMPLOYEES employee = employeesService.getEmp(empno);

        logger.info("Getting all managers for current employee...");
        List<EMPLOYEES> mgrs = employeesService.getAllMgrForEmp(empno);


        logger.info("Getting all sub employees for current employee...");
        List<EMPLOYEES> subEmployees = employeesService.getAllSubEmpForEmp(empno);


        logger.info("Adding attributes...");
        model.addAttribute("employee", employee);
        model.addAttribute("mgrs", mgrs);
        model.addAttribute("subEmp", subEmployees);

        return "employees";
    }

    @RequestMapping(value = "/addemp", method = RequestMethod.GET)
    public String addEmp(Model model){
        logger.info("Return add page.");
        employeesService = new EmployeesService();
        jobService = new JobService();
        locateService = new LocateService();
        List<EMPLOYEES> employees = employeesService.getAll();
        List<JOB> jobs = jobService.getAll();
        List<LOCATE> locates = locateService.getAll();
        model.addAttribute("empls", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("locates", locates);
        return "addemp";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveEmp(Model model, @RequestParam(value="empno", required=false, defaultValue = "null") Integer empno,  @RequestParam(value="first_name", required=true) String fName, @RequestParam(value="last_name", required=true) String sName, @RequestParam(value="job", required=true) String job, @RequestParam(value="mgr", required=true) String mgr, @RequestParam(value="hiredate", required=true) String hDate, @RequestParam(value="salary", required=true) Integer sal, @RequestParam(value="deptno", required=true) Integer deptNo){
        logger.info("Start saving...");
        EMPLOYEES employee = new EMPLOYEES();
        employeesService = new EmployeesService();
        Integer findedMgr = 0;

        List<EMPLOYEES> employees = employeesService.getAll();

        for (EMPLOYEES employee1 : employees) {
            if (employee1.getFirstName().equals(mgr)){
                findedMgr = employee1.getEmpNo();
                break;
            }
        }

        logger.info("Creating new employee...");
        if (empno == null) {
            employee.setDeptNo(null);
            employee.setFirstName(fName);
            employee.setSecondName(sName);
            employee.setJob(job);
            logger.info("Set date!");
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");
                java.util.Date date = null;
                try {
                    date = formatter.parse(hDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date1 = new Date(date.getTime());
                employee.setHireDate(date1);
            } catch (org.springframework.beans.ConversionNotSupportedException e) {
                logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }

            employee.setMgr(findedMgr);
            employee.setSal(sal);
            employee.setDeptNo(deptNo);
        }
        else
        {
            employee = employeesService.getEmp(empno);
            employee.setDeptNo(null);
            employee.setFirstName(fName);
            employee.setSecondName(sName);
            employee.setJob(job);
            logger.info("Set date!");
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");
                java.util.Date date = null;
                try {
                    date = formatter.parse(hDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date1 = new Date(date.getTime());
                employee.setHireDate(date1);
            } catch (org.springframework.beans.ConversionNotSupportedException e) {
                logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }

            employee.setMgr(findedMgr);
            employee.setSal(sal);
            employee.setDeptNo(deptNo);
        }
        employeesService.addEmp(employee);


        logger.info("Creating employee service...");
        employeesService = new EmployeesService();
        logger.info("Employee service created.");
        logger.info("Getting all employees...");
        employees = employeesService.getAll();
        logger.info("Adding attribute...");
        model.addAttribute("employees", employees);

        return "redirect:employees";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam(value="empno", required=true, defaultValue = "1450") Integer empno){
        employeesService = new EmployeesService();
        EMPLOYEES employee = employeesService.getEmp(empno);
        employeesService.removeEmp(employee);
        model.addAttribute("name", employee.getFirstName());
        return "remove";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam(value="empno", required=true, defaultValue = "1450") Integer empno){
        employeesService = new EmployeesService();
        EMPLOYEES employee = employeesService.getEmp(empno);
        jobService = new JobService();
        locateService = new LocateService();
        List<EMPLOYEES> employees = employeesService.getAll();
        List<JOB> jobs = jobService.getAll();
        List<LOCATE> locates = locateService.getAll();
        model.addAttribute("empls", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("locates", locates);
        model.addAttribute("employee", employee);

        return "update";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam(value="name", required=false, defaultValue = "null") String name, @RequestParam(value="surname", required=false, defaultValue = "null") String surname, @RequestParam(value="hiredate", required=false, defaultValue = "null") String hDAte, @RequestParam(value="job", required=false, defaultValue = "null") String job, @RequestParam(value="mgr", required=false, defaultValue = "null") String mgr, @RequestParam(value="sal", required=false, defaultValue = "null") String sal){
        boolean[] filters = new boolean[6];
        employeesService = new EmployeesService();
        List<EMPLOYEES> employees = null;

        if (!name.equals("")){
            filters[0] = true;
        }
        else {
            filters[0] = false;
        }

        if (!surname.equals("")){
            filters[1] = true;
        }
        else {
            filters[1] = false;
        }

        if (!hDAte.equals("")){
            filters[2] = true;
        }
        else {
            filters[2] = false;
        }

        if (!job.equals("")){
            filters[3] = true;
        }
        else {
            filters[3] = false;
        }

        if (!mgr.equals("")){
            filters[4] = true;
        }
        else {
            filters[4] = false;

        }

        if (!sal .equals("")){
            filters[5] = true;
        }
        else {
            filters[5] = false;
        }

        Date date1 = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");
            java.util.Date date = null;
            try {
                date = formatter.parse(hDAte);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            date1 = new Date(date.getTime());
        } catch (org.springframework.beans.ConversionNotSupportedException e) {
            logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        employees = employeesService.search(filters, name, surname, date1, job, mgr, sal);

        model.addAttribute("employees", employees);
        return "search";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String searchWithFilters(Model model){

        return "filters";
    }


    private DepartmentService departmentService;

    @RequestMapping(name="/departments", method= RequestMethod.GET)
    public String getDepartments(Model model)
    {
        departmentService=new DepartmentService();
        List<DEPARTMENTS> depts=departmentService.getAll();
        model.addAttribute("depts", depts);
        return "departments";
    }

}
