package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmJobActivities;
import com.victory.ehrsystem.service.hrm.impl.HrmJobActivitiesService;
import com.victory.ehrsystem.util.CollectionUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ajkx_Du
 * @create 2016-11-21 14:52
 */
@Controller
@RequestMapping(value = "/jobactivities")
public class HrmJobActivitiesController {

    @Autowired
    private HrmJobActivitiesService jobActivitiesService;

    @RequiresPermissions(value = "jobGroup:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmJobActivities> temp = jobActivitiesService.findAll(HrmJobActivities.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmJobActivities jobactivity : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", jobactivity.getName());
            tempmap.put("详述", jobactivity.getDescription());
            tempmap.put("职务类型", jobactivity.getGroupid().toString());
            map.put(jobactivity.getId(),tempmap);
        }
        model.addAttribute("topic","职务管理");
        model.addAttribute("simplename","职务");
        model.addAttribute("url","jobactivities");
        model.addAttribute("map",map);
        model.addAttribute("editlist", CollectionUtil.getObjectFields(HrmJobActivities.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobGroup:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HrmJobActivities jobactivity,Model model) {
        jobActivitiesService.save(jobactivity);
        model.addAttribute("list",jobActivitiesService.findAll(HrmJobActivities.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobGroup:update")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public @ResponseBody HrmJobActivities showupdate(@PathVariable("id") int id) {
        HrmJobActivities jobactivity = jobActivitiesService.findOne(HrmJobActivities.class, id);
        return jobactivity;
    }

    @RequiresPermissions(value = "jobGroup:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HrmJobActivities jobactivity,Model model) {
        jobActivitiesService.update(HrmJobActivities.class, jobactivity);
        model.addAttribute("list",jobActivitiesService.findAll(HrmJobActivities.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobGroup:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> delete(@PathVariable("id") int id) {
        jobActivitiesService.delete(HrmJobActivities.class,id);
        Map<String, String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }
}
