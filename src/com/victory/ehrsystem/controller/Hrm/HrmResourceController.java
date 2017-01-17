package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/1/17.
 * Time:9:21
 */
@Controller
@RequestMapping(value = "/resource")
public class HrmResourceController {

    @Autowired
    private HrmResourceService hrmResourceService;

    @RequiresPermissions(value = "resource:view")
    public String index(Model model){
        List<HrmResource> list = hrmResourceService.findAll(HrmResource.class);

        return "hrm/resource";
    }

    @RequiresPermissions(value = "resource:view")
    @RequestMapping(value = "/list")
    public @ResponseBody List listByNomanager(){
        List<HrmResource> list = hrmResourceService.findNoManager();
        return list;
    }
}
