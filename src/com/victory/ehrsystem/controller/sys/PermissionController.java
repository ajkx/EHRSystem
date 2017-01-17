package com.victory.ehrsystem.controller.sys;

import com.victory.ehrsystem.entity.sys.SysResource;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.entity.sys.User;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.service.sys.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/1/16.
 * Time:15:58
 */
@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions(value = "permission:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<SysResource> temp = resourceService.findAll();
        List<Map<String, Object>> list = new ArrayList<>();
        model.addAttribute("topic","权限管理");
        model.addAttribute("simplename","权限");
        model.addAttribute("url","/permission");
        model.addAttribute("list",temp);
        return "sys/permission";
    }

    @RequiresPermissions(value = "user:view")
    @RequestMapping(value = "/list")
    public @ResponseBody List list(){
        List<SysResource> temp = resourceService.findAll();
        List<Map<String, String>> list = new ArrayList<>();
        for (SysResource resource : temp) {
            Map<String, String> map = new HashMap<>();
            map.put("id", resource.getId() + "");
            map.put("name", resource.getName());
            list.add(map);
        }
        return list;
    }
}
