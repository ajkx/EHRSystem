package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.service.attendance.AttendanceDetailService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.vo.ColInfo;
import com.victory.ehrsystem.vo.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        String beginStr = DateUtil.getMonthFristDay().toString();
        String endStr = DateUtil.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        return "attendance/attendanceDetail";
    }

    @RequiresPermissions(value = "AttendanceDetail:view")
    @RequestMapping(value = "/list")
    public @ResponseBody PageInfo list(HttpServletRequest request) {
        PageInfo pageInfo = detailService.findByPageDetail(request);
        return pageInfo;
    }
}
