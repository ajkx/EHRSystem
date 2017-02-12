package com.victory.ehrsystem.controller.sys;

import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.entity.sys.User;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.service.sys.PasswordHelper;
import com.victory.ehrsystem.service.sys.RoleService;
import com.victory.ehrsystem.service.sys.UserService;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.ColInfo;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
import com.victory.ehrsystem.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

        Subject subject = SecurityUtils.getSubject();

        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","账号"));
        String template1 = "";
        String template2 = "";
//      //单个链接
        if (subject.isPermitted("resource:view")) {
            template1 = StringUtil.getScript("resource","hrmResource",true);
        }else{
            template1 = StringUtil.getScript("resource","hrmResource",false);
        }

        //集合链接
        if (subject.isPermitted("role:view")) {
            template2 = StringUtil.getMultiScript("role","roles",true);
        }else{
            template2 = StringUtil.getMultiScript("role","roles",false);
        }
        colInfos.add(new ColInfo("hrmResource", "所属员工",template1));
        colInfos.add(new ColInfo("roles", "所有角色",template2));

        model.addAttribute("topic","操作员管理");
        model.addAttribute("simplename","操作员");
        model.addAttribute("url","/user");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "user");
        model.addAttribute("id", 1);
        return "topic";
    }

    @RequiresPermissions(value = "user:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = userService.findByPage(User.class,request);
        return pageInfo;
    }

    @RequiresPermissions(value = "user:view")
    @RequestMapping(value = "/view/{id}")
    public String modal_view(@PathVariable int id, Model model) {
        User user = userService.findOne(id);
        model.addAttribute("topic", "管理员信息");
        model.addAttribute("obj", user);
        return "modal/sys/manager_view";
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
        return "modal/sys/manager";
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
        User user = userService.findOne(User.class,id);
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
        return "modal/sys/manager";
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
            User user = userService.findOne(User.class,userVo.getId());

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
    @RequestMapping(value = "/jsonlist")
    public @ResponseBody List jsonList(){
        List<User> temp = userService.findAll(User.class);
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
