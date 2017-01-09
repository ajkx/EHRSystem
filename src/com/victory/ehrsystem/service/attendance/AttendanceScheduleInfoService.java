package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.AttendanceScheduleDao;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleInfoDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.vo.ScheduleInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2016/12/23.
 * Time:14:35
 */
@Service
public class AttendanceScheduleInfoService extends BaseService<AttendanceSchedule>{

    @Autowired
    private AttendanceScheduleDao attendanceScheduleDao;

    @Autowired
    private AttendanceScheduleInfoDao attendanceScheduleInfoDao;

//    public List<ScheduleInfoVo> findScheduleInfoByDateAndResouce(Date date) {
//
//    }
}
