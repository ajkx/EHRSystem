package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmJobActivities;
import com.victory.ehrsystem.entity.hrm.HrmJobGroups;
import com.victory.ehrsystem.service.hrm.impl.HrmJobGroupsService;
import com.victory.ehrsystem.util.CollectionUtil;
import com.victory.ehrsystem.vo.ColInfo;
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
import java.util.*;

/**
 * @author ajkx_Du
 * @create 2016-11-21 14:52
 */
@Controller
@RequestMapping(value = "/jobgroup")
public class HrmJobGroupController {

    @Autowired
    private HrmJobGroupsService jobGroupsService;

    @RequiresPermissions(value = "jobGroup:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        //列名集合
        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","类别名"));
        colInfos.add(new ColInfo("description", "描述"));

        model.addAttribute("topic","职务类别");
        model.addAttribute("simplename","类别");
        model.addAttribute("url", "/jobgroup");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "jobGroup");
        return "topic";
    }


    @RequiresPermissions(value = "jobGroup:view")
    @RequestMapping(value = "/list")
    public @ResponseBody PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = jobGroupsService.findByPage(HrmJobGroups.class,request);
        return pageInfo;
    }

    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "jobGroup:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","职务类别信息创建");
        model.addAttribute("action","/jobgroup/create");
        model.addAttribute("map", CollectionUtil.getObjectFields(HrmJobGroups.class));
        return "modal/hrm/JobGroup";
    }
    /**
     * 执行创建的操作
     * @param jobgroup
     * @param
     * @return
     */
    @RequiresPermissions(value = "jobGroup:create")
    @RequestMapping(value = "/create")
    public @ResponseBody
    JsonVo create(HrmJobGroups jobgroup) {
        jobGroupsService.save(jobgroup);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("添加成功");
        return jsonVo;
    }

    @RequiresPermissions(value = "jobGroup:view")
    @RequestMapping(value = "/view/{id}")
    public String modal_view(@PathVariable int id, Model model) {
        HrmJobGroups jobGroups = jobGroupsService.findOne(HrmJobGroups.class, id);
        model.addAttribute("topic", "职务类别信息");
        model.addAttribute("obj", jobGroups);
        return "modal/hrm/JobGroup_view";
    }
    /**
     * 返回修改模态框
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "jobGroup:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        model.addAttribute("topic", "职务类别信息修改");
        model.addAttribute("action","/jobgroup/update");
        model.addAttribute("map", CollectionUtil.getObejctValueAndFields(jobGroupsService.findOne(HrmJobGroups.class, id)));
        return "modal/hrm/JobGroup";
    }

    /**
     * 执行修改操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "jobGroup:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(HrmJobGroups jobgroup,Model model) {
        jobGroupsService.update(HrmJobGroups.class, jobgroup);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "jobGroup:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        jobGroupsService.delete(HrmJobGroups.class,id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }

    @RequestMapping(value = "/jsonlist")
    public @ResponseBody List jsonList(){
        List<HrmJobGroups> groupses = jobGroupsService.findAll(HrmJobGroups.class);
        return groupses;
    }
}
