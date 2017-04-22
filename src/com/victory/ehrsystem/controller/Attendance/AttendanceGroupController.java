package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.attendance.AttendanceGroupService;
import com.victory.ehrsystem.service.attendance.AttendanceScheduleService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.util.StringUtil;
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
 * Created by ajkx on 2017/2/17.
 */
@Controller
@RequestMapping(value = "/group")
public class AttendanceGroupController {

    @Autowired
    private AttendanceGroupService groupService;

    @Autowired
    private AttendanceScheduleService scheduleService;

    @Autowired
    private HrmResourceService resourceService;

//    @Autowired
//    private AttendanceManager manager;
//    @RequestMapping(value="/test")
//    public void test(){
//        System.out.println("===========开始执行自动考勤===========");
//        manager.testAttendance();
//        System.out.println("===========结束自动考勤===========");
//    }

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
    @RequestMapping(value = "/setting")
    public String page_create(Model model)
    {
        model.addAttribute("action", "/group/create");
        return "attendance/group_detail";
    }


    /**
     * 返回更新页面
      * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "AttendanceGroup:update")
    @RequestMapping(value = "/setting/{id}")
    public String detail(@PathVariable int id,Model model){
        AttendanceGroup group = groupService.findOne(AttendanceGroup.class, id);


        model.addAttribute("group", group);
        model.addAttribute("action", "/group/update");

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


    /**
     * 执行创建或更新操作 根据是否有id判断,返回json信息
     * @param request
     * @return
     */
    @RequiresPermissions(value = "AttendanceGroup:update")
    @RequestMapping(value = "/edit")
    public @ResponseBody JsonVo createOrUpdate(HttpServletRequest request) {
        return packagingByRequest(request);
    }


    @RequiresPermissions(value = "AttendanceGroup:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody JsonVo delete(@PathVariable int id) {
        groupService.delete(AttendanceGroup.class, id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }

    //封装group
    public JsonVo packagingByRequest(HttpServletRequest request) {
        String id = StringUtil.nullString(request.getParameter("id"));
        String groupType = StringUtil.nullString(request.getParameter("groupType"));
        String resourceStr = request.getParameter("resources");
        AttendanceGroup group = null;
        String msg = "";
        //创建
        if(id.equals("")){
            group = new AttendanceGroup();
            msg = "新增成功";
        }
        //更新
        else{
            group = groupService.findOne(AttendanceGroup.class, Integer.parseInt(id));
            msg = "更新成功";
        }
        List<HrmResource> list = resourceService.splitForHrmResource(resourceStr);
        Set<HrmResource> set = new HashSet<>();
        set.addAll(list);
        group.setResources(set);
        group.setName(request.getParameter("name"));
        group.setDescription(request.getParameter("description"));
        switch (groupType){
            case "1":
                String mondayStr = request.getParameter("monday");
                String tuesdayStr = request.getParameter("tuesday");
                String wednesdayStr = request.getParameter("wednesday");
                String thursdayStr = request.getParameter("thursday");
                String fridayStr = request.getParameter("friday");
                String saturdayStr = request.getParameter("saturday");
                String sundayStr = request.getParameter("sunday");
                String isAuto = request.getParameter("isAuto");

                group.setAuto(isAuto.equals("0") ? false : true);
                group.setGroupType(1);

                AttendanceSchedule restSchedule = scheduleService.findRestSchedule();
                if(mondayStr.equals("")){
                    group.setMonday(restSchedule);
                }else{
                    group.setMonday(scheduleService.findOne(AttendanceSchedule.class,Integer.parseInt(mondayStr)));
                }

                if(tuesdayStr.equals("")){
                    group.setTuesday(restSchedule);
                }else{
                    group.setTuesday(scheduleService.findOne(AttendanceSchedule.class,Integer.parseInt(tuesdayStr)));
                }

                if(wednesdayStr.equals("")){
                    group.setWednesday(restSchedule);
                }else{
                    group.setWednesday(scheduleService.findOne(AttendanceSchedule.class,Integer.parseInt(wednesdayStr)));
                }

                if(thursdayStr.equals("")){
                    group.setThursday(restSchedule);
                }else{
                    group.setThursday(scheduleService.findOne(AttendanceSchedule.class,Integer.parseInt(thursdayStr)));
                }

                if(fridayStr.equals("")){
                    group.setFriday(restSchedule);
                }else{
                    group.setFriday(scheduleService.findOne(AttendanceSchedule.class,Integer.parseInt(fridayStr)));
                }

                if(saturdayStr.equals("")){
                    group.setSaturday(restSchedule);
                }else{
                    group.setSaturday(scheduleService.findOne(AttendanceSchedule.class,Integer.parseInt(saturdayStr)));
                }

                if(sundayStr.equals("")){
                    group.setSunday(restSchedule);
                }else{
                    group.setSunday(scheduleService.findOne(AttendanceSchedule.class,Integer.parseInt(sundayStr)));
                }
                break;
            case "2":
                group.setGroupType(2);
                break;
            case "3":
                group.setGroupType(3);
                break;
        }
        if (id.equals("")) {
            groupService.save(group);
        }else{
            groupService.update(AttendanceGroup.class, group);
        }

        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg(msg);
        return jsonVo;
    }



}
