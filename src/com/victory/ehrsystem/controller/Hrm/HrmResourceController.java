package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.EducationInfo;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;
import com.victory.ehrsystem.service.hrm.OrganizationService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.vo.JsonVo;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private OrganizationService organizationService;

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

    @RequiresPermissions(value = "resource:view")
    @RequestMapping("modal/list")
    public String modal_list(Model model) {
        return "modal/hrm/HrmResource";
    }

    @RequiresPermissions(value = "resource:view")
    @RequestMapping("subcompany/{id}")
    public @ResponseBody JsonVo listBySubCompany(@PathVariable int id) {
        HrmSubCompany subCompany = organizationService.findOne_SubCompany(id);
        List<HrmResource> resources = null;
        JsonVo jsonVo = new JsonVo();
        if(subCompany != null){
            resources =hrmResourceService.findBySubCompany(subCompany);
            jsonVo.setStatus(true);
            jsonVo.put("data", getResourceList(resources));
        }else{
            jsonVo.setStatus(false).setMsg("传入参数错误！");
        }
        return jsonVo;
    }

    @RequiresPermissions(value = "resource:view")
    @RequestMapping("department/{id}")
    public @ResponseBody JsonVo listByDepartment(@PathVariable int id) {
        HrmDepartment department = organizationService.findOne_Department(id);
        List<HrmResource> resources = null;
        JsonVo jsonVo = new JsonVo();
        if(department != null){
            resources =hrmResourceService.findByDepartment(department);

            jsonVo.setStatus(true);
            jsonVo.put("data", getResourceList(resources));
        }else{
            jsonVo.setStatus(false).setMsg("传入参数错误！");
        }
        return jsonVo;
    }

    @RequiresPermissions(value = "resource:view")
    @RequestMapping("list/customer")
    public @ResponseBody JsonVo listByCustomer(String resourceStr) {
        String[] resources = resourceStr.split(",");
        List<HrmResource> resourceList = new ArrayList<>();
        JsonVo jsonVo = new JsonVo();
        for (String temp : resources) {
            if(temp.equals("")) continue;
            HrmResource resource = hrmResourceService.findOne(HrmResource.class, temp);
            if(resource != null){
                resourceList.add(resource);
            }
        }
        jsonVo.setStatus(true);
        jsonVo.put("data", getResourceList(resourceList));
        return jsonVo;
    }

    public List getResourceList(List<HrmResource> resources) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (HrmResource resource : resources) {
            Map<String, String> temp = new HashMap<>();
            temp.put("id", resource.getId() + "");
            temp.put("name", resource.getName());
            temp.put("subcompany", resource.getSubCompany().getName());
            temp.put("department", resource.getDepartment().getName());
            mapList.add(temp);
        }
        return mapList;
    }
}
