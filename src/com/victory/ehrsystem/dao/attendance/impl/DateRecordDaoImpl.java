package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.DateRecordDao;
import com.victory.ehrsystem.entity.attendance.DateRecord;

/**
 * Created by ajkx on 2017/2/27.
 */
public class DateRecordDaoImpl extends BaseDaoImpl<DateRecord> implements DateRecordDao{
    @Override
    public DateRecord getTopRecord() {
        return get(DateRecord.class,1);
    }
}
