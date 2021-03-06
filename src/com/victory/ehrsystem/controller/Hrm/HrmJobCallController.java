package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.JobCall;
import com.victory.ehrsystem.service.hrm.impl.JobCallService;
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
@RequestMapping(value = "/jobcall")
public class HrmJobCallController {

    @Autowired
    private JobCallService jobCallService;

    @RequiresPermissions(value = "jobCall:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        //列名集合
        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","名称"));
        colInfos.add(new ColInfo("description", "描述"));

        model.addAttribute("topic","职称管理");
        model.addAttribute("simplename","职称");
        model.addAttribute("url", "/jobcall");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "jobCall");
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = jobCallService.findByPage(JobCall.class,request);
        return pageInfo;
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
    JsonVo create(JobCall jobcall) {
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
        JobCall jobCall = jobCallService.findOne(JobCall.class, id);
        Map<String, String> map = new HashMap<>();
        map.put("id",jobCall.getId()+"");
        map.put("name",jobCall.getName());
        map.put("description", jobCall.getName());
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
    public @ResponseBody JsonVo update(JobCall jobcall, Model model) {
        jobCallService.update(JobCall.class, jobcall);
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
        jobCallService.delete(JobCall.class,id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
}
