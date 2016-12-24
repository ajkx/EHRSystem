package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.AttendanceScheduleDao;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleInfoDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.AttendanceScheduleInfo;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2016/12/23.
 * Time:14:35
 */
@Service
public class AttendanceScheduleService {

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
}
