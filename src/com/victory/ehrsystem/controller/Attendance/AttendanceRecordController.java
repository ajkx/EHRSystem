package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.attendance.LevelRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.attendance.AttendanceDetailService;
import com.victory.ehrsystem.service.attendance.AttendanceRecordService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
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
import java.sql.Date;
import java.sql.Time;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/record")
public class AttendanceRecordController {

    @Autowired
    private AttendanceRecordService recordService;

    @Autowired
    private HrmResourceService resourceService;

    //考勤机记录主页
    @RequiresPermissions(value = "MachineRecord:view")
    @RequestMapping(value = "/machine")
    public String machineIndex(Model model) {
        String beginStr = DateUtil.getMonthFristDay().toString();
        String endStr = DateUtil.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        return "attendance/machineRecord";
    }

    //考勤机记录列表
    @RequiresPermissions(value = "MachineRecord:view")
    @RequestMapping(value = "/list/machine")
    public @ResponseBody PageInfo machine(HttpServletRequest request) {
        //查询考勤机的
        PageInfo pageInfo = recordService.findByPage(request,1);
        return pageInfo;
    }

    //签卡记录主页
    @RequiresPermissions(value = "ManualRecord:view")
    @RequestMapping(value = "/manual")
    public String manualIndex(Model model) {
        String beginStr = DateUtil.getMonthFristDay().toString();
        String endStr = DateUtil.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        model.addAttribute("per","ManualRecord");
        return "attendance/manualRecord";
    }

    //签卡记录列表
    @RequiresPermissions(value = "ManualRecord:view")
    @RequestMapping(value = "/list/manual")
    public @ResponseBody PageInfo manual(HttpServletRequest request) {
        //查询考勤机的
        PageInfo pageInfo = recordService.findByPage(request,2);
        return pageInfo;
    }

    //签卡登记
    @RequiresPermissions(value = "ManualRecord:create")
    @RequestMapping(value = "/manual/setting")
    public String manualSetting(Model model){
        model.addAttribute("action", "/record/manual/edit");
        model.addAttribute("resourceUrl","/resource/modal/list/single");
        model.addAttribute("returnUrl", "/record/manual/setting.html");
        return "attendance/manual_detail";
    }

    /**
     * 执行创建或者更新的操作
     * @param request
     * @return
     */
    @RequiresPermissions(value = "ManualRecord:update")
    @RequestMapping(value = "/manual/edit")
    public @ResponseBody JsonVo createOrUpdate(HttpServletRequest request){
        return packagingByRequest(request);
    }

    /**
     * 返回更新数据的页面
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "LevelRecord:update")
    @RequestMapping(value = "/manual/setting/{id}")
    public String modal_edit(@PathVariable int id, Model model) {
        AttendanceRecord record = recordService.findOne(AttendanceRecord.class, id);
        model.addAttribute("record", record);
        model.addAttribute("action","/record/manual/edit");
        model.addAttribute("resourceUrl","/resource/modal/list/single");
        model.addAttribute("returnUrl", "/record/manual.html");
        return "attendance/manual_detail";
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "LevelRecord:delete")
    @RequestMapping(value = "/manual/delete/{id}")
    public @ResponseBody JsonVo delete(@PathVariable int id) {
        recordService.delete(AttendanceRecord.class, id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }


    public JsonVo packagingByRequest(HttpServletRequest request){
        String id = StringUtil.nullString(request.getParameter("id"));
        String resourceStr = StringUtil.nullString(request.getParameter("resources"));
        String dateStr = StringUtil.nullString(request.getParameter("date"));
        String timeStr = StringUtil.nullString(request.getParameter("time"));
        String reason = StringUtil.nullString(request.getParameter("reason"));

        AttendanceRecord record = null;
        String msg = "";
        if ("".equals(id)) {
            record = new AttendanceRecord();
            msg = "新增成功";
        }else{
            record = recordService.findOne(AttendanceRecord.class,Integer.parseInt(id));
            msg = "更新成功";
        }

        HrmResource resource = resourceService.findOne(HrmResource.class, Integer.parseInt(resourceStr));
        Date date = DateUtil.parseDateByDay(dateStr);
        Time time = Time.valueOf(timeStr + ":00");

        record.setResource(resource);
        record.setDate(date);
        record.setPunchTime(time);
        record.setReason(reason);
        record.setType(2);

        if (id.equals("")) {
            recordService.save(record);
        }else{
            recordService.update(AttendanceRecord.class, record);
        }

        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg(msg);
        return jsonVo;
    }
}
