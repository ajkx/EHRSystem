package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmJobActivities;
import com.victory.ehrsystem.service.hrm.impl.HrmJobActivitiesService;
import com.victory.ehrsystem.vo.JsonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ajkx_Du
 * @create 2016-11-21 14:52
 */
@Controller
@RequestMapping(value = "/jobactivities")
public class HrmJobActivitiesController {

    @Autowired
    private HrmJobActivitiesService jobActivitiesService;

    @RequiresPermissions(value = "jobactivities:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmJobActivities> temp = jobActivitiesService.findAll(HrmJobActivities.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmJobActivities jobactivity : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", jobactivity.getName());
            tempmap.put("详述", jobactivity.getDescription());
            tempmap.put("职务类型", jobactivity.getGroupid().getName());
            map.put(jobactivity.getId(),tempmap);
        }
        model.addAttribute("topic","职务管理");
        model.addAttribute("simplename","职务");
        model.addAttribute("url", "/jobactivities");
        model.addAttribute("map",map);
        return "topic";
    }

    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "jobactivities:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","职务信息创建");
        model.addAttribute("action","/jobactivities/create");
        return "modal/hrm/JobActivities";
    }

    @RequiresPermissions(value = "jobactivities:create")
    @RequestMapping(value = "/create")
    public @ResponseBody JsonVo create(HrmJobActivities activities) {
        jobActivitiesService.save(activities);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("添加成功");
        return jsonVo;
    }

    @RequiresPermissions(value = "jobactivities:update")
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
    @RequiresPermissions(value = "jobactivities:update")
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
    @RequiresPermissions(value = "jobactivities:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        jobActivitiesService.delete(HrmJobActivities.class, id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
}
