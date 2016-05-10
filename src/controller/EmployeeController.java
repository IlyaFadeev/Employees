package controller;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Period;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.*;
import services.*;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
    private DepartmentService deptService;
    private TimeOffTypesService timeOffTypesService;
    private TimeOffService timeOffService;

    private final static String DEFAULT_EMP_NO = "-1";

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getPersons(Model model, @RequestParam(value = "empno", required = true, defaultValue = DEFAULT_EMP_NO) Integer empno) {
        logger.info("Creating time off service...");
        timeOffService = new TimeOffService();
        logger.info("Creating employee service...");
        employeesService = new EmployeesService();
        logger.info("Employee service created.");

        logger.info("Getting all employees...");

        HashMap<EMPLOYEES, Long> employeestimeoffHashMap = new HashMap<EMPLOYEES, Long>();

        if (empno == -1) empno = employeesService.getAll().get(0).getEmpNo();

        EMPLOYEES employee = employeesService.getEmp(empno);

        logger.info("Getting all managers for current employee...");
        List<EMPLOYEES> mgrs = employeesService.getAllMgrForEmp(empno);


        logger.info("Getting all sub employees for current employee...");
        List<EMPLOYEES> subEmployees = employeesService.getAllSubEmpForEmp(empno);

        logger.info("Getting all times off for employees...");
        List<TIMEOFF> timeoffs = timeOffService.getAll();

        List<EMPLOYEES> employees = employeesService.getAll();

        long hours = 0;

        DateTime start = new DateTime(timeOffService.get(employee.getEmpNo()).getStartdate().getTime());
        DateTime end = new DateTime(timeOffService.get(employee.getEmpNo()).getEnddate().getTime());


        hours = Hours.hoursBetween(start, end).getHours();


        logger.info("Adding attributes...");
        model.addAttribute("employee", employee);
        model.addAttribute("mgrs", mgrs);
        model.addAttribute("subEmp", subEmployees);
        model.addAttribute("timeoff", hours);

        return "employees";
    }

    @RequestMapping(value = "/addemp", method = RequestMethod.GET)
    public String addEmp(Model model) {
        logger.info("Return add page.");
        employeesService = new EmployeesService();
        jobService = new JobService();
        locateService = new LocateService();
        deptService = new DepartmentService();
        timeOffTypesService = new TimeOffTypesService();
        List<TIMEOFFTYPES> timeofftypes = timeOffTypesService.getAll();
        List<EMPLOYEES> employees = employeesService.getAll();
        List<JOB> jobs = jobService.getAll();
        List<LOCATE> locates = locateService.getAll();
        List<DEPARTMENTS> departmentses = deptService.getAll();
        model.addAttribute("empls", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("dept", departmentses);
        model.addAttribute("types", timeofftypes);
        return "addemp";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveEmp(Model model, @RequestParam(value = "empno", required = false, defaultValue = "null") Integer empno, @RequestParam(value = "first_name", required = true) String fName, @RequestParam(value = "last_name", required = true) String sName, @RequestParam(value = "job", required = true) String job, @RequestParam(value = "mgr", required = true) String mgr, @RequestParam(value = "hiredate", required = true) String hDate, @RequestParam(value = "salary", required = true) Integer sal, @RequestParam(value = "deptno", required = true) Integer deptNo, @RequestParam(value = "start", required = true) String start, @RequestParam(value = "end", required = true) String end, @RequestParam(value = "type", required = true) Integer type) {
        logger.info("Start saving...");
        EMPLOYEES employee = new EMPLOYEES();
        employeesService = new EmployeesService();
        timeOffService = new TimeOffService();
        Integer findedMgr = 0;

        List<EMPLOYEES> employees = employeesService.getAll();

        for (EMPLOYEES employee1 : employees) {
            if (employee1.getFirstName().equals(mgr)) {
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
        } else {
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


        logger.info("Creating time off...");
        java.util.Date startDate = null;
        java.util.Date endDate = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");

            try {
                startDate = formatter.parse(start);
                endDate = formatter.parse(end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (org.springframework.beans.ConversionNotSupportedException e) {
            logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        TIMEOFF timeoff = new TIMEOFF();
        timeoff.setEmpno(empno);
        timeoff.setStartdate(new Date(startDate.getTime()));
        timeoff.setEnddate(new Date(endDate.getTime()));
        timeoff.setType(type);

        employeesService.addEmp(employee, timeoff);


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
    public String remove(Model model, @RequestParam(value = "empno", required = true, defaultValue = "1450") Integer empno) {
        employeesService = new EmployeesService();
        EMPLOYEES employee = employeesService.getEmp(empno);
        employeesService.removeEmp(employee);
        model.addAttribute("name", employee.getFirstName());
        return "remove";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam(value = "empno", required = true, defaultValue = "1450") Integer empno) {
        employeesService = new EmployeesService();
        EMPLOYEES employee = employeesService.getEmp(empno);
        jobService = new JobService();
        locateService = new LocateService();
        deptService = new DepartmentService();
        List<EMPLOYEES> employees = employeesService.getAll();
        List<JOB> jobs = jobService.getAll();
        List<LOCATE> locates = locateService.getAll();
        List<DEPARTMENTS> depts = deptService.getAll();
        model.addAttribute("empls", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("locates", locates);
        model.addAttribute("employee", employee);
        model.addAttribute("depts", depts);
        return "update";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam(value = "name", required = false, defaultValue = "null") String name, @RequestParam(value = "surname", required = false, defaultValue = "null") String surname, @RequestParam(value = "hiredate", required = false, defaultValue = "null") String hDAte, @RequestParam(value = "job", required = false, defaultValue = "null") String job, @RequestParam(value = "mgr", required = false, defaultValue = "null") String mgr, @RequestParam(value = "sal", required = false, defaultValue = "null") String sal) {
        boolean[] filters = new boolean[6];
        employeesService = new EmployeesService();
        List<EMPLOYEES> employees = null;

        if (!name.equals("")) {
            filters[0] = true;
        } else {
            filters[0] = false;
        }

        if (!surname.equals("")) {
            filters[1] = true;
        } else {
            filters[1] = false;
        }

        if (!hDAte.equals("")) {
            filters[2] = true;
        } else {
            filters[2] = false;
        }

        if (!job.equals("")) {
            filters[3] = true;
        } else {
            filters[3] = false;
        }

        if (!mgr.equals("")) {
            filters[4] = true;
        } else {
            filters[4] = false;

        }

        if (!sal.equals("")) {
            filters[5] = true;
        } else {
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        employees = employeesService.search(filters, name, surname, date1, job, mgr, sal);

        model.addAttribute("employees", employees);
        return "search";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String searchWithFilters(Model model) {

        return "filters";
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String getDepartments(Model model) {
        deptService = new DepartmentService();
        List<DEPARTMENTS> depts = deptService.getAll();
        logger.info(depts.toString());
        model.addAttribute("depts", depts);
        return "departments";
    }

    @RequestMapping(value = "/employeesfull", method = RequestMethod.GET)
    public String getEmpsByDept(Model model, @RequestParam(value = "deptno", required = true, defaultValue = "null") String deptno) {
        deptService = new DepartmentService();
        logger.info("Getting emps.." + deptno);
        List<EMPLOYEES> emps = deptService.getEmpByDept(Integer.parseInt(deptno));
        logger.info(emps.toString());
        logger.info("Adding attributes");
        if (emps.isEmpty()) throw new NoResultException("There is no employees!");
        model.addAttribute("emps", emps);
        model.addAttribute("pagetitle", "Employees of dept.");
        return "employeesfull";
    }

    @RequestMapping(value = "/savedept", method = RequestMethod.GET)
    public String saveDept(Model model, @RequestParam(name = "deptno", required = false) String deptno, @RequestParam(name = "dname", required = true, defaultValue = "") String dname, @RequestParam(name = "location", required = true, defaultValue = "") String location, @RequestParam(name = "mgr", required = true, defaultValue = "") String manager) {
        DepartmentService departmentService = new DepartmentService();
        DEPARTMENTS dept;
        if (deptno != null && !deptno.equals("")) {
            Integer dno = Integer.parseInt(deptno);
            dept = departmentService.getDeptByNo(dno);
        } else {
            dept = new DEPARTMENTS();
            dept.setDeptno(null);
        }
        dept.setDname(dname);
        dept.setLocation(location);
        employeesService = new EmployeesService();
        boolean[] filters = {true, true, false, false, false, false};
        String[] parts = manager.split(" ");
        EMPLOYEES emp = employeesService.search(filters, parts[0], parts[1], null, null, null, null).get(0);
        dept.setManager(emp.getEmpNo());
        departmentService.updateDept(dept);
        List<DEPARTMENTS> depts = departmentService.getAll();
        model.addAttribute("depts", depts);
        return "redirect:departments";
    }

    @RequestMapping(value = "/updatedept", method = RequestMethod.GET)
    public String updateDept(Model model, @RequestParam(value = "deptno", required = true, defaultValue = "null") Integer deptno) {
        deptService = new DepartmentService();
        employeesService = new EmployeesService();
        locateService = new LocateService();
        DEPARTMENTS dept = new DEPARTMENTS();
        if (deptno != null) dept = deptService.getDeptByNo(deptno);
        List<LOCATE> locates = locateService.getAll();
        List<EMPLOYEES> emps = employeesService.getAll();
        model.addAttribute("emps", emps);
        model.addAttribute("locations", locates);
        model.addAttribute("dept", dept);
        return "updateDept";
    }

    @RequestMapping(value = "/adddept", method = RequestMethod.GET)
    public String addDept(Model model) {
        return updateDept(model, null);
    }

    @RequestMapping(value = "/directories", method = RequestMethod.GET)
    public String getAllDirectory(Model model) {
        jobService = new JobService();
        locateService = new LocateService();
        timeOffTypesService = new TimeOffTypesService();
        List<JOB> jobs = jobService.getAll();
        List<LOCATE> locates = locateService.getAll();
        List<TIMEOFFTYPES> timeofftypes = timeOffTypesService.getAll();
        model.addAttribute("jobs", jobs);
        model.addAttribute("locates", locates);
        model.addAttribute("timeofftypes", timeofftypes);
        return "directories";
    }

    @RequestMapping(value = "/updatedir", method = RequestMethod.GET)
    public String updateDirectory(Model model, @RequestParam(name = "type", required = true) String typeDir, @RequestParam(name = "dir", required = false) String dir) {
        model.addAttribute("type", typeDir);
        //model.addAttribute("dirname",dir);
        if (typeDir.equals("job")) {
            model.addAttribute("dirtitle", "Job");
            if (dir != null) {
                model.addAttribute("dirname", dir);
            } else model.addAttribute("dirname", "");
        } else if (typeDir.equals("loc")) {
            model.addAttribute("dirtitle", "Location");
            if (dir != null) {
                model.addAttribute("dirname", dir);
            } else model.addAttribute("dirname", "");
        } else if (typeDir.equals("timeoff")) {
            model.addAttribute("dirtitle", "Time Off");
            if (dir != null) {
                model.addAttribute("dirname", dir);
            } else model.addAttribute("dirname", "");
        }
        return "updatedir";
    }

    @RequestMapping(value = "/savedir", method = RequestMethod.GET)
    public String saveDir(Model model, @RequestParam(name = "type", required = true) String type, @RequestParam(name = "dir", required = true) String dir) {
        if (type.equals("job")) {
            jobService = new JobService();
            JOB job = new JOB();
            job.setJname(dir);
            jobService.add(job);
        } else if (type.equals("loc")) {
            locateService = new LocateService();
            LOCATE loc = new LOCATE();
            loc.setLname(dir);
            locateService.add(loc);
        } else if (type.equals("timeoff")) {
            timeOffTypesService = new TimeOffTypesService();
            TIMEOFFTYPES timeofftypes = new TIMEOFFTYPES();
            timeofftypes.setType(dir);
            timeOffTypesService.add(timeofftypes);
        }
        return "redirect:" + getAllDirectory(model);
    }

    @RequestMapping(value = "/getallemps", method = RequestMethod.GET)
    public String getAllEmployees(Model model) {
        employeesService = new EmployeesService();
        List<EMPLOYEES> emps = employeesService.getAll();
        model.addAttribute("emps", emps);
        model.addAttribute("pagetitle", "Employees");
        return "employeesfull";
    }

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public String mainPage(Model model) {
        return "mainPage";
    }

}
