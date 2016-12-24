package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;

import java.sql.Date;
import java.util.List;


/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceRecordDao extends BaseDao<AttendanceRecord>{

    List<AttendanceRecord> findByHrmResource(HrmResource resource);

    List<AttendanceRecord> findByDate(Date date);

    List<AttendanceRecord> findByResourceAndDate(HrmResource resource, Date date);
}
