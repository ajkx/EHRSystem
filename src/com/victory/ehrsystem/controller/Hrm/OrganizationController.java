package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmSubCompany;
import com.victory.ehrsystem.service.hrm.OrganizationService;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.service.sys.UserService;
import com.victory.ehrsystem.util.TreeNodeUtil;
import com.victory.ehrsystem.vo.JsonTreeData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public @ResponseBody List<JsonTreeData> getTree(){
        List<HrmSubCompany> list = organizationService.findAllSubCompany_NoParent();
        List<JsonTreeData> tree = TreeNodeUtil.convertSubTreeList(list,organizationService);
        return tree;
    }

    @RequiresPermissions(value = "organization:view")
    @RequestMapping(value = "/subcompany/{id}")
    public String viewSubcompany(@PathVariable(value = "id") int id) {
        return "/organization/detail";
    }
}
