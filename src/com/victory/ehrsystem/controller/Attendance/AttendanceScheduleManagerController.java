package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceScheduleInfo;
import com.victory.ehrsystem.service.attendance.AttendanceScheduleInfoService;
import com.victory.ehrsystem.vo.JsonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ajkx
 * Date: 2017/1/6.
 * Time:19:37
 */
@Controller
@RequestMapping(value = "/scheduleinfo")
public class AttendanceScheduleManagerController {

    @Autowired
    private AttendanceScheduleInfoService ScheduleInfoService;

    @RequiresPermissions(value = "schedulemanage:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "attendance/schedulemanage";
    }

    /**
     * 根据日期和选择的人员查找排班情况表
     * @return
     */
    @RequiresPermissions(value = "schedulemanage:view")
    @RequestMapping(value = "/search")
    public String search(){
        return "";
    }

    /**
     * 编辑人员的每天排班
     * @return
     */
    @RequiresPermissions(value = "schedulemanage:create")
    @RequestMapping(value = "/edit")
    public @ResponseBody JsonVo editSchedule(){
        JsonVo jsonVo = new JsonVo();
        return jsonVo;
    }

}
