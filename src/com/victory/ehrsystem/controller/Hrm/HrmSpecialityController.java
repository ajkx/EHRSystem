package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmSpeciality;
import com.victory.ehrsystem.service.hrm.impl.HrmSpecialityService;
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
@RequestMapping(value = "/speciality")
public class HrmSpecialityController {

    @Autowired
    private HrmSpecialityService specialityService;

    @RequiresPermissions(value = "speciality:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmSpeciality> temp = specialityService.findAll(HrmSpeciality.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmSpeciality speciality : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", speciality.getName());
            tempmap.put("详述", speciality.getDescription());
            map.put(speciality.getId(),tempmap);
        }
        model.addAttribute("topic","专业管理");
        model.addAttribute("simplename","专业");
        model.addAttribute("url", "/speciality");
        model.addAttribute("map",map);
        return "topic";
    }

    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "speciality:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","专业信息创建");
        model.addAttribute("action","/speciality/create");
        return "modal/Speciality";
    }
    /**
     * 执行创建的操作
     * @param speciality
     * @param
     * @return
     */
    @RequiresPermissions(value = "speciality:create")
    @RequestMapping(value = "/create")
    public @ResponseBody
    JsonVo create(HrmSpeciality speciality) {
        specialityService.save(speciality);
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
    @RequiresPermissions(value = "speciality:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        model.addAttribute("topic", "专业信息修改");
        model.addAttribute("action","/speciality/update");
        model.addAttribute("map", CollectionUtil.getObejctValueAndFields(specialityService.findOne(HrmSpeciality.class, id)));
        return "modal/Speciality";
    }

    /**
     * 执行修改操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "speciality:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(HrmSpeciality speciality,Model model) {
        specialityService.update(HrmSpeciality.class, speciality);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "speciality:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        specialityService.delete(HrmSpeciality.class,id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
}
