package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.CustomHolidayDao;
import com.victory.ehrsystem.dao.attendance.OverTimeSettingDao;
import com.victory.ehrsystem.entity.attendance.CustomHoliday;
import com.victory.ehrsystem.entity.attendance.OverTimeSetting;

import java.sql.Date;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */

public class CustomHolidayDaoImpl extends BaseDaoImpl<CustomHoliday> implements CustomHolidayDao {

    @Override
    public CustomHoliday findByDate(Date date) {
        return find("select c from CustomHoliday where date = ?0",date).get(0);
    }
}
