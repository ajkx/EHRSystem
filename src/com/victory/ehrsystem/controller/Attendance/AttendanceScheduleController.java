package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.service.attendance.AttendanceScheduleService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
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
        convertAttendanceSchedule(schedule, scheduleVo);
        scheduleService.save(schedule);
        JsonVo json = new JsonVo();
        json.setStatus(true).setMsg("新增成功");
        return json;
    }

    @RequiresPermissions(value = "schedule:update")
    @RequestMapping(value = "/{id}")
    public String modal_edit(@PathVariable int id, Model model) {
        AttendanceSchedule schedule = scheduleService.findOne(AttendanceSchedule.class, id);
        Set<AttendanceGroup> groups = new HashSet<>();
        groups.addAll(schedule.getMondays());
        groups.addAll(schedule.getTuesdays());
        groups.addAll(schedule.getWednesdays());
        groups.addAll(schedule.getThursdays());
        groups.addAll(schedule.getFridays());
        groups.addAll(schedule.getSaturdays());
        groups.addAll(schedule.getSundays());
        StringBuilder sb = new StringBuilder();
        if(groups.size() > 0){
            for (AttendanceGroup group : groups) {
                sb.append(group.getName());
                sb.append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            model.addAttribute("size", groups.size());
            model.addAttribute("group", sb.toString());
        }
        model.addAttribute("schedule", schedule);
        model.addAttribute("topic","编辑班次");
        model.addAttribute("action","/schedule/update");
        return "modal/attendance/schedule";
    }

    @RequiresPermissions(value = "schedule:update")
    @RequestMapping(value = "/update")
    public @ResponseBody JsonVo update(ScheduleVo scheduleVo){
        AttendanceSchedule schedule = scheduleService.findOne(AttendanceSchedule.class, scheduleVo.getId());
        convertAttendanceSchedule(schedule, scheduleVo);
        scheduleService.update(AttendanceSchedule.class,schedule);
        JsonVo json = new JsonVo();
        json.setStatus(true).setMsg("修改成功");
        return json;
    }


    @RequiresPermissions(value = "schedule:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody JsonVo delete(@PathVariable int id) {
        AttendanceSchedule schedule = scheduleService.findOne(AttendanceSchedule.class, id);
        Set<AttendanceGroup> groups = new HashSet<>();
        groups.addAll(schedule.getMondays());
        groups.addAll(schedule.getTuesdays());
        groups.addAll(schedule.getWednesdays());
        groups.addAll(schedule.getThursdays());
        groups.addAll(schedule.getFridays());
        groups.addAll(schedule.getSaturdays());
        groups.addAll(schedule.getSundays());
        StringBuilder sb = new StringBuilder();
        JsonVo jsonVo = new JsonVo();
        if(groups.size() > 0){
            for (AttendanceGroup group : groups) {
                sb.append(group.getName());
                sb.append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            jsonVo.setStatus(false).setMsg("删除失败！该班次关联了考勤组" + sb);

        }else{
            scheduleService.delete(AttendanceSchedule.class, id);
            jsonVo.setStatus(true).setMsg("删除成功");
        }
        return jsonVo;
    }

    @RequiresPermissions(value = "schedule:view")
    @RequestMapping(value = "/modal/list/{type}")
    public String modal_list(@PathVariable String type, Model model) {
        List<AttendanceSchedule> list = scheduleService.findAll(AttendanceSchedule.class);
        List<Map<String, String>> mapList = new ArrayList<>();
        for (AttendanceSchedule schedule : list) {
            Map<String, String> temp = new HashMap<>();
            if(schedule.getRest() != null && schedule.getRest())continue;
            temp.put("id", schedule.getId()+"");
            temp.put("name", schedule.getName());
            temp.put("time", StringUtil.getScheduleTime(schedule));
            mapList.add(temp);
        }
        model.addAttribute("list", mapList);
        if(type.equals("multi")){
            return "modal/attendance/scheduleMultiList";
        }else{
            return "modal/attendance/scheduleList";
        }

    }

    private AttendanceSchedule convertAttendanceSchedule(AttendanceSchedule schedule,ScheduleVo scheduleVo){
        long attendanceTime = 0;
        schedule.setName(scheduleVo.getName());
        schedule.setSimplename(scheduleVo.getName().substring(0,1));
        schedule.setScheduleType(scheduleVo.getScheduleType());
        schedule.setRest(false);
        schedule.setPunch(scheduleVo.getIsPunch() == 1 ? true : false);
        System.out.println(scheduleVo.getFirst_up());

        schedule.setFirst_time_up(Time.valueOf(scheduleVo.getFirst_up()));
        schedule.setFirst_time_down(Time.valueOf(scheduleVo.getFirst_down()));
//        attendanceTime = DateUtil.getTimeInterval(schedule.getFirst_time_up(), schedule.getFirst_time_down());
        if(scheduleVo.getScheduleType() > 1){
            schedule.setSecond_time_up(Time.valueOf(scheduleVo.getSecond_up()));
            schedule.setSecond_time_down(Time.valueOf(scheduleVo.getSecond_down()));
//            attendanceTime += DateUtil.getTimeInterval(schedule.getSecond_time_up(), schedule.getSecond_time_down());
            if(scheduleVo.getScheduleType() > 2){
                schedule.setThird_time_up(Time.valueOf(scheduleVo.getThird_up()));
                schedule.setThird_time_down(Time.valueOf(scheduleVo.getThird_down()));
//                attendanceTime += DateUtil.getTimeInterval(schedule.getThird_time_up(), schedule.getThird_time_down());
            }
        }
        schedule.setAttendanceTime(scheduleVo.getAttendanceTime()*1000);
        schedule.setAcrossDay(scheduleVo.getAcrossDay() != null && scheduleVo.getAcrossDay() == 1 ? true : false);
        schedule.setScope_up(scheduleVo.getScope_up()*60*1000);
        schedule.setScope_down(scheduleVo.getScope_down()*60*1000);
        schedule.setOffsetTime(scheduleVo.getOffsetTime()*60*1000);
        schedule.setDescription(scheduleVo.getDescription());
        return schedule;
    }
}
