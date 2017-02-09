package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmJobActivities;
import com.victory.ehrsystem.entity.hrm.HrmLocation;
import com.victory.ehrsystem.service.hrm.impl.HrmJobActivitiesService;
import com.victory.ehrsystem.vo.ColInfo;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
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
 * @author ajkx_Du
 * @create 2016-11-21 14:52
 */
@Controller
@RequestMapping(value = "/jobactivities")
public class HrmJobActivitiesController {

    @Autowired
    private HrmJobActivitiesService jobActivitiesService;

    @RequiresPermissions(value = "jobActivities:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        //列名集合
        Subject subject = SecurityUtils.getSubject();
        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","名称"));
        colInfos.add(new ColInfo("description", "描述"));
        String template = "";
        if(subject.isPermitted("jobGroup:view")){
            template = "return \"<a href='javascript:void(0)' onclick=\\\"showEditModal('/jobgroup/view/\"+groupid.id+\"')\\\" class='font-color'>\"+groupid.name+\"</a>\";";
        }else{
            template = "return \"<a disable='' href='javascript:void(0)' onclick=\\\"showEditModal('/jobgroup/view/\"+groupid.id+\"')\\\" class='font-color'>\"+groupid.name+\"</a>\";";
        }
        colInfos.add(new ColInfo("groupid", "职务类别",template));
        model.addAttribute("topic","职务管理");
        model.addAttribute("simplename","职务");
        model.addAttribute("url", "/jobactivities");
        model.addAttribute("col", colInfos);
        model.addAttribute("per","jobActivities");
        return "topic";
    }


    @RequiresPermissions(value = "jobActivities:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = jobActivitiesService.findByPage(HrmJobActivities.class,request);
        return pageInfo;
    }

    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "jobActivities:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","职务信息创建");
        model.addAttribute("action","/jobactivities/create");
        return "modal/hrm/JobActivities";
    }

    @RequiresPermissions(value = "jobActivities:create")
    @RequestMapping(value = "/create")
    public @ResponseBody JsonVo create(HrmJobActivities activities) {
        jobActivitiesService.save(activities);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("添加成功");
        return jsonVo;
    }
    @RequiresPermissions(value = "jobActivities:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        HrmJobActivities jobActivities = jobActivitiesService.findOne(HrmJobActivities.class, id);
        Map<String, String> map = new HashMap<>();
        map.put("id",jobActivities.getId()+"");
        map.put("name",jobActivities.getName());
        map.put("description", jobActivities.getDescription());
        map.put("groupid", jobActivities.getGroupid().getId()+"");
        map.put("groupname", jobActivities.getGroupid().getName());
        model.addAttribute("topic", "职务修改");
        model.addAttribute("action","/jobactivities/update");
        model.addAttribute("map", map);
        return "modal/hrm/JobActivities";
    }

    /**
     * 执行修改操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "jobActivities:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(HrmJobActivities activities, Model model) {
        jobActivitiesService.update(HrmJobActivities.class, activities);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "jobActivities:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        jobActivitiesService.delete(HrmJobActivities.class, id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
}
