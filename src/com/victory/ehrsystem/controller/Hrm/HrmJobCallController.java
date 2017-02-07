package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmJobCall;
import com.victory.ehrsystem.service.hrm.impl.HrmJobCallService;
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
@RequestMapping(value = "/jobcall")
public class HrmJobCallController {

    @Autowired
    private HrmJobCallService jobCallService;

    @RequiresPermissions(value = "jobCall:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmJobCall> temp = jobCallService.findAll(HrmJobCall.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmJobCall jobcall : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", jobcall.getName());
            tempmap.put("详述", jobcall.getDescription());
            map.put(jobcall.getId(),tempmap);
        }
        model.addAttribute("topic","职称管理");
        model.addAttribute("simplename","职称");
        model.addAttribute("url","/jobcall");
        model.addAttribute("map",map);
        model.addAttribute("width","33%");
        return "topic";
    }

    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "jobcall:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","职称信息创建");
        model.addAttribute("action","/jobcall/create");
        return "modal/hrm/JobCall";
    }
    /**
     * 执行创建的操作
     * @param jobcall
     * @param
     * @return
     */
    @RequiresPermissions(value = "jobcall:create")
    @RequestMapping(value = "/create")
    public @ResponseBody
    JsonVo create(HrmJobCall jobcall) {
        jobCallService.save(jobcall);
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
    @RequiresPermissions(value = "jobcall:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        HrmJobCall jobCall = jobCallService.findOne(HrmJobCall.class, id);
        Map<String, String> map = new HashMap<>();
        map.put("id",jobCall.getId()+"");
        map.put("name",jobCall.getName());
        map.put("description", jobCall.getDescription());
        model.addAttribute("topic", "职称信息修改");
        model.addAttribute("action","/jobcall/update");
        model.addAttribute("map",map);
        return "modal/hrm/JobCall";
    }

    /**
     * 执行修改操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "jobcall:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(HrmJobCall jobcall,Model model) {
        jobCallService.update(HrmJobCall.class, jobcall);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "jobcall:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        jobCallService.delete(HrmJobCall.class,id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
}
