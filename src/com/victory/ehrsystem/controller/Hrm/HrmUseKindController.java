package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmJobGroups;
import com.victory.ehrsystem.entity.hrm.HrmUsekind;
import com.victory.ehrsystem.service.hrm.impl.HrmUseKindService;
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
@RequestMapping(value = "/usekind")
public class HrmUseKindController {

    @Autowired
    private HrmUseKindService useKindService;

    @RequiresPermissions(value = "useKind:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        //列名集合
        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","名称"));
        colInfos.add(new ColInfo("description", "描述"));

        model.addAttribute("topic","用工性质");
        model.addAttribute("simplename","性质");
        model.addAttribute("url", "/usekind");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "useKind");
        return "topic";
    }

    @RequiresPermissions(value = "jobGroup:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = useKindService.findByPage(HrmUsekind.class,request);
        return pageInfo;
    }

    /**
     * 返回创建模态框
     * @param model
     * @return
     */
    @RequiresPermissions(value = "useKind:create")
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String modal_create(Model model) {
        model.addAttribute("topic","用工性质创建");
        model.addAttribute("action","/usekind/create");
        model.addAttribute("map", CollectionUtil.getObjectFields(HrmUsekind.class));
        return "common/modal";
    }
    /**
     * 执行创建的操作
     * @param usekind
     * @param
     * @return
     */
    @RequiresPermissions(value = "useKind:create")
    @RequestMapping(value = "/create")
    public @ResponseBody
    JsonVo create(HrmUsekind usekind) {
        useKindService.save(usekind);
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
    @RequiresPermissions(value = "useKind:update")
    @RequestMapping(value = "/{id}")
    public String modal_update(@PathVariable int id, Model model) {
        model.addAttribute("topic", "用工性质修改");
        model.addAttribute("action","/usekind/update");
        model.addAttribute("map", CollectionUtil.getObejctValueAndFields(useKindService.findOne(HrmUsekind.class, id)));
        return "common/modal";
    }

    /**
     * 执行修改操作
     * @param
     * @return
     */
    @RequiresPermissions(value = "useKind:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(HrmUsekind usekind,Model model) {
        useKindService.update(HrmUsekind.class, usekind);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("修改成功");
        return jsonVo;
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "useKind:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonVo delete(@PathVariable("id") int id) {
        useKindService.delete(HrmUsekind.class,id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }
}
