package com.victory.ehrsystem.controller.sys;

import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.sys.SysResource;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.entity.sys.User;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.service.sys.RoleService;
import com.victory.ehrsystem.service.sys.UserService;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.RoleVo;
import org.apache.log4j.lf5.util.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/1/16.
 * Time:15:58
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions(value = "role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<SysRole> temp = roleService.findAll();
        List<Map<String, Object>> list = new ArrayList<>();
        model.addAttribute("topic","角色管理");
        model.addAttribute("simplename","角色");
        model.addAttribute("url","/role");
        model.addAttribute("list",temp);
        return "sys/role";
    }

    /**
     * 返回创建的模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","角色创建");
        model.addAttribute("action","/role/create");
        model.addAttribute("isCreate", true);
        return "sys/modal/role";
    }

    /**
     * 执行创建的操作
     * @param
     * @param
     * @return
     */
    @RequiresPermissions(value = "role:create")
    @RequestMapping(value = "/create")
    public @ResponseBody
    JsonVo create(RoleVo roleVo) {
        JsonVo jsonVo = new JsonVo();
        SysRole role = new SysRole();
        role.setName(roleVo.getName());
        role.setDescription(roleVo.getDescription());
        Set<User> users = new HashSet<>();
        for (String userid : roleVo.getResources().split(",")) {
            if(userid.equals("")) continue;
            users.add(userService.findOne(Integer.parseInt(userid)));
        }
        role.setUsers(users);

        Set<SysResource> resources = new HashSet<>();
        for (String resourceid : roleVo.getPermissions().split(",")) {
            if(resourceid.equals("")) continue;
            resources.add(resourceService.findOne(Integer.parseInt(resourceid)));
        }
        role.setResources(resources);

        roleService.createRole(role);
        jsonVo.setStatus(true).setMsg("创建角色成功");
        return jsonVo;
    }


    /**
     * 返回修改模态框
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        SysRole role = roleService.findOne(id);
        RoleVo roleVo = new RoleVo();
        roleVo.setName(role.getName());
        roleVo.setId(role.getId());

        StringBuilder reStr = new StringBuilder();
        List<Map<String, String>> reList = new ArrayList<>();
        for(SysResource resource : role.getResources()){
            Map<String,String> map = new HashMap();
            map.put("id", resource.getId() + "");
            map.put("name", resource.getName());
            reList.add(map);
            reStr.append(role.getId()+",");
        }
        if(reStr.length() > 1){
            reStr.substring(0,reStr.length() - 1);
        }
        roleVo.setPermissions(reStr.toString());

        StringBuilder userStr = new StringBuilder();
        List<Map<String, String>> userList = new ArrayList<>();
        for(User user : role.getUsers()){
            Map<String,String> map = new HashMap();
            map.put("id", user.getId() + "");
            map.put("name", user.getName());
            userList.add(map);
            userStr.append(role.getId()+",");
        }
        if(userStr.length() > 1){
            userStr.substring(0,userStr.length() - 1);
        }
        roleVo.setResources(userStr.toString());


        model.addAttribute("topic", "操作员信息修改");
        model.addAttribute("action","/role/update");
        model.addAttribute("map",roleVo);
        model.addAttribute("relist", reList);
        model.addAttribute("userlist", userList);
        model.addAttribute("isCreate", false);
        return "sys/modal/role";
    }

    @RequiresPermissions(value = "role:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(RoleVo roleVo){
        //System.out.println(roleVo.toString());
        JsonVo jsonVo = new JsonVo();
        SysRole role = roleService.findOne(roleVo.getId());
        role.setName(roleVo.getName());
        role.setDescription(roleVo.getDescription());

        Set<User> users = new HashSet<>();
        for (String userid : roleVo.getResources().split(",")) {
            if(userid.equals("")) continue;
            users.add(userService.findOne(Integer.parseInt(userid)));
        }
        role.setUsers(users);

        Set<SysResource> resources = new HashSet<>();
        for (String resourceid : roleVo.getPermissions().split(",")) {
            if(resourceid.equals("")) continue;
            resources.add(resourceService.findOne(Integer.parseInt(resourceid)));
        }
        role.setResources(resources);

        roleService.updateRole(role);

        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "role:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        roleService.deleteRole(id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
    
    
    @RequiresPermissions(value = "role:view")
    @RequestMapping(value = "/list")
    public @ResponseBody List list(){
        List<SysRole> temp = roleService.findAll();
        List<Map<String, String>> list = new ArrayList<>();
        for (SysRole role : temp) {
            Map<String, String> map = new HashMap<>();
            map.put("id", role.getId() + "");
            map.put("name", role.getName());
            list.add(map);
        }
        return list;
    }
}
