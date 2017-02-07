package com.victory.ehrsystem.controller.sys;

import com.victory.ehrsystem.entity.hrm.HrmEducationLevel;
import com.victory.ehrsystem.entity.hrm.HrmJobCall;
import com.victory.ehrsystem.entity.hrm.HrmLocation;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.entity.sys.User;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.service.sys.RoleService;
import com.victory.ehrsystem.service.sys.UserService;
import com.victory.ehrsystem.service.sys.impl.PasswordHelper;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.UserVo;
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
@RequestMapping(value = "/user")
public class ManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private HrmResourceService hrmResourceService;

    @Autowired
    private RoleService roleService;

    @Autowired
    public PasswordHelper passwordHelper;

    @RequiresPermissions(value = "user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<User> temp = userService.findAll();
        model.addAttribute("topic","操作员管理");
        model.addAttribute("simplename","操作员");
        model.addAttribute("url","/user");
        model.addAttribute("list",temp);
        return "sys/manager";
    }

    /**
     * 返回创建的模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "user:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","操作员创建");
        model.addAttribute("action","/user/create");
        model.addAttribute("isCreate", true);
        return "sys/modal/manager";
    }

    /**
     * 执行创建的操作
     * @param
     * @param
     * @return
     */
    @RequiresPermissions(value = "user:create")
    @RequestMapping(value = "/create")
    public @ResponseBody
    JsonVo create(UserVo userVo) {
        JsonVo jsonVo = new JsonVo();
        if(!userVo.getPassword().equals(userVo.getPassword_confirm())){
            jsonVo.setStatus(false).setMsg("两次输入密码不一致!");
        }else{
            if(userService.findByUsername(userVo.getName()) != null){
                jsonVo.setStatus(false).setMsg("账号名已存在");
            }else{
                User user = new User();
                user.setName(userVo.getName());
                user.setPassword(userVo.getPassword());
                user.setHrmResource(userVo.getResourceid());
                Set<SysRole> roles = new HashSet<>();
                for(String roleid : userVo.getRoleids().split(",")){
                    if(roleid.equals("")) continue;
                    roles.add(roleService.findOne(Integer.parseInt(roleid)));
                }
                user.setRoleids(roles);
                user.setLocked(false);
                userService.createUser(user);
                jsonVo.setStatus(true).setMsg("注册成功");
            }
        }
        return jsonVo;
    }


    /**
     * 返回修改模态框
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "user:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        User user = userService.findOne(id);
        UserVo userVo = new UserVo();
        userVo.setName(user.getName());
        userVo.setId(user.getId());
        if(user.getHrmResource() != null){
            userVo.setResourceid(user.getHrmResource());
        }
        userVo.setLock(user.getLocked());
        StringBuilder sb = new StringBuilder();
        List<Map<String, String>> list = new ArrayList<>();
        for(SysRole role : user.getRoleids()){
            Map map = new HashMap();
            map.put("id", role.getId() + "");
            map.put("name", role.getName());
            list.add(map);
            sb.append(role.getId()+",");
        }
        if(sb.length() > 1){
            sb.substring(0,sb.length() - 1);
        }
        userVo.setRoleids(sb.toString());


        model.addAttribute("topic", "操作员信息修改");
        model.addAttribute("action","/user/update");
        model.addAttribute("map",userVo);
        model.addAttribute("list", list);
        model.addAttribute("isCreate", false);
        return "sys/modal/manager";
    }

    @RequiresPermissions(value = "user:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(UserVo userVo){
        //System.out.println(userVo.toString());
        JsonVo jsonVo = new JsonVo();
        boolean flag = false;
        if(userVo.getPassword().equals("") || userVo.getPassword_confirm().equals("")){
            flag = false;
        }else{
            flag = true;
        }
        if(!userVo.getPassword().equals(userVo.getPassword_confirm())){
            jsonVo.setStatus(false).setMsg("两次输入密码不一致!");
        }else{
            User user = userService.findOne(userVo.getId());

            user.setHrmResource(userVo.getResourceid());
            Set<SysRole> roles = new HashSet<>();
            for(String roleid : userVo.getRoleids().split(",")){
                if(roleid.equals("")) continue;
                roles.add(roleService.findOne(Integer.parseInt(roleid)));
            }
            user.setRoleids(roles);
            if(flag){
                String salt = user.getCredentialsSalt();
                user.setPassword(passwordHelper.encryptPassword(userVo.getPassword(),salt));
            }
            user.setLocked(userVo.getLock());
            userService.updateUser(user);
            jsonVo.setStatus(true).setMsg("信息更新完成!");
        }
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "user:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        userService.delete(id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }

    @RequiresPermissions(value = "user:view")
    @RequestMapping(value = "/list")
    public @ResponseBody List list(){
        List<User> temp = userService.findAll();
        List<Map<String, String>> list = new ArrayList<>();
        for (User user : temp) {
            Map<String, String> map = new HashMap<>();
            map.put("id", user.getId() + "");
            map.put("name", user.getName());
            list.add(map);
        }
        return list;
    }
}
