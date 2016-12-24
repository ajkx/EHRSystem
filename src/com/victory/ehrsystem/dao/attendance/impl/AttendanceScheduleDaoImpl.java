package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */
@Repository
public class AttendanceScheduleDaoImpl extends BaseDaoImpl<AttendanceSchedule> implements AttendanceScheduleDao {
    @Override
    public List<AttendanceSchedule> findByAcrossDay(boolean acrossday) {
        return find("select s from AttendanceSchedule s where s.acrossDay = ?0", acrossday);
    }
}
