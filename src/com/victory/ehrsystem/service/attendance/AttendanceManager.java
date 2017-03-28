package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.dao.attendance.*;
import com.victory.ehrsystem.entity.attendance.*;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.util.ClassUtil;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/2/24.
 * Time:15:47
 */
@Service("attendanceManager")
public class AttendanceManager {

    public static final long ONE_DAY_TIME = 86400000;
    @Autowired
    private HrmResourceDao resourceDao;

    @Autowired
    private AttendanceGroupDao groupDao;

    @Autowired
    private AttendanceRecordDao recordDao;

    @Autowired
    private AttendanceDetailDao detailDao;

    @Autowired
    private AttendanceTypeDao typeDao;

    @Autowired
    private AcrossDayScheduleDao acrossDayScheduleDao;

    @Autowired
    private DateRecordDao dateRecordDao;

    @Autowired
    private OverTimeDao overTimeDao;

    @Autowired
    private OverTimeSettingDao overTimeSettingDao;

    public Date getRecordDate(){
        DateRecord date = dateRecordDao.getTopRecord();
        return date.getDate();
    }
    public void testAttendance(){
        Date beginDate = getRecordDate();
        Date endDate = DateUtil.getYesterday();
        List<Date> dateList = DateUtil.getDateList(beginDate, endDate);
        if(dateList.size() < 1){
            System.out.println(beginDate.toString()+"当前日期已产生数据!");
            return;
        }
        for (Date temp : dateList) {
            System.out.println("==========正在执行"+temp.toString()+"日期的考勤计算");
            //每天考勤明细计算
            autoAttendance(temp);

            //每天加班计算
            calculateOverTime(temp);
        }
    }

    /**
     * 进行考勤计算
     * @param date
     */
    public void autoAttendance(Date date) {

        //先执行AcrossDaySchedule表中的前天数据
        Date beforeDate = DateUtil.getYesterday(date);

        List<AcrossDaySchedule> acrossDayScheduleList = acrossDayScheduleDao.findScheduleListByDate(beforeDate);
        //执行上一天的跨天的运算m
        for (AcrossDaySchedule temp : acrossDayScheduleList) {
            System.out.println("遍历跨天班次中，人员为"+temp.getResource().getName()+",班次为"+temp.getSchedule().getName());
            initDetail(temp.getResource(), temp.getDate() ,temp.getSchedule());
            //计算完成删除记录
            acrossDayScheduleDao.delete(temp);
        }

        //下面执行正常的考勤
        //获取并遍历有考勤组的在职员工
        List<HrmResource> resourceList = resourceDao.findHaveSchedule();
        System.out.println("开始遍历员工,有"+resourceList.size()+"人!");
        int j = 0;
        for (HrmResource resource : resourceList) {

            //如果该人员该天已产生明细，则continue
            List<AttendanceDetail> detailList = detailDao.findByHrmResourceAndDate(resource, date);
            if(detailList.size() > 0){
                continue;
            }


            j++;
            System.out.println("正在遍历第"+j+"个员工!");
            AttendanceGroup group = resource.getAttendanceGroup();
            //根据考勤组的类型分别执行不同的考勤方法
            int groupType = group.getGroupType();
            switch (groupType) {
                case 1:
                    System.out.println("固定班制");
                    fixedScheduling(resource, group,date);
                    break;
                case 2:
                    arrangeScheduling(resource, group);
                    System.out.println("排班制");
                    break;
                case 3:
                    freeScheduling(resource, group);
                    System.out.println("自由打卡");
                    break;
            }
        }
    }

