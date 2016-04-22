package controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.EMPLOYEES;
import pojo.JOB;
import pojo.LOCATE;
import services.EmployeeService;
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
    private EmployeeService employeeService;
    private JobService jobService;
    private LocateService locateService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getPersons(Model model, @RequestParam(value="empno", required=true, defaultValue = "1450") Integer empno){
        logger.info("Creating employee service...");
        employeeService = new EmployeeService();
        logger.info("Employee service created.");
        logger.info("Getting all employees...");
        EMPLOYEES employee = employeeService.getEmp(empno);

        logger.info("Getting all managers for current employee...");
        List<EMPLOYEES> mgrs = employeeService.getAllMgrForEmp(empno);


        logger.info("Getting all sub employees for current employee...");
        List<EMPLOYEES> subEmployees = employeeService.getAllSubEmpForEmp(empno);


        logger.info("Adding attributes...");
        model.addAttribute("employee", employee);
        model.addAttribute("mgrs", mgrs);
        model.addAttribute("subEmp", subEmployees);

        return "employees";
    }

    @RequestMapping(value = "/addemp", method = RequestMethod.GET)
    public String addEmp(Model model){
        logger.info("Return add page.");
        employeeService = new EmployeeService();
        jobService = new JobService();
        locateService = new LocateService();
        List<EMPLOYEES> employees = employeeService.getAll();
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
        employeeService = new EmployeeService();
        Integer findedMgr = 0;

        List<EMPLOYEES> employees = employeeService.getAll();

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
            employee = employeeService.getEmp(empno);
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
        employeeService.addEmp(employee);


        logger.info("Creating employee service...");
        employeeService = new EmployeeService();
        logger.info("Employee service created.");
        logger.info("Getting all employees...");
        employees = employeeService.getAll();
        logger.info("Adding attribute...");
        model.addAttribute("employees", employees);

        return "redirect:employees";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam(value="empno", required=true, defaultValue = "1450") Integer empno){
        employeeService = new EmployeeService();
        EMPLOYEES employee = employeeService.getEmp(empno);
        employeeService.removeEmp(employee);
        model.addAttribute("name", employee.getFirstName());
        return "remove";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam(value="empno", required=true, defaultValue = "1450") Integer empno){
        employeeService = new EmployeeService();
        EMPLOYEES employee = employeeService.getEmp(empno);
        jobService = new JobService();
        locateService = new LocateService();
        List<EMPLOYEES> employees = employeeService.getAll();
        List<JOB> jobs = jobService.getAll();
        List<LOCATE> locates = locateService.getAll();
        model.addAttribute("empls", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("locates", locates);
        model.addAttribute("employee", employee);

        return "update";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam(value="sVal", required=true) Integer empno){

        return getPersons(model, empno);
    }



}
