package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmLocation;
import com.victory.ehrsystem.service.hrm.impl.HrmLocationService;
import com.victory.ehrsystem.util.CollectionUtil;
import com.victory.ehrsystem.vo.ColInfo;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.mgt.SessionManager;
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
@RequestMapping(value = "/location")
public class HrmLocationController {

    @Autowired
    private HrmLocationService locationService;

    @RequiresPermissions(value = "location:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){
        //列名集合
        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","名称"));
        colInfos.add(new ColInfo("address", "地点"));
        colInfos.add(new ColInfo("city", "所在城市"));

        model.addAttribute("topic","办公地点");
        model.addAttribute("simplename","地点");
        model.addAttribute("url", "/location");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "location");
        return "topic";
    }

    @RequiresPermissions(value = "location:view")
    @RequestMapping(value = "/list")
    public @ResponseBody PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = locationService.findByPage(HrmLocation.class,request);
        return pageInfo;
    }
    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "location:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","办公地点信息创建");
        model.addAttribute("action","/location/create");
        return "modal/hrm/Location";
    }
    /**
     * 执行创建的操作
     * @param location
     * @param
     * @return
     */
    @RequiresPermissions(value = "location:create")
    @RequestMapping(value = "/create")
    public @ResponseBody
    JsonVo create(HrmLocation location) {
        locationService.save(location);
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
    @RequiresPermissions(value = "location:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        model.addAttribute("topic", "办公地点信息修改");
        model.addAttribute("action","/location/update");
        model.addAttribute("map", CollectionUtil.getObejctValueAndFields(locationService.findOne(HrmLocation.class, id)));
        return "modal/hrm/Location";
    }

    /**
     * 执行修改操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "location:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(HrmLocation location,Model model) {
        locationService.update(HrmLocation.class, location);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "location:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        locationService.delete(HrmLocation.class,id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        Subject subject = SecurityUtils.getSubject();
        return jsonVo;
    }
}
