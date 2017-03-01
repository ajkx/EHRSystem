package com.victory.ehrsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2016/12/14.
 * Time:16:17
 */
public class DateUtil {

    /**
     * 获取sql.date格式的今天日期
     * @return
     */
    public static Date getToday(){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    /**
     * 获取sql.date格式的昨天日期
     * @return
     */
    public static Date getYesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    public static Date getYesterday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }
    /**
     * 获取sql.date格式的前天日期
     * @return
     */
    public static Date getBeforeYesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-2);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    public static Date getNextDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }
    public static Long getOneDayTime(){
        return Long.valueOf(86400000);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * 返回两个日期之间的日期集合，包括开始日期和结束日期
     * @param begin
     * @param end
     * @return
     */
    public static List<Date> getDateList(Date begin,Date end){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(begin);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        List<Date> dateList = new ArrayList<>();
        while(cal1.compareTo(cal2) <= 0){
            dateList.add(new Date(cal1.getTimeInMillis()));
            cal1.add(Calendar.DATE,1);
        }
        return dateList;
    }
}
