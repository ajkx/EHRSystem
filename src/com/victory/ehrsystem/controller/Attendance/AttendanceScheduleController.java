package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.service.attendance.AttendanceScheduleService;
import com.victory.ehrsystem.vo.ColInfo;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
import com.victory.ehrsystem.vo.ScheduleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2016/12/27.
 * Time:8:29
 */
@Controller
@RequestMapping(value = "/schedule")
public class AttendanceScheduleController {

    @Autowired
    private AttendanceScheduleService scheduleService;

    @RequiresPermissions(value = "schedule:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        List<ColInfo> colInfos = new ArrayList<>();
        colInfos.add(new ColInfo("name","班次名称"));
        colInfos.add(new ColInfo("time", "考勤时间"));
        colInfos.add(new ColInfo("description", "描述"));
        model.addAttribute("topic","考勤班次");
        model.addAttribute("simplename","班次");
        model.addAttribute("url","/schedule");
        model.addAttribute("col", colInfos);
        model.addAttribute("per", "schedule");
        return "topic";
    }

    @RequiresPermissions(value = "schedule:view")
    @RequestMapping(value = "/list")
    public @ResponseBody
    PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = scheduleService.findByPage(AttendanceSchedule.class,request);
        return pageInfo;
    }

    @RequiresPermissions(value = "schedule:create")
    @RequestMapping(value = "/edit")
    public String modal_create(Model model){
        model.addAttribute("topic","新增班次");
        model.addAttribute("action","/schedule/create");
        return "modal/attendance/schedule";
    }

    @RequiresPermissions(value = "schedule:create")
    @RequestMapping(value = "/create")
    public @ResponseBody JsonVo create(ScheduleVo scheduleVo){
        AttendanceSchedule schedule = new AttendanceSchedule();
        schedule.setName(scheduleVo.getName());
        schedule.setSimplename(scheduleVo.getName().substring(0,1));
        schedule.setScheduleType(scheduleVo.getScheduleType());
        schedule.setPunch(scheduleVo.getIsPunch() == 1 ? true : false);
        schedule.setFirst_time_up(Time.valueOf(scheduleVo.getFirst_up()));
        schedule.setFirst_time_down(Time.valueOf(scheduleVo.getFirst_down()));
        schedule.setSecond_time_up(Time.valueOf(scheduleVo.getSecond_up()));
        schedule.setSecond_time_down(Time.valueOf(scheduleVo.getSecond_down()));
        schedule.setThird_time_up(Time.valueOf(scheduleVo.getThird_up()));
        schedule.setThird_time_down(Time.valueOf(scheduleVo.getThird_down()));
        schedule.setAcrossDay(scheduleVo.getAcrossDay() != null && scheduleVo.getAcrossDay() == 1 ? true : false);
        schedule.setAttendanceTime(scheduleVo.getAttendanceTime());
        schedule.setScope_up(scheduleVo.getScope_up());
        schedule.setScope_down(scheduleVo.getScope_down());
        scheduleService.save(schedule);
        JsonVo json = new JsonVo();
        json.setStatus(true).setMsg("新增成功");
        return json;
    }

    @RequiresPermissions(value = "schedule:update")
    @RequestMapping(value = "/{id}")
    public String modal_edit(@PathVariable int id, Model model) {
        AttendanceSchedule schedule = scheduleService.findOne(AttendanceSchedule.class, id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("topic","编辑班次");
        model.addAttribute("action","/schedule/update");
        return "modal/attendance/schedule";
    }

    @RequiresPermissions(value = "schedule:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(ScheduleVo scheduleVo){
        AttendanceSchedule schedule = scheduleService.findOne(AttendanceSchedule.class, scheduleVo.getId());
        schedule.setName(scheduleVo.getName());
        schedule.setSimplename(scheduleVo.getName().substring(0,1));
        schedule.setScheduleType(scheduleVo.getScheduleType());
        schedule.setPunch(scheduleVo.getIsPunch() == 1 ? true : false);
        schedule.setFirst_time_up(Time.valueOf(scheduleVo.getFirst_up()));
        schedule.setFirst_time_down(Time.valueOf(scheduleVo.getFirst_down()));
        schedule.setSecond_time_up(Time.valueOf(scheduleVo.getSecond_up()));
        schedule.setSecond_time_down(Time.valueOf(scheduleVo.getSecond_down()));
        schedule.setThird_time_up(Time.valueOf(scheduleVo.getThird_up()));
        schedule.setThird_time_down(Time.valueOf(scheduleVo.getThird_down()));
        schedule.setAcrossDay(scheduleVo.getAcrossDay() != null && scheduleVo.getAcrossDay() == 1 ? true : false);
        schedule.setAttendanceTime(scheduleVo.getAttendanceTime());
        schedule.setScope_up(scheduleVo.getScope_up());
        schedule.setScope_down(scheduleVo.getScope_down());
        scheduleService.update(AttendanceSchedule.class,schedule);
        JsonVo json = new JsonVo();
        json.setStatus(true).setMsg("修改成功");
        return json;
    }
}
