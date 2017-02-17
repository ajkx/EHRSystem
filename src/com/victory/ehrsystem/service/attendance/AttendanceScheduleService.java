package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.AttendanceScheduleDao;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleInfoDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.AttendanceScheduleInfo;
import com.victory.ehrsystem.entity.hrm.HrmResource;
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
 * Date: 2016/12/23.
 * Time:14:35
 */
@Service
public class AttendanceScheduleService extends BaseService<AttendanceSchedule>{

    @Autowired
    private AttendanceScheduleDao attendanceScheduleDao;

    @Autowired
    private AttendanceScheduleInfoDao attendanceScheduleInfoDao;

    /**
     * 根据是否跨天的班次找出对应的人员集合
     * @param acrossday
     * @return
     */
    public List<HrmResource> findHrmResourceBySchedule(boolean acrossday) {
        List<AttendanceSchedule> schedules = attendanceScheduleDao.findByAcrossDay(acrossday);
        List<HrmResource> resources = new ArrayList<>();
        for (AttendanceSchedule schedule : schedules) {
            List<AttendanceScheduleInfo> attendanceScheduleInfos = attendanceScheduleInfoDao.findBySchedule(schedule);
            for (AttendanceScheduleInfo attendanceScheduleInfo : attendanceScheduleInfos) {
                resources.add(attendanceScheduleInfo.getResource());
            }
        }
        return resources;
    }

    @Override
    public PageInfo findByPage(Class<AttendanceSchedule> entityClazz, HttpServletRequest request) {
        PageInfo info = super.findByPage(entityClazz, request);
        List<AttendanceSchedule> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (AttendanceSchedule schedule : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", schedule.getId());
            map.put("name", StringUtil.nullString(schedule.getName()));
            String time = StringUtil.getScheduleTime(schedule);
            map.put("time", time);
            map.put("description", StringUtil.nullString(schedule.getDescription()));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
