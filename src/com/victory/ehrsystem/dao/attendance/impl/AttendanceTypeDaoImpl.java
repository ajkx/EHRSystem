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
@Repository
public class AttendanceTypeDaoImpl extends BaseDaoImpl<AttendanceType> implements AttendanceTypeDao {
}
