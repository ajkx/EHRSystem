package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */
@Repository
public class AttendanceDetailDaoImpl extends BaseDaoImpl<AttendanceDetail> implements AttendanceDetailDao{
    @Override
    public List<AttendanceDetail> findByHrmResource(HrmResource resource) {
        return find("select a from AttendanceDetail where resourceid = ?0",resource);
    }

    @Override
    public List<AttendanceDetail> findByDate(Date date) {
        return find("select a from AttendanceDetail where date = ?0",date);
    }
}
