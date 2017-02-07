package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.entity.hrm.HrmUsekind;
import com.victory.ehrsystem.service.hrm.impl.HrmUseKindService;
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
@RequestMapping(value = "/usekind")
public class HrmUseKindController {

    @Autowired
    private HrmUseKindService useKindService;

    @RequiresPermissions(value = "useKind:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        List<HrmUsekind> temp = useKindService.findAll(HrmUsekind.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (HrmUsekind usekind : temp) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("名称", usekind.getName());
            tempmap.put("详述", usekind.getDescription());
            map.put(usekind.getId(),tempmap);
        }
        model.addAttribute("topic","用工性质");
        model.addAttribute("simplename","性质");
        model.addAttribute("url", "/usekind");
        model.addAttribute("map",map);
        model.addAttribute("width","33%");
        return "topic";
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
