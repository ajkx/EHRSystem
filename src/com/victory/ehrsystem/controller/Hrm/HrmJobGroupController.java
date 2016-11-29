package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmJobGroups;
import com.victory.ehrsystem.service.hrm.impl.HrmJobGroupsService;
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
@RequestMapping(value = "/jobgroup")
public class HrmJobGroupController {

    @Autowired
    private HrmJobGroupsService jobGroupsService;

    @RequiresPermissions(value = "jobGroup:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmJobGroups> temp = jobGroupsService.findAll(HrmJobGroups.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmJobGroups jobgroup : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", jobgroup.getName());
            tempmap.put("详述", jobgroup.getDescription());
            map.put(jobgroup.getId(),tempmap);
        }
        model.addAttribute("topic","职务类别");
        model.addAttribute("simplename","类别");
        model.addAttribute("url","jobgroup");
        model.addAttribute("map",map);
        model.addAttribute("editlist", CollectionUtil.getObjectFields(HrmJobGroups.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobGroup:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HrmJobGroups jobgroup,Model model) {
        jobGroupsService.save(jobgroup);
        model.addAttribute("list",jobGroupsService.findAll(HrmJobGroups.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobGroup:update")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public @ResponseBody HrmJobGroups showupdate(@PathVariable("id") int id) {
        HrmJobGroups jobgroup = jobGroupsService.findOne(HrmJobGroups.class, id);
        return jobgroup;
    }

    @RequiresPermissions(value = "jobGroup:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HrmJobGroups jobgroup,Model model) {
        jobGroupsService.update(HrmJobGroups.class, jobgroup);
        model.addAttribute("list",jobGroupsService.findAll(HrmJobGroups.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobGroup:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> delete(@PathVariable("id") int id) {
        jobGroupsService.delete(HrmJobGroups.class,id);
        Map<String, String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }
}
