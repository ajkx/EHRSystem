package com.victory.ehrsystem.controller;

import com.victory.ehrsystem.domain.sys.User;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ajkx_Du
 * @create 2016-11-04 14:46
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> login(User user, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
        try {
            subject.login(token);
            request.getSession().setAttribute("username",user.getName());
            map.put("msg", "success");
            map.put("url","home.html");
            return map;
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            e.printStackTrace();
            map.put("msg", "fail");
            map.put("detail", "unknow");
            return map;
        } catch (LockedAccountException e) {
            e.printStackTrace();
            map.put("msg", "fail");
            map.put("detail", "locked");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
            return map;
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String test() {
        System.out.println("test");
        return "login";
    }

    //@RequestMapping(value = "/tree")
    //public @ResponseBody List<JsonTreeData> tree() {
    //    return ""
    //}

    @RequestMapping(value = "/regist")
    public String regist(User user) {
        userService.createUser(user);
        return "bb";
    }

}
