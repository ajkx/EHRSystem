package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.DateRecord;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.vo.PageInfo;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public interface OverTimeDao extends BaseDao<OverTimeRecord>{

    List<OverTimeRecord> findByDateAndResource(java.util.Date beginDate, java.util.Date endDate,HrmResource resource);

    List<OverTimeRecord> findByDateForRepeat(java.util.Date beginDate, java.util.Date endDate,HrmResource resource);

//    PageInfo list(Date beginDate, Date endDate, List<HrmResource> resources, int pageNo, int pageSize);
    PageInfo list(Date beginDate, Date endDate, List<Integer> resources, int pageNo, int pageSize);
}
