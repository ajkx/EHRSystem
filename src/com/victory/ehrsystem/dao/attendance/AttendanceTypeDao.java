package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.attendance.AttendanceType;
import com.victory.ehrsystem.entity.hrm.HrmResource;

import java.sql.Date;
import java.util.List;


/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceTypeDao extends BaseDao<AttendanceType>{

    /**
     * 正常考勤
     * @return
     */
    AttendanceType getNormalType();

    /**
     * 早退考勤
     * @return
     */
    AttendanceType getEarlyType();

    /**
     * 迟到考勤
     * @return
     */
    AttendanceType getLateType();

    /**
     * 旷工考勤
     * @return
     */
    AttendanceType getMissType();

    /**
     * 异常考勤
     * @return
     */
    AttendanceType getAbnormalType();
}
