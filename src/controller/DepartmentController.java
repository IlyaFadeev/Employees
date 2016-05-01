package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.DEPARTMENTS;
import services.DepartmentService;

import java.util.List;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
@Controller
public class DepartmentController {
    private DepartmentService departmentService;

    @RequestMapping(name="/departments", method= RequestMethod.GET)
    public String getDepartments(Model model)
    {
        departmentService=new DepartmentService();
        List<DEPARTMENTS> depts=departmentService.getAll();
        model.addAttribute("depts", depts);
        return "departments";
    }

    @RequestMapping(name="/removeDept", method=RequestMethod.GET)
    public String removeDept(Model model, @RequestParam(name="deptno", required = true) int deptno)
    {
        departmentService=new DepartmentService();
        departmentService.removeDept(departmentService.getDeptByNo(deptno));
        return "removeDept";
    }
}
