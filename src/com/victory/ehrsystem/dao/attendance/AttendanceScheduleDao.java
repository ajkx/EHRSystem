package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;

import java.util.List;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceScheduleDao extends BaseDao<AttendanceSchedule>{

    /**
     * 返回是否跨天的班次集合
     * @param acrossday
     * @return
     */
    List<AttendanceSchedule> findByAcrossDay(boolean acrossday);
}
