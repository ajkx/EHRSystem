package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmSpeciality;
import com.victory.ehrsystem.service.hrm.impl.HrmSpecialityService;
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
@RequestMapping(value = "/speciality")
public class HrmSpecialityController {

    @Autowired
    private HrmSpecialityService specialityService;

    @RequiresPermissions(value = "jobCall:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmSpeciality> temp = specialityService.findAll(HrmSpeciality.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmSpeciality speciality : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", speciality.getName());
            tempmap.put("详述", speciality.getDescription());
            map.put(speciality.getId(),tempmap);
        }
        model.addAttribute("topic","专业管理");
        model.addAttribute("simplename","专业");
        model.addAttribute("url","speciality");
        model.addAttribute("map",map);
        model.addAttribute("editlist", CollectionUtil.getObjectFields(HrmSpeciality.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HrmSpeciality speciality,Model model) {
        specialityService.save(speciality);
        model.addAttribute("list",specialityService.findAll(HrmSpeciality.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:update")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public @ResponseBody HrmSpeciality showupdate(@PathVariable("id") int id) {
        HrmSpeciality speciality = specialityService.findOne(HrmSpeciality.class, id);
        return speciality;
    }

    @RequiresPermissions(value = "jobCall:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HrmSpeciality speciality,Model model) {
        specialityService.update(HrmSpeciality.class, speciality);
        model.addAttribute("list",specialityService.findAll(HrmSpeciality.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> delete(@PathVariable("id") int id) {
        specialityService.delete(HrmSpeciality.class,id);
        Map<String, String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }
}
