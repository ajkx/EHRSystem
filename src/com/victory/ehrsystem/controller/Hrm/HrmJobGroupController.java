package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmJobGroups;
import com.victory.ehrsystem.service.hrm.impl.HrmJobGroupsService;
import com.victory.ehrsystem.util.CollectionUtil;
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
@RequestMapping(value = "/jobgroup")
public class HrmJobGroupController {

    @Autowired
    private HrmJobGroupsService jobGroupsService;

    @RequiresPermissions(value = "jobGroup:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmJobGroups> temp = jobGroupsService.findAll(HrmJobGroups.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmJobGroups jobgroup : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", jobgroup.getName());
            tempmap.put("详述", jobgroup.getDescription());
            map.put(jobgroup.getId(),tempmap);
        }
        model.addAttribute("topic","职务类别");
        model.addAttribute("simplename","类别");
        model.addAttribute("url", "/jobgroup");
        model.addAttribute("map",map);
        return "topic";
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
        return "common/modal";
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
        return "common/modal";
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
}
