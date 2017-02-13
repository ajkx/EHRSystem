package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.EducationInfo;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.vo.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        return "hrm/resource/list";
    }

    @RequiresPermissions(value = "resource:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = hrmResourceService.findByPage(HrmResource.class,request);
        return pageInfo;
    }

    /**
     * 返回详细人员信息
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "resource:view")
    @RequestMapping(value = "/view/{id}")
    public String viewResource(@PathVariable int id, Model model){
        HrmResource resource = hrmResourceService.findOne(HrmResource.class, id);
        model.addAttribute("resource", resource);
        return "hrm/resource/detail";
    }


    @RequiresPermissions(value = "resource:create")
    @RequestMapping(value = "/create")
    public String page_create(){
        return "";
    }
    @RequiresPermissions(value = "resource:view")
    @RequestMapping(value = "/jsonlist")
    public @ResponseBody List listByNomanager(){
        List<HrmResource> list = hrmResourceService.findNoManager();
        return list;
    }
}
