package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.attendance.AttendanceRecordService;
import com.victory.ehrsystem.service.attendance.OverTimeRecordService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
import com.victory.ehrsystem.vo.ScheduleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:10:33
 */
@Controller
@RequestMapping(value = "/overtime")
public class OverTimeRecordController {

    @Autowired
    private OverTimeRecordService recordService;

    @Autowired
    private HrmResourceService resourceService;

    @RequiresPermissions(value = "OverTime:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        String beginStr = DateUtil.getMonthFristDay().toString();
        String endStr = DateUtil.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        model.addAttribute("per","OverTime");
        return "attendance/overTime";
    }

    /**
     * 返回列表
     * @param request
     * @return
     */
    @RequiresPermissions(value = "OverTime:view")
    @RequestMapping(value = "/list")
    public @ResponseBody PageInfo detail(HttpServletRequest request) {
        PageInfo pageInfo = recordService.findList(request);
        return pageInfo;
    }

    /**
     * 返回批量创建页面
     * @param model
     * @return
     */
    @RequiresPermissions(value = "OverTime:create")
    @RequestMapping(value = "/setting/batch")
    public String modal_create_batch(Model model){
        model.addAttribute("action","/overtime/edit/batch");
        model.addAttribute("resourceUrl","/resource/modal/list");
        return "attendance/overtime_detail";
    }

    /**
     * 返回创建页面
     * @param model
     * @return
     */
    @RequiresPermissions(value = "OverTime:create")
    @RequestMapping(value = "/setting")
    public String modal_create(Model model){
        model.addAttribute("action","/overtime/edit");
        model.addAttribute("resourceUrl","/resource/modal/list/single");
        return "attendance/overtime_detail";
    }

    /**
     * 返回更新数据的页面
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "OverTime:update")
    @RequestMapping(value = "/setting/{id}")
    public String modal_edit(@PathVariable int id, Model model) {
        OverTimeRecord record = recordService.findOne(OverTimeRecord.class, id);
        model.addAttribute("record", record);
        model.addAttribute("action","/overtime/edit");
        model.addAttribute("resourceUrl","/resource/modal/list/single");
        return "attendance/overtime_detail";
    }

    /**
     * 执行创建或者更新的操作
     * @param request
     * @return
     */
    @RequiresPermissions(value = "OverTime:update")
    @RequestMapping(value = "/edit")
    public @ResponseBody JsonVo createOrUpdate(HttpServletRequest request){
        return packagingByRequest(request,false);
    }

    /**
     * 批量创建
     * @param request
     * @return
     */
    @RequiresPermissions(value = "OverTime:update")
    @RequestMapping(value = "/edit/batch")
    public @ResponseBody JsonVo createByBatch(HttpServletRequest request){
        return packagingByRequest(request,true);
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "OverTime:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody JsonVo delete(@PathVariable int id) {
        recordService.delete(OverTimeRecord.class, id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }

//    /**
//     * 修改异常
//     * @param id
//     * @return
//     */
//    @RequiresPermissions(value = "OverTime:update")
//    @RequestMapping(value = "update/{id}")
//    public @ResponseBody JsonVo updateDetail(@PathVariable int id){
//        return recordService.updateRecord(id);
//    }

    public JsonVo packagingByRequest(HttpServletRequest request,boolean isBatch) {
        String id = StringUtil.nullString(request.getParameter("id"));
        String beginDateStr = StringUtil.nullString(request.getParameter("beginDate"));
        String endDateStr = StringUtil.nullString(request.getParameter("endDate"));
        JsonVo jsonVo = new JsonVo();
        if("".equals(beginDateStr) || "".equals(endDateStr) || endDateStr.compareTo(beginDateStr) <= 0){
            jsonVo.setStatus(false).setMsg("时间不合法！");
            return jsonVo;
        }
        String resourceStr = StringUtil.nullString(request.getParameter("resources"));
        String reason = StringUtil.nullString(request.getParameter("reason"));
        String type = StringUtil.nullString(request.getParameter("type"));

        //转换加班日期
        Date beginDate = DateUtil.parseUtilDate(beginDateStr);
        Date endDate = DateUtil.parseUtilDate(endDateStr);
        OverTimeRecord record = null;
        if(isBatch){
            String[] arrays = resourceStr.split(",");
            for (String temp : arrays) {
                if("".equals(temp)) continue;
                OverTimeRecord tempRecord = new OverTimeRecord();
                HrmResource resource = resourceService.findOne(HrmResource.class, Integer.parseInt(temp));
                tempRecord.setResource(resource);
                tempRecord.setDate(beginDate);
                tempRecord.setEndDate(endDate);
                tempRecord.setReason(reason);
                tempRecord.setStatus(OverTimeRecord.Status.normal);
                tempRecord.setType(Integer.parseInt(type));
                tempRecord.setCount(DateUtil.getTimeInterval(beginDate,endDate));
                recordService.save(tempRecord);
            }

            jsonVo.setStatus(true).setMsg("新增成功");
            return jsonVo;
        }else{
            String msg = "";
            if ("".equals(id)) {
                record = new OverTimeRecord();
                msg = "新增成功";
            }else{
                record = recordService.findOne(OverTimeRecord.class,Integer.parseInt(id));
                msg = "更新成功";
            }
            HrmResource resource = resourceService.findOne(HrmResource.class, Integer.parseInt(resourceStr));
            record.setResource(resource);
            record.setDate(beginDate);
            record.setEndDate(endDate);
            record.setReason(reason);
            record.setStatus(OverTimeRecord.Status.normal);
            record.setType(Integer.parseInt(type));
            record.setCount(DateUtil.getTimeInterval(beginDate,endDate));


            if (id.equals("")) {
                recordService.checkRepeat(record,1);
                recordService.save(record);
            }else{
                recordService.checkRepeat(record,2);
                recordService.update(OverTimeRecord.class, record);
            }
            jsonVo.setStatus(true).setMsg(msg);
            return jsonVo;
        }
    }
}
