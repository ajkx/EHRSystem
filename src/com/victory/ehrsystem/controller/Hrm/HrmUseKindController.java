package com.victory.ehrsystem.controller.Hrm;

import com.victory.ehrsystem.domain.hrm.HrmUsekind;
import com.victory.ehrsystem.service.hrm.impl.HrmUseKindService;
import com.victory.ehrsystem.util.CollectionUtil;
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
        model.addAttribute("url","usekind");
        model.addAttribute("map",map);
        model.addAttribute("editlist", CollectionUtil.getObjectFields(HrmUsekind.class));
        return "topic";
    }

    @RequiresPermissions(value = "useKind:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HrmUsekind usekind,Model model) {
        useKindService.save(usekind);
        model.addAttribute("list",useKindService.findAll(HrmUsekind.class));
        return "topic";
    }

    @RequiresPermissions(value = "useKind:update")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public @ResponseBody HrmUsekind showupdate(@PathVariable("id") int id) {
        HrmUsekind usekind = useKindService.findOne(HrmUsekind.class, id);
        return usekind;
    }

    @RequiresPermissions(value = "useKind:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HrmUsekind usekind,Model model) {
        useKindService.update(HrmUsekind.class, usekind);
        model.addAttribute("list",useKindService.findAll(HrmUsekind.class));
        return "topic";
    }

    @RequiresPermissions(value = "useKind:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> delete(@PathVariable("id") int id) {
        useKindService.delete(HrmUsekind.class,id);
        Map<String, String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }
}
