//package com.victory.ehrsystem.service.attendance;
//
//import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
//import com.victory.ehrsystem.dao.attendance.*;
//import com.victory.ehrsystem.entity.attendance.*;
//import com.victory.ehrsystem.entity.hrm.HrmResource;
//import com.victory.ehrsystem.util.ClassUtil;
//import com.victory.ehrsystem.util.DateUtil;
//import com.victory.ehrsystem.util.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.sql.Date;
//import java.sql.Time;
//import java.util.List;
//
///**
// * Created by ajkx
// * Date: 2017/2/24.
// * Time:15:47
// */
//@Service("attendanceManager")
//public class AttendanceManagerCopy {
//
//    public static final long ONE_DAY_TIME = 86400000;
//    @Autowired
//    private HrmResourceDao resourceDao;
//
//    @Autowired
//    private AttendanceGroupDao groupDao;
//
//    @Autowired
//    private AttendanceRecordDao recordDao;
//
//    @Autowired
//    private AttendanceDetailDao detailDao;
//
//    @Autowired
//    private AttendanceTypeDao typeDao;
//
//    @Autowired
//    private AcrossDayScheduleDao acrossDayScheduleDao;
//
//    @Autowired
//    private DateRecordDao dateRecordDao;
//
//    public Date getRecordDate(){
//        DateRecord date = dateRecordDao.getTopRecord();
//        return date.getDate();
//    }
//    public void testAttendance(){
//        Date beginDate = getRecordDate();
//        Date endDate = DateUtil.getYesterday();
//        List<Date> dateList = DateUtil.getDateList(beginDate, endDate);
//        if(dateList.size() < 1){
//            System.out.println(beginDate.toString()+"当前日期已产生数据!");
//            return;
//        }
//        for (Date temp : dateList) {
//            System.out.println("==========正在执行"+temp.toString()+"日期的考勤计算");
//            autoAttendance(temp);
//        }
//    }
//    public void autoAttendance(Date date) {
//
//        //先执行AcrossDaySchedule表中的前天数据
//        Date beforeDate = DateUtil.getYesterday(date);
//
//        List<AcrossDaySchedule> acrossDayScheduleList = acrossDayScheduleDao.findScheduleListByDate(beforeDate);
//        //执行上一天的跨天的运算m
//        for (AcrossDaySchedule temp : acrossDayScheduleList) {
//            System.out.println("遍历跨天班次中，人员为"+temp.getResource().getName()+",班次为"+temp.getSchedule().getName());
//            initDetail(temp.getResource(), temp.getDate() ,temp.getSchedule());
//        }
//
//        //下面执行正常的考勤
//        //获取并遍历有考勤组的在职员工
//        List<HrmResource> resourceList = resourceDao.findHaveSchedule();
//        System.out.println("开始遍历员工,有"+resourceList.size()+"人!");
//        int j = 0;
//        for (HrmResource resource : resourceList) {
//            j++;
//            System.out.println("正在遍历第"+j+"个员工!");
//            AttendanceGroup group = resource.getAttendanceGroup();
//            //根据考勤组的类型分别执行不同的考勤方法
//            int groupType = group.getGroupType();
//            switch (groupType) {
//                case 1:
//                    System.out.println("固定班制");
//                    fixedScheduling(resource, group,date);
//                    break;
//                case 2:
//                    arrangeScheduling(resource, group);
//                    System.out.println("排班制");
//                    break;
//                case 3:
//                    freeScheduling(resource, group);
//                    System.out.println("自由打卡");
//                    break;
//            }
//        }
//    }
//
//    /**
//     * 固定排班
//     * @param resource
//     * @param group
//     */
//    public void fixedScheduling(HrmResource resource,AttendanceGroup group,Date date){
//        int week = DateUtil.getDayOfWeek(date);
//        AttendanceSchedule schedule = null;
//        switch (week) {
//            case 1:
//                schedule = group.getSunday();
//                System.out.println("星期日");
//                break;
//            case 2:
//                schedule = group.getMonday();
//                System.out.println("星期一");
//                break;
//            case 3:
//                schedule = group.getTuesday();
//                System.out.println("星期二");
//                break;
//            case 4:
//                schedule = group.getWednesday();
//                System.out.println("星期三");
//                break;
//            case 5:
//                schedule = group.getThursday();
//                System.out.println("星期四");
//                break;
//            case 6:
//                schedule = group.getFriday();
//                System.out.println("星期五");
//                break;
//            case 7:
//                schedule = group.getSaturday();
//                System.out.println("星期六");
//                break;
//        }
//        //如果为休息班次，则退出运算
//        System.out.println("正在判断班次属性，班次id为"+schedule.getId());
//        if(schedule.getRest() != null && schedule.getRest()){
//            System.out.println("该班次为休息班次！循环下一个员工!");
//            return;
//        }
//        if(schedule.getAcrossDay()){
//            //跨天存起当天数据,待后天执行
//
//            AcrossDaySchedule acrossDaySchedule = new AcrossDaySchedule();
//            acrossDaySchedule.setDate(date);
//            acrossDaySchedule.setResource(resource);
//            acrossDaySchedule.setSchedule(schedule);
//            Serializable id = acrossDayScheduleDao.save(acrossDaySchedule);
//            System.out.println("该班次为跨天班次，存起该跨天信息,id为"+id+"，待明天执行!");
//        }else{
//            //执行考勤计算
//            initDetail(resource, date, schedule);
//        }
//
//
//    }
//
//    /**
//     * 排班制
//     * @param resource
//     * @param group
//     */
//    public void arrangeScheduling(HrmResource resource,AttendanceGroup group){
//
//    }
//
//    /**
//     * 自由打卡
//     * @param resource
//     * @param group
//     */
//    public void freeScheduling(HrmResource resource, AttendanceGroup group) {
//
//    }
//
//    public void initDetail(HrmResource resource,Date date,AttendanceSchedule schedule){
//        int scheduleType = schedule.getScheduleType();
//        System.out.println("===========开始初始化detail信息，该班次属性为"+scheduleType+"班制============");
//        AttendanceDetail detail = new AttendanceDetail();
//        detail.setResource(resource);
//        detail.setSchedule(schedule);
//        detail.setDate(date);
//        detail.setShould_attendance_day(1);
//        detail.setShould_attendance_time(StringUtil.nullLong(schedule.getAttendanceTime()));
//
//        List<AttendanceRecord> records = recordDao.findByResourceAndDate(resource,date);
//        System.out.println("读取考勤原纪录!Records大小为"+records.size());
//        if(schedule.getAcrossDay()){
//            System.out.println(date.toString());
//            System.out.println(DateUtil.getNextDay(date).toString());
//            List<AttendanceRecord> records1 = recordDao.findByResourceAndDate(resource,DateUtil.getNextDay(date));
//            records.addAll(records1);
//            System.out.println("该班次为跨天，将考勤原纪录的集合加上明天的记录！Records大小为"+records.size());
//        }
//        //如果打卡数据为空，直接记录旷工,否则进行计算
//        if(records == null || records.size() < 1){
//
//            System.out.println("该员工的打卡数据为空，直接记录旷工!");
//            detail.setAttendanceType(typeDao.getMissType());
//            detail.setAbsenteeismTime(schedule.getAttendanceTime());
//            detail.setAbsenteeismCount(schedule.getScheduleType());
//            detail.setActual_attendance_time(0L);
//            detail.setActual_attendance_day(0D);
//        }else{
//            switch (scheduleType) {
//                case 0:
//                    //特殊班次，不用进行考勤
//                    System.out.println("特殊班次，不用进行考勤!");
//                    return;
//                case 1:
//                    //一天一班制
//                    System.out.println("执行一天一班制");
//                    executeAttendance(detail,schedule,schedule.getFirst_time_up().getTime(),schedule.getFirst_time_up().getTime(),schedule.getFirst_time_down().getTime(),records,"First");
//                    break;
//                case 2:
//                    //一天两班制
//                    System.out.println("执行一天二班制");
//                    long long1 = executeAttendance(detail, schedule,schedule.getFirst_time_up().getTime(),schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(),records,"First");
//                    executeAttendance(detail, schedule,long1,schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(),records,"Second");
//                    break;
//                case 3:
//                    //一天三班制
//                    System.out.println("执行一天三班制");
//                    long long2 = executeAttendance(detail, schedule, schedule.getFirst_time_up().getTime() ,schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(),records,"First");
//                    long long3 = executeAttendance(detail, schedule, long2 ,schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(),records, "Second");
//                    executeAttendance(detail, schedule,long3, schedule.getThird_time_up().getTime(), schedule.getThird_time_down().getTime(),records,"Third");
//                    break;
//            }
//            //记录明细实际出勤天数
//            if(detail.getActual_attendance_time() != null && detail.getActual_attendance_time() != 0){
//                double temp = (double)detail.getActual_attendance_time() / (double)detail.getShould_attendance_time()*100;
//                detail.setActual_attendance_day((Math.floor(temp)/100));
//            }
//        }
//
//        Serializable id = detailDao.save(detail);
//        System.out.println("生成员工明细表成功，id为"+id);
//    }
//    public long executeAttendance(AttendanceDetail detail, AttendanceSchedule schedule,long currentTime, long time_up, long time_down, List<AttendanceRecord> records,String order){
//
//        //如果为跨天班次则将上下班的时间加上一天
//        if(currentTime > ONE_DAY_TIME || time_up < currentTime){
//            time_up += ONE_DAY_TIME;
//            System.out.println("time_up打卡时段为跨天！");
//        }
//        currentTime = time_up;
//        if(currentTime > ONE_DAY_TIME || time_down < currentTime){
//            time_down += ONE_DAY_TIME;
//            System.out.println("time_down打卡时段为跨天！");
//        }
//        currentTime = time_down;
//
//        //打卡范围时间
//        long scope_up = schedule.getScope_up();
//        long scope_down = schedule.getScope_down();
//
//        //上班开始打卡时间
//        long beginTime = time_up - scope_up;
//
//        //下班结束打卡时间
//        long endTime = time_down + scope_down;
//
//        //筛选完成的信息
//        //上下班时间
//        long actual_time_up = 0;
//        long actual_time_down = 0;
//        //偏差时间
//        long offsetTime = 0;
//
//
//        //SET方法名称
//        String method_up = "set"+order+"_time_up";
//        String method_down = "set"+order+"_time_down";
//
//
//        for(AttendanceRecord record : records){
//            System.out.println("=========开始遍历该员工的打卡数据==========");
//            long time = record.getPunchTime().getTime();
//
//            //如果打卡日期大于当前明细的日期，则将打卡时间加上一天的毫秒数
//            if(record.getDate().compareTo(detail.getDate()) > 0)time += ONE_DAY_TIME;
//
//            //如果打卡时间，可能存在加班的情况
//            if (time < beginTime || time > endTime) {
//                System.out.println("打卡时间在打卡范围之外!");
//                continue;
//            }
//            //上班有效打卡时间范围
//            else if (beginTime <= time && time <= time_up) {
//                System.out.println("打卡时间在上班有效打卡范围!");
//                //因为数据都是按时间的先后顺序找出来的，而存符合的时间是存最早的那个，因此下面的可以直接actual_time_up不等于0就continue的，但为了保险起见，做一个大小判断
//                if (actual_time_up != 0) {
//                    if (actual_time_up > time) {
//                        System.out.println("读取上班打卡时间顺序出错！");
//                        actual_time_up = time;
//                    }
//                } else {
//                    actual_time_up = time;
//                }
//            }
//            //上班期间的偏差打卡
//            else if (time_up < time && time < time_down) {
//                System.out.println("打卡时间在上班时间范围!");
//                if (offsetTime != 0) {
//                    if (offsetTime > time) {
//                        offsetTime = time;
//                    }
//                }else{
//                    offsetTime = time;
//                }
//            }
//            //下班有效打卡时间范围
//            else if (time_down <= time && time <= endTime) {
//                System.out.println("打卡时间在下班有效打卡范围!");
//                if (actual_time_down != 0) {
//                    if(actual_time_down > time){
//                        actual_time_down = time;
//                    }
//                }else{
//                    actual_time_down = time;
//                }
//            }
//        }
//        System.out.println("=========遍历该员工的打卡数据结束==========");
//
//        if(actual_time_up != 0 && actual_time_down != 0){
//            //正常进行打卡的信息设置
//            detail.setAttendanceType(checkType(typeDao.getNormalType(),detail.getAttendanceType()));
//            detail.setActual_attendance_time((time_down - time_up) + StringUtil.nullLong(detail.getActual_attendance_time()));
//            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(actual_time_up));
//            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(actual_time_down));
//            System.out.println("该员工时段打卡正常");
//        }
//
//        else if (actual_time_up == 0 && actual_time_down != 0) {
//            //如果上班卡为空，下班卡不为空，
//
//            //存在偏差时间
//            if(offsetTime != 0){
//                //获取设置的迟到早退的容差时间
//                long defaultOffsetTime = time_up + StringUtil.nullLong(schedule.getOffsetTime());
//                if(offsetTime > defaultOffsetTime){
//                    //旷工信息设置
//                    detail.setAbsenteeismTime((offsetTime - time_up) + StringUtil.nullLong(detail.getAbsenteeismTime()));
//                    detail.setActual_attendance_time((time_down - offsetTime) + StringUtil.nullLong(detail.getActual_attendance_time()));
//                    detail.setAbsenteeismCount(1+StringUtil.nullInteger(detail.getAbsenteeismCount()));
//                    detail.setAttendanceType(checkType(typeDao.getMissType(),detail.getAttendanceType()));
//                }else{
//                    //迟到信息设置
//                    detail.setAttendanceType(checkType(typeDao.getLateType(),detail.getAttendanceType()));
//                    detail.setLateTime((offsetTime - time_up) + StringUtil.nullLong(detail.getLateTime()));
//                    detail.setActual_attendance_time((time_down - offsetTime) + StringUtil.nullLong(detail.getActual_attendance_time()));
//                    detail.setLateCount(1 + StringUtil.nullInteger(detail.getLateCount()));
//                }
//            }
//            //如果没有偏差时间，记录旷工信息
//            else{
//                detail.setAbsenteeismTime((time_down - time_up) + StringUtil.nullLong(detail.getAbsenteeismTime()));
//                detail.setAbsenteeismCount(1+StringUtil.nullInteger(detail.getAbsenteeismCount()));
//                detail.setAttendanceType(checkType(typeDao.getMissType(),detail.getAttendanceType()));
//                detail.setActual_attendance_time(0 + StringUtil.nullLong(detail.getActual_attendance_time()));
//            }
//            ClassUtil.invokeMethod(detail,method_up,Time.class, offsetTime == 0 ? null : new Time(offsetTime));
//            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(actual_time_down));
//            System.out.println("该员工时段上班打卡异常");
//        }
//
//        else if (actual_time_up != 0 && actual_time_down == 0) {
//            //上班卡不为空，下班卡为空
//
//            if (offsetTime != 0) {
//                long defaultOffsetTime = time_down - StringUtil.nullLong(schedule.getOffsetTime());
//                if(offsetTime < defaultOffsetTime){
//                    //旷工信息设置
//                    detail.setAbsenteeismTime((time_down - offsetTime) + StringUtil.nullLong(detail.getAbsenteeismTime()));
//                    detail.setActual_attendance_time((offsetTime - time_up) + StringUtil.nullLong(detail.getActual_attendance_time()));
//                    detail.setAbsenteeismCount(1+StringUtil.nullInteger(detail.getAbsenteeismCount()));
//                    detail.setAttendanceType(checkType(typeDao.getMissType(),detail.getAttendanceType()));
//                }else{
//                    //早退信息设置
//                    detail.setAttendanceType(checkType(typeDao.getEarlyType(),detail.getAttendanceType()));
//                    detail.setActual_attendance_time((offsetTime - time_up) + StringUtil.nullLong(detail.getActual_attendance_time()));
//                    detail.setEarlyTime((time_down - offsetTime) + StringUtil.nullLong(detail.getEarlyTime()));
//                    detail.setEarlyCount(1 + StringUtil.nullInteger(detail.getEarlyCount()));
//                }
//            }else{
//                //旷工信息设置
//                detail.setAbsenteeismTime((time_down - time_up) + StringUtil.nullLong(detail.getAbsenteeismTime()));
//                detail.setActual_attendance_time(0 + StringUtil.nullLong(detail.getActual_attendance_time()));
//                detail.setAbsenteeismCount(1+StringUtil.nullInteger(detail.getAbsenteeismCount()));
//                detail.setAttendanceType(checkType(typeDao.getMissType(),detail.getAttendanceType()));
//            }
//            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(actual_time_up));
//            ClassUtil.invokeMethod(detail,method_down,Time.class,offsetTime == 0 ? null : new Time(offsetTime));
//            System.out.println("该员工时段下班打卡异常");
//        }
//
//        else{
//            //如果上下班的卡都没打，记录出勤类型为旷工，旷工时间为班制出勤时间，旷工次数+1
//            detail.setAbsenteeismTime((time_down - time_up) + StringUtil.nullLong(detail.getAbsenteeismTime()));
//            detail.setActual_attendance_time(0 + StringUtil.nullLong(detail.getActual_attendance_time()));
//            detail.setAbsenteeismCount(1+StringUtil.nullInteger(detail.getAbsenteeismCount()));
//            detail.setAttendanceType(checkType(typeDao.getMissType(),detail.getAttendanceType()));
//            ClassUtil.invokeMethod(detail,method_up,Time.class,null);
//            ClassUtil.invokeMethod(detail,method_down,Time.class,null);
//            System.out.println("该员工时段旷工");
//        }
//        return currentTime;
//    }
//
//    /**
//     * 判断出勤类型优先级显示
//     * @param type1
//     * @param type2
//     * @return
//     */
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
//
//    public void calculateTime(AttendanceDetail detail) {
//        AttendanceSchedule schedule = detail.getSchedule();
//
//
//        long actualAttendance = 0;
//        long lateTime = 0;
//        int lateCount = 0;
//        long earlyTime = 0;
//        int earlyCount = 0;
//        long absenteeismTime = 0;
//        int absenteeismCount = 0;
//        if(detail.getFirstUpType() != null ){
//            switch (detail.getFirstUpType().getName()){
//                case "迟到":
//                    earlyTime = detail.getFirst_time_up().getTime() - schedule.getFirst_time_up().getTime();
//                    break;
//                case "旷工":
//                    absenteeismTime = schedule.getFirst_time_down().getTime() - schedule.getFirst_time_up().getTime();
//                    break;
//            }
//        }
//        if(detail.getFirstUpType().getName() == "迟到"){
//
//        } else if (detail.getFirstDownType().getName() == "旷工") {
//
//        }
//
//    }
//}
