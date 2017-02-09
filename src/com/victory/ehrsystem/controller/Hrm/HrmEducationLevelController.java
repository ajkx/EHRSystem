package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmEducationLevel;
import com.victory.ehrsystem.entity.hrm.HrmLocation;
import com.victory.ehrsystem.service.hrm.impl.HrmEducationLevelService;
import com.victory.ehrsystem.util.CollectionUtil;
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
        //列名集合
        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","名称"));
        colInfos.add(new ColInfo("description", "描述"));

        model.addAttribute("topic","学历管理");
        model.addAttribute("simplename","学历");
        model.addAttribute("url", "/educationlevel");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "educationLevel");
        return "topic";
    }

    @RequiresPermissions(value = "educationLevel:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = educationLevelService.findByPage(HrmEducationLevel.class,request);
        return pageInfo;
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
        return "modal/hrm/EducationLevel";
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
        return "modal/hrm/EducationLevel";
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
