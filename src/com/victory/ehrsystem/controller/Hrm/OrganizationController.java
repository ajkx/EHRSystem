package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;
import com.victory.ehrsystem.service.hrm.OrganizationService;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.service.sys.UserService;
import com.victory.ehrsystem.util.TreeNodeUtil;
import com.victory.ehrsystem.vo.JsonTreeData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-11-18 15:50
 */
@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    /**
     * 进入组织架构的主页
     * @return
     */
    @RequiresPermissions(value = "organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/organization/index";

    }

    /**
     * 返回组织机构树的json数据
     * @return
     */
    @RequiresPermissions(value = "organization:view")
    @RequestMapping(value = "/tree")
    public @ResponseBody List<JsonTreeData> getTree(String type){
        List<HrmSubCompany> list = organizationService.findAllSubCompany_NoParent();
        List<JsonTreeData> tree = TreeNodeUtil.convertSubTreeList(list,organizationService,type);
        return tree;
    }

    /**
     * 返回分部详细
     * @param id
     * @return
     */
    @RequiresPermissions(value = "organization:view")
    @RequestMapping(value = "/subcompany/{id}")
    public String viewSubcompany(@PathVariable(value = "id") int id,Model model) {
        HrmSubCompany subCompany = organizationService.findOne_SubCompany(id);
        List<HrmSubCompany> list_sub = organizationService.findAllSubcompanyBySubcompany(subCompany);
        List<HrmDepartment> list_dep = organizationService.findAllDepartmentBySubcompany(subCompany);
        model.addAttribute("entity", subCompany);
        model.addAttribute("list_dep", list_dep);
        model.addAttribute("list_sub", list_sub);
        return "/organization/sub_detail";
    }

    /**
     * 返回部门详细
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "organization:view")
    @RequestMapping(value = "/department/{id}")
    public String viewDepartment(@PathVariable(value = "id") int id,Model model) {
        HrmDepartment department = organizationService.findOne_Department(id);
        List<HrmDepartment> list_dep = organizationService.findAllDepartmentByDepartment(department);
        model.addAttribute("entity", department);
        model.addAttribute("list", list_dep);
        return "/organization/dep_detail";
    }
}
