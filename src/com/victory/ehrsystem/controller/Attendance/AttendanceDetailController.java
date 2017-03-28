package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.AttendanceType;
import com.victory.ehrsystem.service.attendance.AttendanceDetailService;
import com.victory.ehrsystem.util.DateUtil;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/detail")
public class AttendanceDetailController {

    @Autowired
    private AttendanceDetailService detailService;

    @RequiresPermissions(value = "AttendanceDetail:view")
    @RequestMapping(value = "/detailList")
    public String detailList(Model model) {
        String beginStr = DateUtil.getMonthFristDay().toString();
        String endStr = DateUtil.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        return "attendance/attendanceDetail1";
    }

    @RequiresPermissions(value = "AttendanceDetail:view")
    @RequestMapping(value = "/list/detail")
    public @ResponseBody PageInfo detail(HttpServletRequest request) {
        PageInfo pageInfo = detailService.findByPageDetail(request);
        return pageInfo;
    }

    @RequiresPermissions(value = "AttendanceDetail:view")
    @RequestMapping(value = "/collectList")
    public String collectList(Model model) {
        String beginStr = DateUtil.getMonthFristDay().toString().substring(0,7);
        model.addAttribute("beginDate", beginStr);
        return "attendance/attendanceCollect";
    }

    @RequiresPermissions(value = "AttendanceDetail:view")
    @RequestMapping(value = "/list/collect")
    public @ResponseBody PageInfo collect(HttpServletRequest request) {
        PageInfo pageInfo = detailService.findByPageCollect(request);
        return pageInfo;
    }

    @RequiresPermissions(value = "AttendanceDetail:update")
    @RequestMapping(value = "update/{id}/{type}")
    public @ResponseBody JsonVo updateDetail(@PathVariable int id,@PathVariable int type){
        return detailService.updateDetail(id, type);
    }
}
