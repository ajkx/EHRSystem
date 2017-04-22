package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.CustomHoliday;
import com.victory.ehrsystem.entity.attendance.OverTimeSetting;

import java.sql.Date;


/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface CustomHolidayDao extends BaseDao<CustomHoliday>{

    CustomHoliday findByDate(Date date);
}
