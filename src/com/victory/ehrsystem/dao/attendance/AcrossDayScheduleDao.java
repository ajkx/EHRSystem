package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.AcrossDaySchedule;
import com.victory.ehrsystem.entity.attendance.DateRecord;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public interface AcrossDayScheduleDao extends BaseDao<AcrossDaySchedule>{

    List<AcrossDaySchedule> findScheduleListByDate(Date date);
}
