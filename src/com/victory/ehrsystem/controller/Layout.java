package com.victory.ehrsystem.controller;

import com.victory.ehrsystem.domain.sys.SysModule;
import com.victory.ehrsystem.domain.sys.SysResource;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

/**
 * 公用页面载入
 *
 * @author ajkx_Du
 * @create 2016-11-26 9:27
 */
@Controller
public class Layout {
    public static final String LAYOUT_PATH = "/layout";
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/layout")
    public String layout(HttpSession session, Model model) {
        Map<SysModule,ArrayList<SysResource>> maps = resourceService.findMenus(userService.findPermissions((String) session.getAttribute("username")));
        model.addAttribute("menu", maps);
        return "common/layout";
    }


}
