package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmJobCall;
import com.victory.ehrsystem.service.hrm.impl.HrmJobCallService;
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
@RequestMapping(value = "/jobcall")
public class HrmJobCallController {

    @Autowired
    private HrmJobCallService jobCallService;

    @RequiresPermissions(value = "jobCall:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmJobCall> temp = jobCallService.findAll(HrmJobCall.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmJobCall jobcall : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", jobcall.getName());
            tempmap.put("详述", jobcall.getDescription());
            map.put(jobcall.getId(),tempmap);
        }
        model.addAttribute("topic","职称管理");
        model.addAttribute("simplename","职称");
        model.addAttribute("url","jobcall");
        model.addAttribute("map",map);
        model.addAttribute("editlist", CollectionUtil.getObjectFields(HrmJobCall.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HrmJobCall jobcall,Model model) {
        jobCallService.save(jobcall);
        model.addAttribute("list",jobCallService.findAll(HrmJobCall.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:update")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public @ResponseBody HrmJobCall showupdate(@PathVariable("id") int id) {
        HrmJobCall jobcall = jobCallService.findOne(HrmJobCall.class, id);
        return jobcall;
    }

    @RequiresPermissions(value = "jobCall:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HrmJobCall jobcall,Model model) {
        jobCallService.update(HrmJobCall.class, jobcall);
        model.addAttribute("list",jobCallService.findAll(HrmJobCall.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> delete(@PathVariable("id") int id) {
        jobCallService.delete(HrmJobCall.class,id);
        Map<String, String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }
}
