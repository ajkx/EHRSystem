package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceRecordDao;
import com.victory.ehrsystem.dao.attendance.AttendanceTypeDao;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.attendance.AttendanceType;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */

public class AttendanceTypeDaoImpl extends BaseDaoImpl<AttendanceType> implements AttendanceTypeDao {
    @Override
    public AttendanceType getNormalType() {
        return get(AttendanceType.class,1);
    }

    @Override
    public AttendanceType getEarlyType() {
        return get(AttendanceType.class,2);
    }

    @Override
    public AttendanceType getLateType() {
        return get(AttendanceType.class,3);
    }

    @Override
    public AttendanceType getMissType() {
        return get(AttendanceType.class,4);
    }

    @Override
    public AttendanceType getAbnormalType() {
        return get(AttendanceType.class,5);
    }

    @Override
    public AttendanceType getLevelType() {
        return get(AttendanceType.class,14);
    }

    @Override
    public AttendanceType getNotGroupType() {
        return get(AttendanceType.class,15);
    }

    @Override
    public AttendanceType getRestType() {
        return get(AttendanceType.class,16);
    }

    @Override
    public AttendanceType getAcrossDayType() {
        return get(AttendanceType.class,17);
    }
}
