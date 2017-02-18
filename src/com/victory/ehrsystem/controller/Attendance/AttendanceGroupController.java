package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.service.attendance.AttendanceGroupService;
import com.victory.ehrsystem.vo.ColInfo;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajkx on 2017/2/17.
 */
@Controller
@RequestMapping(value = "/group")
public class AttendanceGroupController {

    @Autowired
    private AttendanceGroupService groupService;

    @RequiresPermissions(value = "AttendanceGroup:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","名称","300px",""));
        colInfos.add(new ColInfo("count", "人数"));
        colInfos.add(new ColInfo("type", "类型"));
        colInfos.add(new ColInfo("time", "考勤时间","500px",""));
        colInfos.add(new ColInfo("description","描述"));
        model.addAttribute("topic","考勤组");
        model.addAttribute("simplename","考勤组");
        model.addAttribute("url","/group");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "AttendanceGroup");
        return "attendance/attendanceGroup";
    }

    @RequiresPermissions(value = "AttendanceGroup:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = groupService.findByPage(AttendanceGroup.class,request);
        return pageInfo;
    }

    @RequestMapping(value = "/setting/{id}")
    public String detail(@PathVariable int id,Model model){
        AttendanceGroup group = groupService.findOne(AttendanceGroup.class, id);
        model.addAttribute("group", group);
        return "attendance/group_detail";
    }
}
