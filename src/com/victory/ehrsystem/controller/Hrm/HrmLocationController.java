package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmLocation;
import com.victory.ehrsystem.service.hrm.impl.HrmLocationService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        List<HrmLocation> temp = locationService.findAll(HrmLocation.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmLocation location : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", location.getName());
            tempmap.put("地点", location.getAddress());
            tempmap.put("所在城市", location.getCity());
            map.put(location.getId(),tempmap);
        }
        model.addAttribute("topic","办公地点");
        model.addAttribute("simplename","地点");
        model.addAttribute("url", "/location");
        model.addAttribute("map",map);
        return "topic";
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
        return "modal/Location";
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
        return "modal/Location";
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
        return jsonVo;
    }
}
