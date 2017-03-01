package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AcrossDayScheduleDao;
import com.victory.ehrsystem.dao.attendance.DateRecordDao;
import com.victory.ehrsystem.entity.attendance.AcrossDaySchedule;
import com.victory.ehrsystem.entity.attendance.DateRecord;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public class AcrossDayScheduleDaoImpl extends BaseDaoImpl<AcrossDaySchedule> implements AcrossDayScheduleDao{

    @Override
    public List<AcrossDaySchedule> findScheduleListByDate(Date date) {
        return find("select a from AcrossDaySchedule a where a.date = ?0",date);
    }
}
