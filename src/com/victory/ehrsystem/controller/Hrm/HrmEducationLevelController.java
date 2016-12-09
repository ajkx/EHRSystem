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
        model.addAttribute("url","/educationlevel");
        model.addAttribute("map",map);
        return "topic";
    }

    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "educationLevel:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","学历信息创建");
        model.addAttribute("action","/educationlevel/create");
        model.addAttribute("map", CollectionUtil.getObjectFields(HrmEducationLevel.class));
        return "common/modal";
    }
    /**
     * 执行创建的操作
     * @param educationlevel
     * @param
     * @return
     */
    @RequiresPermissions(value = "educationLevel:create")
    @RequestMapping(value = "/create")
    public @ResponseBody JsonVo create(HrmEducationLevel educationlevel) {
        educationLevelService.save(educationlevel);
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
    @RequiresPermissions(value = "educationLevel:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        model.addAttribute("topic", "学历信息修改");
        model.addAttribute("action","/educationlevel/update");
        model.addAttribute("map", CollectionUtil.getObejctValueAndFields(educationLevelService.findOne(HrmEducationLevel.class, id)));
        return "common/modal";
    }

    /**
     * 执行修改操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "educationLevel:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(HrmEducationLevel educationlevel,Model model) {
        educationLevelService.update(HrmEducationLevel.class, educationlevel);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "educationLevel:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        educationLevelService.delete(HrmEducationLevel.class,id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
}
