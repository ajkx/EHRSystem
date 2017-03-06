package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.vo.PageInfo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface AttendanceDetailDao extends BaseDao<AttendanceDetail>{

    List<AttendanceDetail> findByHrmResource(HrmResource resource);

    List<AttendanceDetail> findByDate(Date date);

    PageInfo findByPage(Date beginDate, Date endDate, List<HrmResource> resources,int pageNo, int pageSize);
}
