package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.CustomHolidayDao;
import com.victory.ehrsystem.entity.attendance.CustomHoliday;
import com.victory.ehrsystem.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ajkx
 * Date: 2017/4/22.
 * Time:16:14
 */
@Service
public class CustomHoldayService extends BaseService<CustomHoliday>{

    @Autowired
    private CustomHolidayDao customHolidayDao;

    public CustomHoliday findByDate(Date date) {
        return customHolidayDao.findByDate(date);
    }

    public Set<Date> findAllDate(){
        List<CustomHoliday> list = customHolidayDao.findAll(CustomHoliday.class);
        Set<Date> dateSet = new HashSet<>();
        for (CustomHoliday holiday : list) {
            dateSet.add(holiday.getDate());
        }
        return dateSet;
    }
}
