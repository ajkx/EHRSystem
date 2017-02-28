package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.DateRecordDao;
import com.victory.ehrsystem.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by ajkx on 2017/2/28.
 */
@Service
public class AttendanceDateManager {

    @Autowired
    private DateRecordDao dateRecordDao;

    public void checkDate(){
        Date beginDate = dateRecordDao.getTopRecord().getDate();
        Date nowDate = DateUtil.getYesterday();

    }
}