    /**
     * 固定排班
     * @param resource
     * @param group
     */
    public void fixedScheduling(HrmResource resource,AttendanceGroup group,Date date){
        int week = DateUtil.getDayOfWeek(date);
        AttendanceSchedule schedule = null;
        switch (week) {
            case 1:
                schedule = group.getSunday();
                System.out.println("星期日");
                break;
            case 2:
                schedule = group.getMonday();
                System.out.println("星期一");
                break;
            case 3:
                schedule = group.getTuesday();
                System.out.println("星期二");
                break;
            case 4:
                schedule = group.getWednesday();
                System.out.println("星期三");
                break;
            case 5:
                schedule = group.getThursday();
                System.out.println("星期四");
                break;
            case 6:
                schedule = group.getFriday();
                System.out.println("星期五");
                break;
            case 7:
                schedule = group.getSaturday();
                System.out.println("星期六");
                break;
        }
        //如果为休息班次，则退出运算
        System.out.println("正在判断班次属性，班次id为"+schedule.getId());
        if(schedule.getRest() != null && schedule.getRest()){
            System.out.println("该班次为休息班次！循环下一个员工!");
            return;
        }
        if(schedule.getAcrossDay()){
            //跨天存起当天数据,待后天执行

            AcrossDaySchedule acrossDaySchedule = new AcrossDaySchedule();
            acrossDaySchedule.setDate(date);
            acrossDaySchedule.setResource(resource);
            acrossDaySchedule.setSchedule(schedule);
            Serializable id = acrossDayScheduleDao.save(acrossDaySchedule);
            System.out.println("该班次为跨天班次，存起该跨天信息,id为"+id+"，待明天执行!");
        }else{
            //执行考勤计算
            initDetail(resource, date, schedule);
        }


    }

    /**
     * 排班制
     * @param resource
     * @param group
     */
    public void arrangeScheduling(HrmResource resource,AttendanceGroup group){

    }

    /**
     * 自由打卡
     * @param resource
     * @param group
     */
    public void freeScheduling(HrmResource resource, AttendanceGroup group) {

    }

    /**
     * 初始化明细
     * @param resource
     * @param date
     * @param schedule
     */
    public void initDetail(HrmResource resource,Date date,AttendanceSchedule schedule){

        AttendanceDetail detail = new AttendanceDetail();
        detail.setResource(resource);
        detail.setSchedule(schedule);
        detail.setDate(date);

        if(schedule.getRest() != null && schedule.getRest()){
            System.out.println("该班次为休息班次");
            detail.setShould_attendance_day(0);
            detail.setShould_attendance_time(0l);
            detail.setActual_attendance_time(0l);
            detail.setActual_attendance_day(0d);
        }else{
            int scheduleType = schedule.getScheduleType();
            System.out.println("===========开始初始化detail信息，该班次属性为"+scheduleType+"班制============");

            detail.setShould_attendance_day(1);
            detail.setShould_attendance_time(StringUtil.nullLong(schedule.getAttendanceTime()));

            List<AttendanceRecord> records = recordDao.findByResourceAndDate(resource,date);
            System.out.println("读取考勤原纪录!Records大小为"+records.size());
            if(schedule.getAcrossDay()){
                System.out.println(date.toString());
                System.out.println(DateUtil.getNextDay(date).toString());
                List<AttendanceRecord> records1 = recordDao.findByResourceAndDate(resource,DateUtil.getNextDay(date));
                records.addAll(records1);
                System.out.println("该班次为跨天，将考勤原纪录的集合加上明天的记录！Records大小为"+records.size());
            }
            //如果打卡数据为空，直接记录旷工,否则进行计算
            if(records == null || records.size() < 1){

                System.out.println("该员工的打卡数据为空，直接记录旷工!");
                detail.setAttendanceType(typeDao.getMissType());
                detail.setAbsenteeismTime(schedule.getAttendanceTime());
                detail.setAbsenteeismCount(schedule.getScheduleType());
                detail.setActual_attendance_time(0L);
                detail.setActual_attendance_day(0D);
            }else{
                switch (scheduleType) {
                    case 0:
                        //特殊班次，不用进行考勤
                        System.out.println("特殊班次，不用进行考勤!");
                        return;
                    case 1:
                        //一天一班制
                        System.out.println("执行一天一班制");
                        executeAttendance(detail,schedule,schedule.getFirst_time_up().getTime(),schedule.getFirst_time_up().getTime(),schedule.getFirst_time_down().getTime(),records,"First");
                        break;
                    case 2:
                        //一天两班制
                        System.out.println("执行一天二班制");
                        long long1 = executeAttendance(detail, schedule,schedule.getFirst_time_up().getTime(),schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(),records,"First");
                        executeAttendance(detail, schedule,long1,schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(),records,"Second");
                        break;
                    case 3:
                        //一天三班制
                        System.out.println("执行一天三班制");
                        long long2 = executeAttendance(detail, schedule, schedule.getFirst_time_up().getTime() ,schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(),records,"First");
                        long long3 = executeAttendance(detail, schedule, long2 ,schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(),records, "Second");
                        executeAttendance(detail, schedule,long3, schedule.getThird_time_up().getTime(), schedule.getThird_time_down().getTime(),records,"Third");
                        break;
                }
                //计算考勤时间
                calculateTime(detail);

                //记录明细实际出勤天数
                if(detail.getActual_attendance_time() != null && detail.getActual_attendance_time() != 0){
                    double temp = (double)detail.getActual_attendance_time() / (double)detail.getShould_attendance_time()*100;
                    detail.setActual_attendance_day((Math.floor(temp)/100));
                }
            }
        }
        Serializable id = detailDao.save(detail);
        System.out.println("生成员工明细表成功，id为"+id);
    }

