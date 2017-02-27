package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.DateRecord;

/**
 * Created by ajkx on 2017/2/27.
 */
public interface DateRecordDao extends BaseDao<DateRecord>{

    DateRecord getTopRecord();
}
