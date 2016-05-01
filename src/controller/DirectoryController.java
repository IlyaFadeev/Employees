package controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.JOB;
import services.DirectoryService;
import services.JobService;
import services.LocateService;
import services.TimeOffTypesService;

import java.util.List;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public class DirectoryController {
    private DirectoryService directoryService;

    @RequestMapping(name="/jobs", method = RequestMethod.GET)
    public String getJobs(Model model)
    {
        directoryService=new JobService();
        List jobs=directoryService.getAll();
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @RequestMapping(name="/locations", method = RequestMethod.GET)
    public String getLocations(Model model)
    {
        directoryService=new LocateService();
        List jobs=directoryService.getAll();
        model.addAttribute("locations", jobs);
        return "locations";
    }

    @RequestMapping(name="/types", method = RequestMethod.GET)
    public String getTimeOffTypes(Model model)
    {
        directoryService=new TimeOffTypesService();
        List jobs=directoryService.getAll();
        model.addAttribute("types", jobs);
        return "types";
    }
}
