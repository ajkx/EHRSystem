package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmEducationLevel;
import com.victory.ehrsystem.service.hrm.impl.HrmEducationLevelService;
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
@RequestMapping(value = "/educationlevel")
public class HrmEducationLevelController {

    @Autowired
    private HrmEducationLevelService educationLevelService;

    /**
     * 返回学历的通用主页
     * @param model
     * @return
     */
    @RequiresPermissions(value = "educationLevel:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmEducationLevel> temp = educationLevelService.findAll(HrmEducationLevel.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmEducationLevel educationlevel : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", educationlevel.getName());
            tempmap.put("详述", educationlevel.getDescription());
            map.put(educationlevel.getId(),tempmap);
        }
        model.addAttribute("topic","学历管理");
        model.addAttribute("simplename","学历");
        model.addAttribute("url","educationlevel");
        model.addAttribute("map",map);
        model.addAttribute("editlist", CollectionUtil.getObjectFields(HrmEducationLevel.class));
        return "topic";
    }

    @RequiresPermissions(value = "educationLevel:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String dialog_create(Model model) {
        return "/location/dialog";
    }

    /**
     * 执行创建的操作
     * @param educationlevel
     * @param model
     * @return
     */
    @RequiresPermissions(value = "educationLevel:create")
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody JsonVo create(HrmEducationLevel educationlevel,Model model) {
        educationLevelService.save(educationlevel);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("添加成功");
        return jsonVo;
    }

    @RequiresPermissions(value = "jobCall:update")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public @ResponseBody HrmEducationLevel showupdate(@PathVariable("id") int id) {
        HrmEducationLevel educationlevel = educationLevelService.findOne(HrmEducationLevel.class, id);
        return educationlevel;
    }

    @RequiresPermissions(value = "jobCall:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HrmEducationLevel educationlevel,Model model) {
        educationLevelService.update(HrmEducationLevel.class, educationlevel);
        model.addAttribute("list",educationLevelService.findAll(HrmEducationLevel.class));
        return "topic";
    }

    @RequiresPermissions(value = "jobCall:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> delete(@PathVariable("id") int id) {
        educationLevelService.delete(HrmEducationLevel.class,id);
        Map<String, String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }
}
