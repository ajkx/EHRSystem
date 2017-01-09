package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.AttendanceType;
import com.victory.ehrsystem.entity.hrm.HrmEducationLevel;
import com.victory.ehrsystem.service.attendance.AttendanceScheduleService;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.ScheduleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @RequiresPermissions(value = "AttendanceSchedule:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<AttendanceSchedule> list = scheduleService.findAll(AttendanceSchedule.class);
        Map<Integer,LinkedHashMap<String, String>> map = new HashMap<Integer,LinkedHashMap<String,String>>();
        for (AttendanceSchedule schedule : list) {
            LinkedHashMap<String,String> tempmap = new LinkedHashMap<>();
            tempmap.put("班次名称", schedule.getName());
//            tempmap.put("详述", schedule.getDescription());
            map.put(schedule.getId(),tempmap);
        }
        model.addAttribute("topic","考勤班次");
        model.addAttribute("simplename","班次");
        model.addAttribute("url","/schedule");
        model.addAttribute("map",map);
        return "topic";
    }

    @RequiresPermissions(value = "AttendanceSchedule:create")
    @RequestMapping(value = "/edit")
    public String modal_create(Model model){
        model.addAttribute("topic","新增班次");
        model.addAttribute("action","/schedule/create");
        return "modal/attendance/schedule";
    }

    @RequiresPermissions(value = "AttendanceSchedule:create")
    @RequestMapping(value = "/create")
    public @ResponseBody JsonVo create(ScheduleVo scheduleVo){
        System.out.println(scheduleVo.toString());
        AttendanceSchedule schedule = new AttendanceSchedule();
        schedule.setName(scheduleVo.getName());
        schedule.setSimplename(scheduleVo.getName().substring(0,1));
        schedule.setScheduleType(scheduleVo.getScheduleType());
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

    @RequiresPermissions(value = "AttendanceSchedule:update")
    @RequestMapping(value = "/{id}")
    public String modal_edit(@PathVariable int id, Model model) {
        AttendanceSchedule schedule = scheduleService.findOne(AttendanceSchedule.class, id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("topic","编辑班次");
        model.addAttribute("action","/schedule/update");
        return "modal/attendance/schedule";
    }

    @RequiresPermissions(value = "AttendanceSchedule:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(){
        JsonVo jsonVo = new JsonVo();
        return jsonVo;
    }
}
