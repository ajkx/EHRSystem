package com.victory.ehrsystem.service.attendance;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private HrmResourceDao resourceDao;

    public PageInfo findByPageDetail(HttpServletRequest request){
        int pageNo = Integer.parseInt(request.getParameter("cPage"));
        int pageSize = Integer.parseInt(request.getParameter("pSize"));
        String beginStr = StringUtil.nullString(request.getParameter("beginDate"));
        String endStr = StringUtil.nullString(request.getParameter("endDate"));
        String resources = StringUtil.nullString(request.getParameter("resources"));
        Date beginDate = null;
        Date endDate = null;
        List<HrmResource> resourceList = null;
        if ("".equals(beginStr) || "".equals(endStr)) {
            endDate = DateUtil.getToday();
            beginDate = DateUtil.getMonthFristDay();
        }else{
            beginDate = DateUtil.parseDate(beginStr);
            endDate = DateUtil.parseDate(endStr);
        }
        if(!"".equals(resources)){
            String[] array = resources.split(",");
            for (String temp : array) {
                HrmResource resource = resourceDao.get(HrmResource.class, Integer.parseInt(temp));
                resourceList.add(resource);
            }
        }
        return convertDate(detailDao.findByPage(beginDate, endDate, resourceList,pageNo,pageSize));
    }
//    @Override
//    public PageInfo findByPage(Class<AttendanceDetail> entityClazz, HttpServletRequest request) {
//        PageInfo info = super.findByPage(entityClazz, request);
//        List<AttendanceDetail> list = info.getData();
//        List<Map<String, Object>> mapList = new ArrayList();
//        for (AttendanceDetail detail : list) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", detail.getId());
//            map.put("name", detail.getResourceId().getName());
//            map.put("department", detail.getResourceId().getDepartment().getName());
//            map.put("workCode", detail.getResourceId().getWorkCode());
//            map.put("date", detail.getDate());
//            map.put("schedule", detail.getSchedule().getName());
//
//            map.put("setting_first_up", StringUtil.nullString(detail.getSchedule().getFirst_time_up()));
//            map.put("actual_first_up", StringUtil.nullString(detail.getFirst_time_up()));
//            map.put("setting_first_down", StringUtil.nullString(detail.getSchedule().getFirst_time_down()));
//            map.put("actual_first_down", StringUtil.nullString(detail.getFirst_time_down()));
//
//            map.put("setting_second_up", StringUtil.nullString(detail.getSchedule().getSecond_time_up()));
//            map.put("actual_second_up", StringUtil.nullString(detail.getSecond_time_up()));
//            map.put("setting_second_down", StringUtil.nullString(detail.getSchedule().getSecond_time_down()));
//            map.put("actual_second_down", StringUtil.nullString(detail.getSecond_time_down()));
//
//            map.put("setting_third_up", StringUtil.nullString(detail.getSchedule().getThird_time_up()));
//            map.put("actual_third_up", StringUtil.nullString(detail.getThird_time_up()));
//            map.put("setting_third_down", StringUtil.nullString(detail.getSchedule().getThird_time_down()));
//            map.put("actual_third_down", StringUtil.nullString(detail.getThird_time_down()));
//
//            map.put("attendanceType", detail.getAttendanceType().getName());
//            map.put("lateTime", StringUtil.nullLong(detail.getLateTime()));
//            map.put("earlyTime", StringUtil.nullLong(detail.getEarlyTime()));
//            map.put("absenteeismTime", StringUtil.nullLong(detail.getAbsenteeismTime()));
//            map.put("ot_normal", StringUtil.nullLong(detail.getOvertime_normal()));
//            map.put("ot_weekend", StringUtil.nullLong(detail.getOvertime_weekend()));
//            map.put("ot_festival", StringUtil.nullLong(detail.getOvertime_festival()));
//            map.put("setting_time", StringUtil.nullFloat(detail.getShould_attendance_time()));
//            map.put("actual_time", StringUtil.nullFloat(detail.getActual_attendance_time()));
//            mapList.add(map);
//        }
//        info.setData(mapList);
//        return info;
//    }

    private PageInfo convertDate(PageInfo info) {
        List<AttendanceDetail> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (AttendanceDetail detail : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", detail.getId());
            map.put("name", detail.getResourceId().getName());
            map.put("department", detail.getResourceId().getDepartment().getName());
            map.put("workCode", detail.getResourceId().getWorkCode());
            map.put("date", detail.getDate());
            map.put("schedule", detail.getSchedule().getName());

            map.put("setting_first_up", StringUtil.nullString(detail.getSchedule().getFirst_time_up()));
            map.put("actual_first_up", StringUtil.nullString(detail.getFirst_time_up()));
            map.put("setting_first_down", StringUtil.nullString(detail.getSchedule().getFirst_time_down()));
            map.put("actual_first_down", StringUtil.nullString(detail.getFirst_time_down()));

            map.put("setting_second_up", StringUtil.nullString(detail.getSchedule().getSecond_time_up()));
            map.put("actual_second_up", StringUtil.nullString(detail.getSecond_time_up()));
            map.put("setting_second_down", StringUtil.nullString(detail.getSchedule().getSecond_time_down()));
            map.put("actual_second_down", StringUtil.nullString(detail.getSecond_time_down()));

            map.put("setting_third_up", StringUtil.nullString(detail.getSchedule().getThird_time_up()));
            map.put("actual_third_up", StringUtil.nullString(detail.getThird_time_up()));
            map.put("setting_third_down", StringUtil.nullString(detail.getSchedule().getThird_time_down()));
            map.put("actual_third_down", StringUtil.nullString(detail.getThird_time_down()));

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
