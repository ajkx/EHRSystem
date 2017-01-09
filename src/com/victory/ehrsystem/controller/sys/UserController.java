package com.victory.ehrsystem.controller.sys;

import com.victory.ehrsystem.entity.sys.User;
import com.victory.ehrsystem.service.sys.UserService;
import com.victory.ehrsystem.service.sys.impl.PasswordHelper;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PasswordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ajkx
 * Date: 2016/12/28.
 * Time:19:54
 */
@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public PasswordHelper passwordHelper;

    @RequestMapping(value = "/change")
    public String changePassword_modal(){
        return "";
    }

    @RequestMapping(value = "/changepassword")
    public @ResponseBody JsonVo changePassword(PasswordVo passwordVo,HttpSession session){
        //先判断用户
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);
        String currentPassword = passwordVo.getCurrentPassword();
        String newPassword = passwordVo.getPassword();
        String confirmPassword = passwordVo.getConfirmPassword();
        String salt ="";
        JsonVo jsonVo = new JsonVo();
        if (user != null) {
            salt = user.getCredentialsSalt();
            if(!passwordHelper.encryptPassword(currentPassword,salt).equals(user.getPassword())){
                jsonVo.setStatus(false);
                jsonVo.setMsg("原密码错误！");
            }else{
                if(!passwordHelper.encryptPassword(newPassword,salt).equals(user.getPassword())){
                    user.setPassword(passwordHelper.encryptPassword(newPassword,salt));
                    userService.updateUser(user);
                    jsonVo.setStatus(true);
                    jsonVo.setMsg("修改完成！");
                }
            }

        }else{
            jsonVo.setStatus(false);
            jsonVo.setMsg("会话过期！");
        }
        return jsonVo;
    }
}
