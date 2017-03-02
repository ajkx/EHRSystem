package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceRecordDao;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */

public class AttendanceRecordDaoImpl extends BaseDaoImpl<AttendanceRecord> implements AttendanceRecordDao {
    @Override
    public List<AttendanceRecord> findByHrmResource(HrmResource resource) {
        return find("select a from AttendanceRecord a where resourceId = ?0",resource);
    }

    @Override
    public List<AttendanceRecord> findByDate(Date date) {
        return find("select a from AttendanceRecord a where punchDate = ?0",date);
    }

    @Override
    public List<AttendanceRecord> findByResourceAndDate(HrmResource resource, Date date) {
        return find("select a from AttendanceRecord a where resourceId = ?0 and punchDate = ?1", resource, date);
    }
}
