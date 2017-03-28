package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.dao.attendance.LevelTypeDao;
import com.victory.ehrsystem.entity.attendance.LevelRecord;
import com.victory.ehrsystem.entity.attendance.LevelType;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.attendance.OverTimeSetting;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.attendance.LevelRecordService;
import com.victory.ehrsystem.service.attendance.OverTimeRecordService;
import com.victory.ehrsystem.service.attendance.OverTimeSettingService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:49
 */
@Controller
@RequestMapping(value = "/attendancesetting")
public class AttendanceSettingController {

    @Autowired
    private OverTimeSettingService overTimeSettingService;

    @RequiresPermissions(value = "AttendanceSetting:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        OverTimeSetting setting = overTimeSettingService.findOne(OverTimeSetting.class, 1);
        model.addAttribute("overTime", setting);
        return "attendance/attendanceSetting";
    }

    @RequiresPermissions(value = "AttendanceSetting:update")
    @RequestMapping(value = "/overtime")
    public @ResponseBody JsonVo updateOverTime(int calculateType,int offsetTimeUp,int offsetTimeDown){
        OverTimeSetting setting = overTimeSettingService.findOne(OverTimeSetting.class, 1);
        setting.setCalculateType(calculateType);
        setting.setOffsetTimeUp((long) (offsetTimeUp*60000));
        setting.setOffsetTimeDown((long) (offsetTimeDown*60000));
        overTimeSettingService.update(OverTimeSetting.class, setting);
        JsonVo jsonVo = new JsonVo().setStatus(true).setMsg("更新完成");
        return jsonVo;
    }
}