    /**
     * 执行考勤计算
     * @param detail
     * @param schedule
     * @param currentTime
     * @param time_up
     * @param time_down
     * @param records
     * @param order
     * @return
     */
    public long executeAttendance(AttendanceDetail detail, AttendanceSchedule schedule,long currentTime, long time_up, long time_down, List<AttendanceRecord> records,String order){

        //如果为跨天班次则将上下班的时间加上一天
        if(currentTime > ONE_DAY_TIME || time_up < currentTime){
            time_up += ONE_DAY_TIME;
            System.out.println("time_up打卡时段为跨天！");
        }
        currentTime = time_up;
        if(currentTime > ONE_DAY_TIME || time_down < currentTime){
            time_down += ONE_DAY_TIME;
            System.out.println("time_down打卡时段为跨天！");
        }
        currentTime = time_down;

        //打卡范围时间
        long scope_up = schedule.getScope_up();
        long scope_down = schedule.getScope_down();

        //上班开始打卡时间
        long beginTime = time_up - scope_up;

        //下班结束打卡时间
        long endTime = time_down + scope_down;

        //筛选完成的信息
        //上下班时间
        long actual_time_up = 0;
        long actual_time_down = 0;
        //偏差时间
        List<Long> offsetTime = new ArrayList<>();


        //SET方法名称
        String method_up = "set"+order+"_time_up";
        String method_down = "set"+order+"_time_down";

        String method_type_up = "set"+order+"UpType";
        String method_type_down = "set"+order+"DownType";


        for(AttendanceRecord record : records){
            System.out.println("=========开始遍历该员工的打卡数据==========");
            long time = record.getPunchTime().getTime();

            //如果打卡日期大于当前明细的日期，则将打卡时间加上一天的毫秒数
            if(record.getDate().compareTo(detail.getDate()) > 0)time += ONE_DAY_TIME;

            //如果打卡时间，可能存在加班的情况
            if (time < beginTime || time > endTime) {
                System.out.println("打卡时间在打卡范围之外!");
                continue;
            }
            //上班有效打卡时间范围
            else if (beginTime <= time && time <= time_up) {
                System.out.println("打卡时间在上班有效打卡范围!");
                //因为数据都是按时间的先后顺序找出来的，而存符合的时间是存最早的那个，因此下面的可以直接actual_time_up不等于0就continue的，但为了保险起见，做一个大小判断
                if (actual_time_up != 0) {
                    if (actual_time_up > time) {
                        System.out.println("读取上班打卡时间顺序出错！");
                        actual_time_up = time;
                    }
                } else {
                    actual_time_up = time;
                }
            }
            //上班期间的偏差打卡
            else if (time_up < time && time < time_down) {
                System.out.println("打卡时间在上班时间范围!");
                offsetTime.add(time);
            }
            //下班有效打卡时间范围
            else if (time_down <= time && time <= endTime) {
                System.out.println("打卡时间在下班有效打卡范围!");
                if (actual_time_down != 0) {
                    if(actual_time_down > time){
                        actual_time_down = time;
                    }
                }else{
                    actual_time_down = time;
                }
            }
        }

        //升序列表
        Collections.sort(offsetTime);
        long earliestTime = 0;
        long latestTime = 0;
        //获取最早的那个偏差时间
        if(offsetTime.size() > 0){
            earliestTime = offsetTime.get(0);
            latestTime = offsetTime.get(offsetTime.size() - 1);
        }

        //获取设置的迟到早退的容差时间
        long defaultUpOffsetTime = time_up + StringUtil.nullLong(schedule.getOffsetTime());
        long defaultDownOffsetTime = time_down - StringUtil.nullLong(schedule.getOffsetTime());

        System.out.println("=========遍历该员工的打卡数据结束==========");

        if(actual_time_up != 0 && actual_time_down != 0){
            //正常进行打卡的信息设置
            ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getNormalType());
            ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getNormalType());
            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(actual_time_up));
            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(actual_time_down));
            System.out.println("该员工时段打卡正常");
        }

        else if (actual_time_up == 0 && actual_time_down != 0) {
            //如果上班卡为空，下班卡不为空，

            //存在偏差时间
            if(offsetTime.size() > 0){


                if(earliestTime > defaultUpOffsetTime){
                    //旷工信息设置
                    ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getMissType());
                }else{
                    //迟到信息设置
                    ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getLateType());
                }
                ClassUtil.invokeMethod(detail,method_up,Time.class, new Time(earliestTime));
            }
            //如果没有偏差时间，记录旷工信息
            else{
                ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getMissType());
            }
            ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getNormalType());
            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(actual_time_down));
            System.out.println("该员工时段上班打卡异常");
        }

        else if (actual_time_up != 0 && actual_time_down == 0) {
            //上班卡不为空，下班卡为空

            if (offsetTime.size() > 0) {

                if(latestTime < defaultDownOffsetTime){
                    //旷工信息设置
                    ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getMissType());
                }else{
                    //早退信息设置
                    ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getEarlyType());
                }
                ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(latestTime));
            }else{
                //旷工信息设置
                ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getMissType());
            }
            ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getNormalType());
            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(actual_time_up));
            System.out.println("该员工时段下班打卡异常");
        }

        else{
            //如果偏差时间大于等于2个，则判断最早和最迟的时间来记录是迟到还是早退还是旷工，都要把打卡时间存进实际打卡时间
            if(offsetTime.size() > 1){
                if(earliestTime > defaultUpOffsetTime){
                    ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getMissType());
                }else{
                    ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getLateType());
                }
                if(latestTime < defaultDownOffsetTime){
                    ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getMissType());
                }else{
                    ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getEarlyType());
                }
                ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(earliestTime));
                ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(latestTime));
            }
            //如果只有1个时间，则先根据默认偏差设置来判断迟到还是旷工，只存上班实际打卡时间
            else if(offsetTime.size() == 1){
                if(earliestTime > defaultUpOffsetTime){
                    ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getMissType());
                }else{
                    ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getLateType());
                }
                ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(earliestTime));
                ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getMissType());
            }
            //偏差时间为空，直接设为旷工
            else{
                ClassUtil.invokeMethod(detail,method_type_up,AttendanceType.class,typeDao.getMissType());
                ClassUtil.invokeMethod(detail,method_type_down,AttendanceType.class,typeDao.getMissType());

            }
            System.out.println("该员工时段旷工");
        }
        return currentTime;
    }

