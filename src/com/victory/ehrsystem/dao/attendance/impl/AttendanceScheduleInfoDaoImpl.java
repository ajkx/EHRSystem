package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleDao;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleInfoDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.AttendanceScheduleInfo;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */
@Repository
public class AttendanceScheduleInfoDaoImpl extends BaseDaoImpl<AttendanceScheduleInfo> implements AttendanceScheduleInfoDao {
    @Override
    public List<AttendanceScheduleInfo> findOneByNameAndDate(HrmResource resource, Date date) {
        return find("select a from AttendanceScheduleInfo a where a.resource = ?0 and a.date = ?1",resource,date);
    }


    @Override
    public List<AttendanceScheduleInfo> findBySchedule(AttendanceSchedule schedule) {
        return find("select a from AttendanceScheduleInfo a where a.schedule = ?0", schedule);
    }
}
