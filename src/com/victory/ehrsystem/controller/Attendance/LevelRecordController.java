package com.victory.ehrsystem.controller.Attendance;

import com.victory.ehrsystem.dao.attendance.LevelRecordDao;
import com.victory.ehrsystem.dao.attendance.LevelTypeDao;
import com.victory.ehrsystem.entity.attendance.LevelRecord;
import com.victory.ehrsystem.entity.attendance.LevelType;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.attendance.LevelRecordService;
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
import java.util.Date;

/**
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:49
 */
@Controller
@RequestMapping(value = "/levelrecord")
public class LevelRecordController {

    @Autowired
    private LevelRecordService recordService;

    @Autowired
    private HrmResourceService resourceService;

    @Autowired
    private LevelTypeDao typeDao;


    @RequiresPermissions(value = "LevelRecord:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        String beginStr = DateUtil.getMonthFristDay().toString();
        String endStr = DateUtil.getToday().toString();
        model.addAttribute("beginDate", beginStr);
        model.addAttribute("endDate", endStr);
        model.addAttribute("per","LevelRecord");
        return "attendance/levelRecord";
    }

    @RequiresPermissions(value = "LevelRecord:view")
    @RequestMapping(value = "/list")
    public @ResponseBody PageInfo detail(HttpServletRequest request) {
        PageInfo pageInfo = recordService.findList(request);
        return pageInfo;
    }

    @RequiresPermissions(value = "LevelRecord:create")
    @RequestMapping(value = "/setting")
    public String modal_create(Model model){
        model.addAttribute("action","/levelrecord/edit");
        model.addAttribute("resourceUrl","/resource/modal/list/single");
        model.addAttribute("returnUrl","/levelrecord/setting.html");
        return "attendance/levelRecord_detail";
    }

    /**
     * 执行创建或者更新的操作
     * @param request
     * @return
     */
    @RequiresPermissions(value = "LevelRecord:update")
    @RequestMapping(value = "/edit")
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
    @RequestMapping(value = "/setting/{id}")
    public String modal_edit(@PathVariable int id, Model model) {
        LevelRecord record = recordService.findOne(LevelRecord.class, id);
        model.addAttribute("record", record);
        model.addAttribute("action","/overtime/edit");
        model.addAttribute("resourceUrl","/resource/modal/list/single");
        model.addAttribute("returnUrl","/levelrecord.html");
        return "attendance/levelRecord_detail";
    }

    /**
     * 执行删除操作
     * @param id
     * @return
     */
    @RequiresPermissions(value = "LevelRecord:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody JsonVo delete(@PathVariable int id) {
        recordService.delete(LevelRecord.class, id);
        JsonVo jsonVo = new JsonVo();
        jsonVo.setStatus(true).setMsg("删除成功");
        return jsonVo;
    }

    public JsonVo packagingByRequest(HttpServletRequest request){
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

        Date beginDate = DateUtil.parseUtilDate(beginDateStr);
        Date endDate = DateUtil.parseUtilDate(endDateStr);
        LevelRecord record = null;
        String msg = "";
        if ("".equals(id)) {
            record = new LevelRecord();
            msg = "新增成功";
        }else{
            record = recordService.findOne(LevelRecord.class,Integer.parseInt(id));
            msg = "更新成功";
        }
        HrmResource resource = resourceService.findOne(HrmResource.class, Integer.parseInt(resourceStr));
        record.setResource(resource);
        record.setDate(beginDate);
        record.setEndDate(endDate);
        record.setReason(reason);
        record.setStatus(OverTimeRecord.Status.normal);
        record.setType(typeDao.get(LevelType.class,Integer.parseInt(type)));
        record.setCount(DateUtil.getTimeInterval(beginDate,endDate));

        if (id.equals("")) {
            recordService.save(record);
        }else{
            recordService.update(LevelRecord.class, record);
        }
        jsonVo.setStatus(true).setMsg(msg);
        return jsonVo;
    }
}
