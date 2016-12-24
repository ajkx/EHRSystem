package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.AttendanceScheduleInfo;
import com.victory.ehrsystem.entity.hrm.HrmResource;

import java.util.Date;
import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceScheduleInfoDao extends BaseDao<AttendanceScheduleInfo> {

    /**
     * 根据员工和日期找寻其班次
     * @param resource
     * @param date
     * @return
     */
    List<AttendanceScheduleInfo> findOneByNameAndDate(HrmResource resource, Date date);


    /**
     * 通过班次找出对应的班次对应集合
     * @param schedule
     * @return
     */
    List<AttendanceScheduleInfo> findBySchedule(AttendanceSchedule schedule);
}
