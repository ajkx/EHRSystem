package com.victory.ehrsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

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
}
