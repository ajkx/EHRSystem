package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmLocation;
import com.victory.ehrsystem.service.hrm.impl.HrmLocationService;
import com.victory.ehrsystem.util.CollectionUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ajkx_Du
 * @create 2016-11-21 14:52
 */
@Controller
@RequestMapping(value = "/location")
public class HrmLocationController {

    @Autowired
    private HrmLocationService locationService;

    @RequiresPermissions(value = "location:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){
        String header = request.getHeader("X-PJAX");
        System.out.println(header);
        List<HrmLocation> temp = locationService.findAll(HrmLocation.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmLocation location : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", location.getName());
            tempmap.put("地点", location.getAddress());
            tempmap.put("所在城市", location.getCity());
            map.put(location.getId(),tempmap);
        }
        model.addAttribute("topic","办公地点");
        model.addAttribute("simplename","地点");
        model.addAttribute("url","location");
        model.addAttribute("map",map);
        model.addAttribute("editlist", CollectionUtil.getObjectFields(HrmLocation.class));
        return "topic";
    }

    @RequiresPermissions(value = "location:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HrmLocation location, Model model) {
        locationService.save(location);
        model.addAttribute("list",locationService.findAll(HrmLocation.class));
        return "topic";
    }

    @RequiresPermissions(value = "location:update")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public @ResponseBody HrmLocation showupdate(@PathVariable("id") int id) {
        HrmLocation location = locationService.findOne(HrmLocation.class, id);
        return location;
    }

    @RequiresPermissions(value = "location:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HrmLocation location,Model model) {
        locationService.update(HrmLocation.class, location);
        model.addAttribute("list",locationService.findAll(HrmLocation.class));
        return "topic";
    }

    @RequiresPermissions(value = "location:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> delete(@PathVariable("id") int id) {
        locationService.delete(HrmLocation.class,id);
        Map<String, String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }
}
