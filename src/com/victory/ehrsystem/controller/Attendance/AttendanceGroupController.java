package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.service.attendance.AttendanceGroupService;
import com.victory.ehrsystem.util.StringUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 返回创建页面
     * @return
     */
    @RequiresPermissions(value = "AttendanceGroup:create")
    @RequestMapping(value = "/edit")
    public String page_create(){
        return "attendance/group_detail";
    }
    @RequestMapping(value = "/setting/{id}")
    public String detail(@PathVariable int id,Model model){
        AttendanceGroup group = groupService.findOne(AttendanceGroup.class, id);


        model.addAttribute("group", group);
        AttendanceSchedule monday = group.getMonday();
        if(monday != null && (monday.getRest() == null || !monday.getRest())){
            model.addAttribute("monday", monday.getName()+":"+StringUtil.getScheduleTime(monday));
        }

        AttendanceSchedule tuesday = group.getTuesday();
        if(tuesday != null && (tuesday.getRest() == null || !tuesday.getRest())){
            model.addAttribute("tuesday", tuesday.getName()+":"+StringUtil.getScheduleTime(tuesday));
        }

        AttendanceSchedule wednesday = group.getWednesday();
        if(wednesday != null && (wednesday.getRest() == null || !wednesday.getRest())){
            model.addAttribute("wednesday", wednesday.getName()+":"+StringUtil.getScheduleTime(wednesday));
        }

        AttendanceSchedule thursday = group.getThursday();
        if(thursday != null && (thursday.getRest() == null || !thursday.getRest())){
            model.addAttribute("thursday", thursday.getName()+":"+StringUtil.getScheduleTime(thursday));
        }

        AttendanceSchedule friday = group.getFriday();
        if(friday != null && (friday.getRest() == null || !friday.getRest())){
            model.addAttribute("friday", friday.getName()+":"+StringUtil.getScheduleTime(friday));
        }

        AttendanceSchedule saturday = group.getSaturday();
        if(saturday != null && (saturday.getRest() == null || !saturday.getRest())){
            model.addAttribute("saturday", saturday.getName()+":"+StringUtil.getScheduleTime(saturday));
        }

        AttendanceSchedule sunday = group.getSunday();
        if(sunday != null && (sunday.getRest() == null || !sunday.getRest())){
            model.addAttribute("sunday", sunday.getName()+":"+StringUtil.getScheduleTime(sunday));
        }
        return "attendance/group_detail";
    }
}
