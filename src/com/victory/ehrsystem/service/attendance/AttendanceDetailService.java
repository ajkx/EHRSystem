package com.victory.ehrsystem.service.attendance;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public PageInfo findByPage(Class<AttendanceDetail> entityClazz, HttpServletRequest request) {
        PageInfo info = super.findByPage(entityClazz, request);
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

            map.put("setting_first_up", detail.getSchedule().getFirst_time_up());
            map.put("actual_first_up", detail.getFirst_time_up());
            map.put("setting_first_down", detail.getSchedule().getFirst_time_down());
            map.put("actual_first_down", detail.getFirst_time_down());

            map.put("setting_second_up", detail.getSchedule().getSecond_time_up());
            map.put("actual_second_up", detail.getSecond_time_up());
            map.put("setting_second_down", detail.getSchedule().getSecond_time_down());
            map.put("actual_second_down", detail.getSecond_time_down());

            map.put("setting_third_up", detail.getSchedule().getThird_time_up());
            map.put("actual_third_up", detail.getThird_time_up());
            map.put("setting_third_down", detail.getSchedule().getThird_time_down());
            map.put("actual_third_down", detail.getThird_time_down());

            map.put("attendanceType", detail.getAttendanceType().getName());
            map.put("lateTime", detail.getLateTime());
            map.put("earlyTime", detail.getEarlyTime());
            map.put("absenteeismTime", detail.getAbsenteeismTime());
            map.put("ot_normal", detail.getOvertime_normal());
            map.put("ot_weekend", detail.getOvertime_weekend());
            map.put("ot_festival", detail.getOvertime_festival());
            map.put("setting_time", detail.getShould_attendance_time());
            map.put("actual_time", detail.getActual_attendance_time());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
