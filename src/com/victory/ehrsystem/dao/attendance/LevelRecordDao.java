package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.LevelRecord;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.vo.PageInfo;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public interface LevelRecordDao extends BaseDao<LevelRecord>{

    List<LevelRecord> findByDate(java.util.Date beginDate, java.util.Date endDate);

    List<LevelRecord> findByDateAndResource(java.util.Date beginDate, java.util.Date endDate,HrmResource resource);
}
