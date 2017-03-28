package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.dao.attendance.AttendanceTypeDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2017/3/4.
 * Time:9:51
 */
@Service
public class AttendanceDetailService extends BaseService<AttendanceDetail>{

    @Autowired
    private AttendanceDetailDao detailDao;

    @Autowired
    private HrmResourceService resourceService;

    @Autowired
    private AttendanceManager attendanceManager;

    @Autowired
    private AttendanceTypeDao typeDao;

    public JsonVo updateDetail(int id, int type) {
        AttendanceDetail detail = findOne(AttendanceDetail.class, id);
        JsonVo jsonVo = new JsonVo();
        if (detail == null) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("该操作失效！");
        }else{
            AttendanceSchedule schedule = detail.getSchedule();
            switch (type) {
                case 1:
                    detail.setFirst_time_up(schedule.getFirst_time_up());
                    detail.setFirstUpType(typeDao.getNormalType());
                    jsonVo.put("time",schedule.getFirst_time_up());
                    break;
                case 2:
                    detail.setFirst_time_down(schedule.getFirst_time_down());
                    detail.setFirstDownType(typeDao.getNormalType());
                    jsonVo.put("time",schedule.getFirst_time_down());
                    break;
                case 3:
                    detail.setSecond_time_up(schedule.getSecond_time_up());
                    detail.setSecondUpType(typeDao.getNormalType());
                    jsonVo.put("time",schedule.getSecond_time_up());
                    break;
                case 4:
                    detail.setFirst_time_down(schedule.getSecond_time_down());
                    detail.setSecondDownType(typeDao.getNormalType());
                    jsonVo.put("time",schedule.getSecond_time_down());
                    break;
                case 5:
                    detail.setThird_time_up(schedule.getThird_time_up());
                    detail.setThirdUpType(typeDao.getNormalType());
                    jsonVo.put("time",schedule.getThird_time_up());
                    break;
                case 6:
                    detail.setThird_time_down(schedule.getThird_time_down());
                    detail.setThirdDownType(typeDao.getNormalType());
                    jsonVo.put("time",schedule.getThird_time_down());
                    break;
            }
            attendanceManager.calculateTime(detail);
            jsonVo.setStatus(true).setMsg("修改成功");

        }
        return jsonVo;
    }
    //明细表的查询分页
    public PageInfo findByPageDetail(HttpServletRequest request){
//        int pageNo = Integer.parseInt(request.getParameter("cPage"));
//        int pageSize = Integer.parseInt(request.getParameter("pSize"));
//        String beginStr = StringUtil.nullString(request.getParameter("beginDate"));
//        String endStr = StringUtil.nullString(request.getParameter("endDate"));
//        String resources = StringUtil.nullString(request.getParameter("resources"));
//        Date beginDate = null;
//        Date endDate = null;
//        List<HrmResource> resourceList = null;
//        //如果开始或结束日期为空，则取当前月的第一天和今天
//        if ("".equals(beginStr) || "".equals(endStr)) {
//            endDate = DateUtil.getToday();
//            beginDate = DateUtil.getMonthFristDay();
//        }else{
//            beginDate = DateUtil.parseDateByDay(beginStr);
//            endDate = DateUtil.parseDateByDay(endStr);
//        }
//        //如果人员条件不为空，则解析前台传来的resources字段为Hrmresource集合
//        if(!"".equals(resources)){
//            resourceList = resourceService.splitForHrmResource(resources);
//            Set<HrmResource> resourceSet = new HashSet<>(resourceList);
//            resourceList.clear();
//            resourceList.addAll(resourceSet);
//        }
//        return convertData(detailDao.findDetail(beginDate, endDate, resourceList,pageNo,pageSize));
        return convertData(list(AttendanceDetail.class, request, resourceService));
    }

    //汇总的查询分页
    public PageInfo findByPageCollect(HttpServletRequest request) {
        int pageNo = Integer.parseInt(request.getParameter("cPage"));
        int pageSize = Integer.parseInt(request.getParameter("pSize"));
        String beginStr = StringUtil.nullString(request.getParameter("beginDate"));
        String endStr = StringUtil.nullString(request.getParameter("endDate"));
        String resources = StringUtil.nullString(request.getParameter("resources"));
        Date beginDate = null;
        Date endDate = DateUtil.getMonthLastDay(null);
        List<HrmResource> resourceList = null;
        if ("".equals(beginStr)) {
            beginDate = DateUtil.getMonthFristDay();
        }else{
            beginDate = DateUtil.parseDateByMonth(beginStr);
            endDate = DateUtil.getMonthLastDay(beginDate);
            System.out.println(endDate);
        }
        if(!"".equals(resources)){
            resourceList = resourceService.splitForHrmResource(resources);
            Set<HrmResource> resourceSet = new HashSet<>(resourceList);
            resourceList.clear();
            resourceList.addAll(resourceSet);
        }
        return convertDateByCollect(detailDao.findCollect(beginDate, endDate, resourceList, pageNo, pageSize));
    }

    //返回值组装格式
    private PageInfo convertDateByCollect(PageInfo info){
        List<Object[]> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Object[] obj : list) {
            Map<String, Object> map = new HashMap<>();
            HrmResource resource = (HrmResource) obj[16];
            map.put("name",resource.getName());
            map.put("department",resource.getDepartment().getName());
            map.put("workCode",resource.getWorkCode());
            map.put("should_attendance",StringUtil.nullLong(obj[0]));
            map.put("actual_attendance",StringUtil.nullDouble(obj[1]));
            map.put("should_attendance_time",StringUtil.nullLong(obj[2]));
            map.put("actual_attendance_time",StringUtil.nullLong(obj[3]));
            map.put("late_count",StringUtil.nullLong(obj[4]));
            map.put("late_time",StringUtil.nullLong(obj[5]));
            map.put("early_count",StringUtil.nullLong(obj[6]));
            map.put("early_time",StringUtil.nullLong(obj[7]));
            map.put("absenteeism_count",StringUtil.nullLong(obj[8]));
            map.put("absenteeism_time",StringUtil.nullLong(obj[9]));
            map.put("ot_normal",StringUtil.nullLong(obj[10]));
            map.put("ot_weekend",StringUtil.nullLong(obj[11]));
            map.put("ot_festival",StringUtil.nullLong(obj[12]));
            map.put("leave_personal",StringUtil.nullLong(obj[13]));
            map.put("leave_rest",StringUtil.nullLong(obj[14]));
            map.put("leave_business",StringUtil.nullLong(obj[15]));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
    private PageInfo convertData(PageInfo info) {
        List<AttendanceDetail> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (AttendanceDetail detail : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", detail.getId());
            map.put("name", detail.getResource().getName());
            map.put("department", detail.getResource().getDepartment().getName());
            map.put("workCode", detail.getResource().getWorkCode());
            map.put("date", detail.getDate());
            map.put("schedule", detail.getSchedule().getName());

            map.put("setting_first_up", StringUtil.nullString(detail.getSchedule().getFirst_time_up()));
            map.put("actual_first_up", StringUtil.nullString(detail.getFirst_time_up()));
            map.put("first_up_type", detail.getFirstUpType() == null ? "" : detail.getFirstUpType().getName());
            map.put("setting_first_down", StringUtil.nullString(detail.getSchedule().getFirst_time_down()));
            map.put("actual_first_down", StringUtil.nullString(detail.getFirst_time_down()));
            map.put("first_down_type", detail.getFirstDownType() == null ? "" : detail.getFirstDownType().getName());


            map.put("setting_second_up", StringUtil.nullString(detail.getSchedule().getSecond_time_up()));
            map.put("actual_second_up", StringUtil.nullString(detail.getSecond_time_up()));
            map.put("second_up_type", detail.getSecondUpType() == null ? "" : detail.getSecondUpType().getName());
            map.put("setting_second_down", StringUtil.nullString(detail.getSchedule().getSecond_time_down()));
            map.put("actual_second_down", StringUtil.nullString(detail.getSecond_time_down()));
            map.put("second_down_type", detail.getSecondDownType() == null ? "" : detail.getSecondDownType().getName());

            map.put("setting_third_up", StringUtil.nullString(detail.getSchedule().getThird_time_up()));
            map.put("actual_third_up", StringUtil.nullString(detail.getThird_time_up()));
            map.put("third_up_type", detail.getThirdUpType() == null ? "" : detail.getThirdUpType().getName());
            map.put("setting_third_down", StringUtil.nullString(detail.getSchedule().getThird_time_down()));
            map.put("actual_third_down", StringUtil.nullString(detail.getThird_time_down()));
            map.put("third_down_type", detail.getThirdDownType() == null ? "" : detail.getThirdDownType().getName());

            map.put("attendanceType", detail.getAttendanceType().getName());
            map.put("lateTime", StringUtil.nullLong(detail.getLateTime()));
            map.put("earlyTime", StringUtil.nullLong(detail.getEarlyTime()));
            map.put("absenteeismTime", StringUtil.nullLong(detail.getAbsenteeismTime()));
            map.put("ot_normal", StringUtil.nullLong(detail.getOvertime_normal()));
            map.put("ot_weekend", StringUtil.nullLong(detail.getOvertime_weekend()));
            map.put("ot_festival", StringUtil.nullLong(detail.getOvertime_festival()));
            map.put("setting_time", StringUtil.nullLong(detail.getShould_attendance_time()));
            map.put("actual_time", StringUtil.nullLong(detail.getActual_attendance_time()));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
