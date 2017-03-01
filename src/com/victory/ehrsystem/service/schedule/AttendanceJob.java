package com.victory.ehrsystem.service.schedule;

import com.victory.ehrsystem.dao.attendance.DateRecordDao;
import com.victory.ehrsystem.entity.attendance.DateRecord;
import com.victory.ehrsystem.service.attendance.AttendanceManager;
import com.victory.ehrsystem.util.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/3/1.
 * Time:15:02
 */
public class AttendanceJob extends QuartzJobBean{

    private boolean isRunning = false;

    private AttendanceManager manager;

    public AttendanceManager getManager() {
        return manager;
    }

    public void setManager(AttendanceManager manager) {
        this.manager = manager;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        System.out.println("aaaaa");
        if(!isRunning){
            try{


            isRunning = true;
            Date beginDate = manager.getRecordDate();
            Date endDate = DateUtil.getYesterday();
            List<Date> dateList = DateUtil.getDateList(beginDate, endDate);
            for (Date temp : dateList) {
                manager.autoAttendance(temp);
            }
            isRunning = false;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