//    private AttendanceType checkType(AttendanceType type1, AttendanceType type2) {
//        if(type1 == null){
//            return type2;
//        }
//        if(type2 == null){
//            return type1;
//        }
//        if (type1.getPriority() > type2.getPriority()) {
//            return type1;
//        }else{
//            return type2;
//        }
//    }

    /**
     * 计算迟到，早退，旷工，出勤的具体时间
     * @param detail
     */
    public void calculateTime(AttendanceDetail detail) {
        AttendanceSchedule schedule = detail.getSchedule();
        //如果为休息班次的
        if (schedule.getRest() == null || schedule.getRest()) {
            return;
        }
        if(schedule.getAcrossDay()){

        }
        long lateTime = 0;
        int lateCount = 0;
        long earlyTime = 0;
        int earlyCount = 0;
        long absenteeismTime = 0;
        int absenteeismCount = 0;

        //计算第一次上班
        if(detail.getFirstUpType() != null ){

            long settingUp = schedule.getFirst_time_up().getTime();
            long settingDown = schedule.getFirst_time_down().getTime();

            Time actualTime = detail.getFirst_time_up();
            if(schedule.getAcrossDay()){
                if(settingDown < settingUp){
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getFirstUpType().getName()){
                case "迟到":
                    lateTime += actualTime.getTime() - settingUp;
                    lateCount++;
                    break;
                case "旷工":
                    if(actualTime == null){
                        absenteeismTime = settingDown - settingUp;
                    }else{
                        absenteeismTime += actualTime.getTime() - settingUp;
                    }
                    absenteeismCount++;
                    break;
            }
        }
        //计算第一次下班
        if (detail.getFirstDownType() != null) {

            long settingUp = schedule.getFirst_time_up().getTime();
            long settingDown = schedule.getFirst_time_down().getTime();

            Time actualTime = detail.getFirst_time_down();
            if(schedule.getAcrossDay()){
                if(settingDown < settingUp){
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getFirstDownType().getName()){
                case "早退":;
                    earlyTime += returnTime(settingDown) - returnTime(actualTime.getTime());
                    earlyCount++;
                    break;
                case "旷工":
                    if(actualTime == null){
                        absenteeismTime = settingDown - settingUp;
                    }else{
                        if(detail.getFirst_time_up() == null){
                            absenteeismTime = settingDown - settingUp;
                        }else{
                            absenteeismTime += returnTime(settingDown) - returnTime(actualTime.getTime());
                        }
                    }
                    absenteeismCount++;
                    break;
            }
        }


        //计算第二次上班
        if(detail.getSecondUpType() != null ){

            long settingUp = schedule.getSecond_time_up().getTime();
            long settingDown = schedule.getSecond_time_down().getTime();

            Time actualTime = detail.getSecond_time_up();
            if(schedule.getAcrossDay()){
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getSecondUpType().getName()){
                case "迟到":
                    lateTime += returnTime(actualTime.getTime()) - returnTime(settingUp);
                    lateCount++;
                    break;
                case "旷工":
                    if(actualTime == null){
                        absenteeismTime += settingDown - settingUp;
                    }else{
                        absenteeismTime += returnTime(actualTime.getTime()) - returnTime(settingUp);
                    }
                    absenteeismCount++;
                    break;
            }
        }

        //计算第二次下班
        if (detail.getSecondDownType() != null) {

            long settingUp = schedule.getSecond_time_up().getTime();
            long settingDown = schedule.getSecond_time_down().getTime();

            Time actualTime = detail.getSecond_time_down();
            if(schedule.getAcrossDay()){
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getSecondDownType().getName()){
                case "早退":
                    earlyTime += returnTime(settingDown) - returnTime(actualTime.getTime());
                    earlyCount++;
                    break;
                case "旷工":
                    if(actualTime == null){
                        absenteeismTime += settingDown - settingUp;
                    }else{
                        if(detail.getSecond_time_up() == null){
                            absenteeismTime += settingDown - settingUp;
                        }else{
                            absenteeismTime += returnTime(actualTime.getTime()) - returnTime(settingDown);
                        }
                    }
                    absenteeismCount++;
                    break;
            }
        }

        //计算第三次上班
        if(detail.getThirdUpType() != null ){

            long settingUp = schedule.getThird_time_up().getTime();
            long settingDown = schedule.getThird_time_down().getTime();

            Time actualTime = detail.getThird_time_up();
            if(schedule.getAcrossDay()){
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getThirdUpType().getName()){
                case "迟到":
                    lateTime += returnTime(actualTime.getTime()) - returnTime(settingUp);
                    lateCount++;
                    break;
                case "旷工":
                    if(actualTime == null){
                        absenteeismTime += settingDown - settingUp;
                        System.out.println("");
                    }else{
                        absenteeismTime += returnTime(actualTime.getTime()) - returnTime(settingUp);
                    }
                    absenteeismCount++;
                    break;
            }
        }

        //计算第三次下班
        if (detail.getThirdDownType() != null) {

            long settingUp = schedule.getThird_time_up().getTime();
            long settingDown = schedule.getThird_time_down().getTime();

            Time actualTime = detail.getThird_time_down();
            if(schedule.getAcrossDay()){
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getThirdDownType().getName()){
                case "早退":
                    earlyTime += returnTime(settingDown) - returnTime(actualTime.getTime());
                    earlyCount++;
                    break;
                case "旷工":
                    if(actualTime == null){
                        absenteeismTime += settingDown - settingUp;
                    }else{
                        if(detail.getThird_time_up() == null){
                            absenteeismTime += settingDown - settingUp;
                        }else{
                            absenteeismTime += returnTime(settingDown) - returnTime(actualTime.getTime());
                        }
                    }
                    absenteeismCount++;
                    break;
            }
        }

        detail.setLateCount(lateCount);
        detail.setLateTime(lateTime);

        detail.setEarlyCount(earlyCount);
        detail.setEarlyTime(earlyTime);

        detail.setAbsenteeismCount(absenteeismCount);
        detail.setAbsenteeismTime(absenteeismTime);

        if (earlyCount == 0 && lateCount == 0 && absenteeismCount == 0) {
            detail.setAttendanceType(typeDao.getNormalType());
        }else{
            detail.setAttendanceType(typeDao.getAbnormalType());
        }
        detail.setActual_attendance_time(schedule.getAttendanceTime() - lateTime - earlyTime - absenteeismTime);
    }


    /**
     * 计算加班时间
     * @param date
     */
    public void calculateOverTime(Date date) {
        java.util.Date endDate = DateUtil.getLastTimeInDay(date);
        //获取当天的加班记录
        List<OverTimeRecord> list = overTimeDao.findByDate(date, endDate);

        for (OverTimeRecord record : list) {
            //获取加班申请人员
            HrmResource resource = record.getResource();
            Date sqlDate = new Date(record.getDate().getTime());
            //找出其当天的考勤明细
            List<AttendanceDetail> detailList = detailDao.findByHrmResourceAndDate(resource,sqlDate);
            if(detailList.size() < 1){
                record.setRemark("当天没有考勤记录，该加班申请失效");
                record.setStatus(OverTimeRecord.Status.error);
                continue;
            }

            //获取申请的加班开始时间和结束时间,和算上范围的beginTimeUp endTimeDown
            OverTimeSetting setting = overTimeSettingDao.get(OverTimeSetting.class, 1);
            long timeUp = record.getDate().getTime();
            long timeDown = record.getEndDate().getTime();
            long beginTimeUp = timeUp - setting.getOffsetTimeUp();
            long endTimeDown = timeDown + setting.getOffsetTimeDown();


            //获取规定打卡时间开始和结束时间相差的日期的打卡记录
            List<Date> dateList = DateUtil.getDateList(beginTimeUp, endTimeDown);
            List<AttendanceRecord> recordList = new ArrayList<>();
            for (Date date1 : dateList) {
                List<AttendanceRecord> tempList = recordDao.findByResourceAndDate(resource, date1);
                recordList.addAll(tempList);
            }

            //符合打卡范围的打卡记录
            List<Long> timeList = new ArrayList<>();
            //遍历打卡记录
            for (AttendanceRecord record1 : recordList) {
                long time = DateUtil.parseSqlDateAndTime(record1.getDate().toString(),record1.getPunchTime().toString());

                //如果打卡时间在规定打卡时间之外
                if (time < beginTimeUp || time > endTimeDown) {
                    continue;
                }else{
                    timeList.add(time);
                }
            }
            Collections.sort(timeList);
            long actualUp = 0;
            long actualDown = 0;
            int timeSize = timeList.size();


            //获取对应的考勤明细detail和当天的班次schedule
            AttendanceDetail detail = detailList.get(0);
            AttendanceSchedule schedule = detail.getSchedule();

            //判断连班的情况，先假设连班的规则仅仅为最后一个班次的下班与加班的开始时间相等
            long scheduleDown = 0;
            Time scheduleDownTime = null;
            int type = schedule.getScheduleType();
            switch (type){
                case 1:
                    scheduleDownTime = schedule.getFirst_time_down();
                    break;
                case 2:
                    scheduleDownTime = schedule.getSecond_time_down();
                    break;
                case 3:
                    scheduleDownTime = schedule.getThird_time_down();
                    break;
            }
            //班次的最后下班时间
            scheduleDown = DateUtil.parseSqlDateAndTime(sqlDate.toString(), scheduleDownTime.toString());
            //如果班次的最后下班时间等于加班开始时间，则判断为连班
            if(scheduleDown == timeUp){

            }

            if (timeSize < 1) {
                record.setStatus(OverTimeRecord.Status.abnormal);
                record.setRemark("上下班卡都为空");
            }else if(timeSize == 1){
                actualUp = timeList.get(0);
                record.setTimeUp(new java.util.Date(actualUp));
                record.setActualCount(0l);
                record.setStatus(OverTimeRecord.Status.abnormal);
                record.setRemark("下班卡为空");
            }else{
                actualUp = timeList.get(0);
                actualDown = timeList.get(timeSize - 1);
                record.setTimeUp(new java.util.Date(actualUp));
                record.setTimeDown(new java.util.Date(actualDown));

                long offset = 0;

                //如果实际加班时数大于申请加班时数
                if(offset > record.getCount()){
                    record.setStatus(OverTimeRecord.Status.success);
                    //如果加班设置为按刷卡时间计算，则实际加班时间为offset
                    if(setting.getCalculateType() == 2){
                        record.setActualCount(actualDown - actualUp);
                    }else if(setting.getCalculateType() == 1){
                        record.setActualCount(record.getCount());
                    }
                }
//               如果实际加班时数小于申请加班时数，按实际来算,并且记录异常
                else{
                    if(setting.getCalculateType() == 2){
                        offset = actualDown - actualUp;
                    }else if(setting.getCalculateType() == 1){
                        offset = actualDown - timeUp;
                    }
                    record.setActualCount(offset);
                    record.setStatus(OverTimeRecord.Status.abnormal);
                    record.setRemark("实际加班时间小于申请加班时间！");
                }
                switch (record.getType()){
                    case 1:
                        detail.setOvertime_normal(record.getActualCount());
                        break;
                    case 2:
                        detail.setOvertime_weekend(record.getActualCount());
                        break;
                    case 3:
                        detail.setOvertime_festival(record.getActualCount());
                        break;
                }
                detailDao.update(detail);
            }
            overTimeDao.update(record);
        }

    }

    public long returnTime(long l) {
        return l > ONE_DAY_TIME ? l - ONE_DAY_TIME : l;
    }
}
