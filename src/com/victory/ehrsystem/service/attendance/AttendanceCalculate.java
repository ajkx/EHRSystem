package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.dao.attendance.*;
import com.victory.ehrsystem.entity.attendance.*;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.util.ClassUtil;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.TimeVo;
import org.aspectj.org.eclipse.jdt.internal.compiler.codegen.AttributeNamesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

import static com.victory.ehrsystem.util.DateUtil.clearDate;

/**
 * Created by ajkx
 * Date: 2017/2/24.
 * Time:15:47
 *
 *
 * 可优化的地方
 * 1.typeDao.getXXX()应该可以配置缓存或者将attendanceType改为枚举类型
 */
@Service
public class AttendanceCalculate {

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

    @Autowired
    private LevelRecordDao levelRecordDao;

    @Autowired
    private CustomHoldayService customHoldayService;

    /**
     * 获取产生考勤结果的最后的日期的下一天，即待计算的开始日期
     * @return
     */
    public Date getRecordDate() {
        DateRecord date = dateRecordDao.getTopRecord();
        return date.getDate();
    }

    /**
     * 功能：系统定时调用考勤计算的开始方法
     */
    public void calculateAll() {
        //获取最后生成考勤记录的下一日
        Date beginDate = getRecordDate();
        //获取昨日
        Date endDate = DateUtil.getYesterday();
        List<Date> dateList = DateUtil.getDateList(beginDate, endDate);
        if (dateList.size() < 1) {
            System.out.println(beginDate.toString() + "当前日期已产生数据!");
            return;
        }

        for (Date date : dateList) {
            System.out.println("==========正在执行" + date.toString() + "日期的考勤计算");
            calculateAllByDate(date);
        }
        //更新计算考勤的最后日期
        DateRecord dateRecord = dateRecordDao.getTopRecord();
        dateRecord.setDate(DateUtil.getNextDay(endDate));
    }

    /**
     * 功能:
     *      根据日期，先执行前一个日期的已存的状态为待计算的明细（跨天）
     ** @param date
     */
    public void calculateAllByDate(Date date) {

//      先执行存起来的待计算的跨天记录
        Date beforeDate = DateUtil.getYesterday(date);
        List<AttendanceDetail> detailList = detailDao.findAcrossDayByDate(typeDao.getAcrossDayType(), beforeDate);
        for (AttendanceDetail detail : detailList) {
            calculateDetail(detail);
            detailDao.update(detail);
        }
        //计算所有在职员工
        List<HrmResource> resourceList = resourceDao.findAllWorkingAndEntryDate(date);
//        for (HrmResource resource : resourceList) {
//            calculateByDateAndResource(date, resource);
//        }
        HrmResource resource = resourceDao.get(HrmResource.class, 2212);
        calculateByDateAndResource(date,resource);

    }

    /**
     * 使用情景:
     *      系统定时进行调用的，将来可能开出来给用户触发，因为打卡记录读取完毕的时间和自动调用该方法的时间可能有时间差，
     *      所有想尽快生成数据就要提供给用户调用
     * 功能:
     *      初始化考勤明细，主要是创建明细和关联明细的班次，然后再调用calculateDetail方法计算，最后保存该明细
     *
     * @param date
     * @param resource
     */
    public void calculateByDateAndResource(Date date, HrmResource resource) {

        //判断detail是否重复
        AttendanceDetail detail = null;
        List<AttendanceDetail> detailList = detailDao.findByHrmResourceAndDate(resource, date);
        if (detailList.size() > 0) {
            detail = detailList.get(0);
        } else {
            //初始化明细数据
            detail = new AttendanceDetail();
            detail.setResource(resource);
            detail.setDate(date);
        }

        AttendanceGroup group = resource.getAttendanceGroup();

        //如果不在考勤组
        if (group == null) {
            detail.setAttendanceType(typeDao.getNotGroupType());
        }else{
            //初始化考勤班次
            AttendanceSchedule schedule = null;

            boolean flag = true;

            //根据考勤组获取对应的班次
            int groupType = group.getGroupType();
            switch (groupType) {
                case 1:
                    System.out.println("固定班制");
                    schedule = fixedScheduling(group, date);
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
            detail.setSchedule(schedule);
            //判断schedule是否为休息的班次
            if (schedule.getRest() != null && schedule.getRest()) {
                flag = false;
            }
            //判断当前时期是否为设定的假期
            Set<Date> holidays = customHoldayService.findAllDate();
            if (holidays.contains(date)) {
                flag = false;
            }

            //判断不用打卡的日期
            Set<Date> noPunchDate = group.getNoNeedPunchDate();
            if (noPunchDate.contains(date)) {
                flag = false;
            }
            //判断必须打卡的日期
            Set<Date> mustPunchDate = group.getMustPunchDate();
            if (mustPunchDate.contains(date)) {
                flag = true;
            }
            if(flag){
                calculateDetail(detail);
            }else{
                detail.setAttendanceType(typeDao.getRestType());
            }

        }
        detailDao.save(detail);
    }

    /**
     * 固定排班,主要目的是获取班次
     *
     * @param group
     */
    public AttendanceSchedule fixedScheduling(AttendanceGroup group, Date date) {
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
        return schedule;

    }

    /**
     * 排班制
     *
     * @param resource
     * @param group
     */
    public void arrangeScheduling(HrmResource resource, AttendanceGroup group) {

    }

    /**
     * 自由打卡
     *
     * @param resource
     * @param group
     */
    public void freeScheduling(HrmResource resource, AttendanceGroup group) {

    }


    /**
     * 功能:计算传入的detail，包括普通的出勤，请假，加班，统一处理
     * 使用情景:
     *  1.系统每天定时调用，对应方法 calculateByDateAndResource
     *  2.存起来待计算（跨天）的明细调用 对应方法 calculateAllByDate里计算跨天的那部分，也是系统定时调用
     *  3.计划提供的功能：让用户手动触发重新计算该明细（未实行）
     *
     * @param detail
     */
    public void calculateDetail(AttendanceDetail detail) {
        AttendanceSchedule schedule = detail.getSchedule();


        //判断是否跨天 这里因觉得没必要还去typeDao.getAcrossDay().getName(),直接用了直接量判断
        if (schedule.getAcrossDay() != null && schedule.getAcrossDay() && detail.getAttendanceType() != null && "待计算".equals(detail.getAttendanceType().getName())) {
            //设置明细数据为待计算类型（跨天）
            detail.setAttendanceType(typeDao.getAcrossDayType());
        } else {

            //初始化规定出勤的时数和天数
            detail.setShould_attendance_time(StringUtil.nullLong(schedule.getAttendanceTime()));
            detail.setShould_attendance_day(1);
            //请假或普通的考勤计算
            calculateLevelRecord(detail);
            //加班计算
            calculateOverTimeByDetail(detail);
        }

    }

    /**
     * 功能: 分不同班次计算每个班次的请假数据
     *
     * 对上方法: calculateDetail
     * 对下方法: calculateLevelTime
     *
     * @param detail
     */
    public void calculateLevelRecord(AttendanceDetail detail) {

        AttendanceSchedule schedule = detail.getSchedule();
        int scheduleType = schedule.getScheduleType();

        //值传输对象，存currentTime和levelTime
        TimeVo time = new TimeVo();
        time.setCurrentTime(schedule.getFirst_time_up().getTime());
        time.setLevelTime(0L);

        if (scheduleType > 0) {
            detail.setShould_first_time_up(schedule.getFirst_time_up());
            detail.setShould_first_time_down(schedule.getFirst_time_down());
            calculateLevelTime(detail, time, schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(), "First");
        }
        if (scheduleType > 1) {
            detail.setShould_second_time_up(schedule.getSecond_time_up());
            detail.setShould_second_time_down(schedule.getSecond_time_down());
            calculateLevelTime(detail, time, schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(), "Second");
        }
        if (scheduleType > 2) {
            detail.setThird_time_up(schedule.getThird_time_up());
            detail.setThird_time_down(schedule.getThird_time_down());
            calculateLevelTime(detail, time, schedule.getThird_time_up().getTime(), schedule.getThird_time_down().getTime(), "Third");
        }

        //重新计算出勤时间等等
        calculateTime(detail);
        detail.setActual_attendance_time(detail.getActual_attendance_time() - time.getLevelTime());
        double temp = (double) detail.getActual_attendance_time() / (double) detail.getShould_attendance_time() * 100;
        detail.setActual_attendance_day((Math.floor(temp) / 100));
    }

    /**
     * time_up time_down主要用于分班次查询相应的请假记录，并将查询的结果更新进detail里
     *
     * 向上对应方法: calculateLevelRecord
     * 向下对应方法：calculateAttendanceType
     *
     * @param detail
     * @param timeVo
     * @param time_up
     * @param time_down
     * @param order
     */
    public void calculateLevelTime(AttendanceDetail detail, TimeVo timeVo, long time_up, long time_down, String order) {
        //初始化班次的上下班时间，主要用于传递给calculateAttendanceType方法计算出勤的类型


        //存起最后一次规定的打卡时间，用于判断跨天，因判断请假的跨天和判断考勤明细的跨天是平行的，所有用两个相同的变量做重复的判断操作
        long currentTime = timeVo.getCurrentTime();
        //每个班次的请假时间,控制同一班次下有多条请假记录的合计请假时间多于班次时间
        long totalLevel = 0;

        HrmResource resource = detail.getResource();

        if (currentTime > ONE_DAY_TIME || time_up < currentTime) {
            time_up += ONE_DAY_TIME;
        }
        currentTime = time_up;
        if (currentTime > ONE_DAY_TIME || time_down < currentTime) {
            time_down += ONE_DAY_TIME;
        }
        currentTime = time_down;

        //加上8小时的时区时间，存在严重的耦合，临时解决
        long dateTime = detail.getDate().getTime()+28800000;
        //原始的上班时间
        long attendanceTime = time_down - time_up;
        time_up += dateTime;
        time_down += dateTime;

        //遍历请假记录，不status有关联，找寻所有的与打卡时间交集的记录(不管请假时间是否有交集，这方面应该放在controller输入时限制)
        List<LevelRecord> records = levelRecordDao.findByDateAndResource(new java.util.Date(time_up), new java.util.Date(time_down), resource);
        //如果上下班范围与请假没有交集则正常考勤计算
        if (records.size() < 1) {
            calculateAttendanceType(detail,time_up,time_down,order);
        } else {


            for (LevelRecord record : records) {

                //如果请假时间大于或等于出勤时间，则退出循环
                if (totalLevel >= attendanceTime) {
                    break;
                }
                //请假的开始时间和结束时间
                long beginTime = record.getDate().getTime();
                long endTime = record.getEndDate().getTime();

                long actualTimeUp = time_up;
                long actualTimeDown = time_down;

                long levelTime = 0;
                //判断上班时间与请假时间是以哪种方式交集
                //上面为班次的上下班时间 下面为请假的开始结束时间
                //第一种情况
                // |------------|
                //   |--------|
                if (time_up < beginTime && beginTime < time_down && time_up < endTime && endTime < time_down) {
                    levelTime = endTime - beginTime;
                    calculateAttendanceType(detail, time_up, time_down, order);
                }
                //第二种情况
                // |------------|
                //       |----------|
                else if (time_up < beginTime && beginTime < time_down && endTime >= time_down) {
                    levelTime = time_down - beginTime;
                    time_down = beginTime;
                    calculateAttendanceType(detail, time_up, time_down, order);
                    //设置该班次出勤属性计算z后为正常的即转为请假
                    AttendanceType type = ClassUtil.invokeMethod(detail, "get" + order + "DownType");
                    if (type != null && type.getName().equals("正常")) {
                        ClassUtil.invokeMethod(detail, "set" + order + "DownType", AttendanceType.class, typeDao.getLevelType());
                    }
                }
                //第三种情况
                //      |------------|
                // |----------|
                else if (time_up < endTime && endTime < time_down && beginTime <= time_up) {
                    levelTime = endTime - time_up;
                    time_up = endTime;
                    calculateAttendanceType(detail, time_up, time_down, order);
                    AttendanceType type = ClassUtil.invokeMethod(detail, "get" + order + "UpType");
                    if (type != null && type.getName().equals("正常")) {
                        ClassUtil.invokeMethod(detail, "set" + order + "UpType", AttendanceType.class, typeDao.getLevelType());
                    }
                }
                //第四种情况
                //     |------------|
                // |--------------------|
                else {
                    //如果请假时间为班次的上班时间，直接退出循环，因为原则上levelTime不可大于班次的时间
                    levelTime = time_down - time_up;
                    ClassUtil.invokeMethod(detail, "set" + order + "UpType", AttendanceType.class, typeDao.getLevelType());
                    ClassUtil.invokeMethod(detail, "set" + order + "DownType", AttendanceType.class, typeDao.getLevelType());
                }

                switch (record.getType().getName()) {
                    case "事假":
                        detail.setLeave_personal(StringUtil.nullLong(detail.getLeave_personal()) + levelTime);
                        break;
                    case "出差":
                        detail.setLeave_business(StringUtil.nullLong(detail.getLeave_business()) + levelTime);
                        break;
                    case "工伤":
                        detail.setLeave_injury(StringUtil.nullLong(detail.getLeave_injury()) + levelTime);
                        break;
                    case "年假":
                        detail.setLeave_annual(StringUtil.nullLong(detail.getLeave_personal()) + levelTime);
                        break;
                    case "调休":
                        detail.setLeave_rest(StringUtil.nullLong(detail.getLeave_rest()) + levelTime);
                        break;
                    case "婚假":
                        detail.setLeave_married(StringUtil.nullLong(detail.getLeave_married()) + levelTime);
                        break;
                    case "丧假":
                        detail.setLeave_funeral(StringUtil.nullLong(detail.getLeave_funeral()) + levelTime);
                        break;
                    case "产假":
                        detail.setLeave_delivery(StringUtil.nullLong(detail.getLeave_delivery()) + levelTime);
                        break;
                    case "病假":
                        detail.setLeave_sick(StringUtil.nullLong(detail.getLeave_sick()) + levelTime);
                        break;
                }
                totalLevel += levelTime;

                //添加双向关联 这里存在detail可能重复add同一个record，存在性能损耗?
                Set<LevelRecord> recordSet = detail.getLevelRecords() == null ? new HashSet<>() : detail.getLevelRecords();
                recordSet.add(record);
                detail.setLevelRecords(recordSet);

//                Set<AttendanceDetail> detailSet = record.getDetails() == null ? new HashSet<>() : record.getDetails();
//                detailSet.add(detail);
//                record.setDetails(detailSet);
                record.setStatus(OverTimeRecord.Status.success);
                levelRecordDao.update(record);

            }
        }
        timeVo.setCurrentTime(currentTime);
//        timeVo.setLevelTime(totalLevel);
    }

    /**
     * 功能: 分班次根据不同的打卡记录计算该班次的上下班打卡时间和打卡结果
     *
     * 对上方法: calculateLevelTime
     *
     * 缺陷: 目前用反射来动态识别不同的班次，并且分支语句过多，效率不高
     * @param detail
     * @param time_up
     * @param time_down
     * @param order
     */
    public void calculateAttendanceType(AttendanceDetail detail, long time_up, long time_down, String order) {



        //初始化需要的变量
        AttendanceSchedule schedule = detail.getSchedule();
        int scheduleType = schedule.getScheduleType();
        if (scheduleType > 0) {
            detail.setShould_first_time_up(schedule.getFirst_time_up());
            detail.setShould_first_time_down(schedule.getFirst_time_down());
        }

        if (scheduleType > 1) {
            detail.setShould_second_time_up(schedule.getSecond_time_up());
            detail.setShould_second_time_down(schedule.getSecond_time_down());
        }

        if (scheduleType > 2) {
            detail.setShould_third_time_up(schedule.getThird_time_up());
            detail.setShould_third_time_down(schedule.getThird_time_down());
        }
        HrmResource resource = detail.getResource();
        long scope_up = schedule.getScope_up();
        long scope_down = schedule.getScope_down();

        //反射的SET方法名称
        String method_up = "set" + order + "_time_up";
        String method_down = "set" + order + "_time_down";

        String method_type_up = "set" + order + "UpType";
        String method_type_down = "set" + order + "DownType";

        //范围打卡时间
        long beginTime = time_up - scope_up;
        long endTime = time_down + scope_down;

        //实际上下班卡
        long actualTimeUp = 0;
        long actualTimeDown = 0;
        //偏差时间集合
        List<Long> offsetTime = new ArrayList<>();

        List<AttendanceRecord> records = recordDao.findByResourceAndDate(resource, new java.util.Date(beginTime), new java.util.Date(endTime));
        for (AttendanceRecord record : records) {
            long time = record.getDate().getTime();
            //上班有效打卡时间范围
            if (beginTime <= time && time <= time_up) {
                System.out.println("打卡时间在上班有效打卡范围!");
                //因为数据都是按时间的先后顺序找出来的，而存符合的时间是存最早的那个，因此下面的可以直接actual_time_up不等于0就continue的，但为了保险起见，做一个大小判断
                if (actualTimeUp != 0) {
                    if (actualTimeUp > time) {
                        System.out.println("读取上班打卡时间顺序出错！");
                        actualTimeUp = time;
                    }
                } else {
                    actualTimeUp = time;
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
                if (actualTimeDown != 0) {
                    if (actualTimeDown > time) {
                        actualTimeDown = time;
                    }
                } else {
                    actualTimeDown = time;
                }
            }
        }

        //升序列表
        Collections.sort(offsetTime);
        long earliestTime = 0;
        long latestTime = 0;
        //获取最早的那个偏差时间
        if (offsetTime.size() > 0) {
            earliestTime = offsetTime.get(0);
            latestTime = offsetTime.get(offsetTime.size() - 1);
        }

        //获取设置的迟到早退的容差时间
        long defaultUpOffsetTime = time_up + StringUtil.nullLong(schedule.getOffsetTime());
        long defaultDownOffsetTime = time_down - StringUtil.nullLong(schedule.getOffsetTime());
        if (actualTimeUp != 0 && actualTimeDown != 0) {
            //正常进行打卡的信息设置
            ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getNormalType());
            ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getNormalType());
            ClassUtil.invokeMethod(detail, method_up, Time.class, new Time(actualTimeUp));
            ClassUtil.invokeMethod(detail, method_down, Time.class, new Time(actualTimeDown));
            System.out.println("该员工时段打卡正常");
        } else if (actualTimeUp == 0 && actualTimeDown != 0) {
            //如果上班卡为空，下班卡不为空，

            //存在偏差时间
            if (offsetTime.size() > 0) {

                if (earliestTime > defaultUpOffsetTime) {
                    //旷工信息设置
                    ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getMissType());
                } else {
                    //迟到信息设置
                    ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getLateType());
                }
                ClassUtil.invokeMethod(detail, method_up, Time.class, new Time(earliestTime));
            }
            //如果没有偏差时间，记录旷工信息
            else {
                ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getMissType());
            }
            ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getNormalType());
            ClassUtil.invokeMethod(detail, method_down, Time.class, new Time(actualTimeDown));
            System.out.println("该员工时段上班打卡异常");
        } else if (actualTimeUp != 0 && actualTimeDown == 0) {
            //上班卡不为空，下班卡为空

            if (offsetTime.size() > 0) {

                if (latestTime < defaultDownOffsetTime) {
                    //旷工信息设置
                    ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getMissType());
                } else {
                    //早退信息设置
                    ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getEarlyType());
                }
                ClassUtil.invokeMethod(detail, method_down, Time.class, new Time(latestTime));
            } else {
                //旷工信息设置
                ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getMissType());
            }
            ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getNormalType());
            ClassUtil.invokeMethod(detail, method_up, Time.class, new Time(actualTimeUp));
            System.out.println("该员工时段下班打卡异常");
        } else {
            //如果偏差时间大于等于2个，则判断最早和最迟的时间来记录是迟到还是早退还是旷工，都要把打卡时间存进实际打卡时间
            if (offsetTime.size() > 1) {
                if (earliestTime > defaultUpOffsetTime) {
                    ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getMissType());
                } else {
                    ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getLateType());
                }
                if (latestTime < defaultDownOffsetTime) {
                    ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getMissType());
                } else {
                    ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getEarlyType());
                }
                ClassUtil.invokeMethod(detail, method_up, Time.class, new Time(earliestTime));
                ClassUtil.invokeMethod(detail, method_down, Time.class, new Time(latestTime));
            }
            //如果只有1个时间，则先根据默认偏差设置来判断迟到还是旷工，只存上班实际打卡时间
            else if (offsetTime.size() == 1) {
                if (earliestTime > defaultUpOffsetTime) {
                    ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getMissType());
                } else {
                    ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getLateType());
                }
                ClassUtil.invokeMethod(detail, method_up, Time.class, new Time(earliestTime));
                ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getMissType());
            }
            //偏差时间为空，直接设为旷工
            else {
                ClassUtil.invokeMethod(detail, method_type_up, AttendanceType.class, typeDao.getMissType());
                ClassUtil.invokeMethod(detail, method_type_down, AttendanceType.class, typeDao.getMissType());
            }
        }
    }


    /**
     * 功能:根据每个班次的打卡时间和打卡结果计算迟到，早退，旷工，出勤的具体时间
     *
     * 对上方法: calculateLevelRecord
     *
     * @param detail
     */
    public void calculateTime(AttendanceDetail detail) {
        AttendanceSchedule schedule = detail.getSchedule();
        //如果为休息班次的
        long lateTime = 0;
        int lateCount = 0;
        long earlyTime = 0;
        int earlyCount = 0;
        long absenteeismTime = 0;
        int absenteeismCount = 0;

        //三个班次的迟到，早退，旷工时间
        long firstLateTime = 0L;
        long firstEarlyTime = 0L;
        long firstAbsenteeismTime = 0L;

        long secondLateTime = 0L;
        long secondEarlyTime = 0L;
        long secondAbsenteeismTime = 0L;

        long thirdLateTime = 0L;
        long thirdEarlyTime = 0L;
        long thirdAbsenteeismTime = 0L;

        //计算第一次上班
        if (detail.getFirstUpType() != null) {

            long settingUp = detail.getShould_first_time_up().getTime();
            long settingDown = detail.getShould_first_time_down().getTime();

            Time actualTime = detail.getFirst_time_up();
            if (schedule.getAcrossDay()) {
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getFirstUpType().getName()) {
                case "迟到":
                    firstLateTime = actualTime.getTime() - settingUp;
                    lateCount++;
                    break;
                case "旷工":
                    //没打卡
                    if (actualTime == null) {
                        firstAbsenteeismTime = settingDown - settingUp;
                    } else {
                        //打卡超过限定的迟到时间
                        firstAbsenteeismTime = actualTime.getTime() - settingUp;
                    }
                    absenteeismCount++;
                    break;
            }
        }
        //计算第一次下班
        if (detail.getFirstDownType() != null) {

            long settingUp = detail.getShould_first_time_up().getTime();
            long settingDown = detail.getShould_first_time_down().getTime();

            Time actualTime = detail.getFirst_time_down();
            if (schedule.getAcrossDay()) {
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getFirstDownType().getName()) {
                case "早退":
                    firstEarlyTime = returnTime(settingDown) - returnTime(actualTime.getTime());
                    if (detail.getFirst_time_up() == null) {
                        firstAbsenteeismTime = settingDown - settingUp - firstEarlyTime;
                    }
                    earlyCount++;
                    break;
                case "旷工":
                    if (actualTime == null) {
                        firstAbsenteeismTime = settingDown - settingUp - firstLateTime;
                    } else {
                        if (detail.getFirst_time_up() == null) {
                            firstAbsenteeismTime = settingDown - settingUp;
                        } else {
                            firstAbsenteeismTime += returnTime(settingDown) - returnTime(actualTime.getTime());
                        }
                    }
                    absenteeismCount++;
                    break;
            }
        }


        //计算第二次上班
        if (detail.getSecondUpType() != null) {

            long settingUp = detail.getShould_second_time_up().getTime();
            long settingDown = detail.getShould_second_time_down().getTime();

            Time actualTime = detail.getSecond_time_up();
            if (schedule.getAcrossDay()) {
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getSecondUpType().getName()) {
                case "迟到":
                    secondLateTime += returnTime(actualTime.getTime()) - returnTime(settingUp);
                    lateCount++;
                    break;
                case "旷工":
                    if (actualTime == null) {
                        secondAbsenteeismTime = settingDown - settingUp;
                    } else {
                        secondAbsenteeismTime = returnTime(actualTime.getTime()) - returnTime(settingUp);
                    }
                    absenteeismCount++;
                    break;
            }
        }

        //计算第二次下班
        if (detail.getSecondDownType() != null) {

            long settingUp = detail.getShould_second_time_up().getTime();
            long settingDown = detail.getShould_second_time_down().getTime();

            Time actualTime = detail.getSecond_time_down();
            if (schedule.getAcrossDay()) {
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getSecondDownType().getName()) {
                case "早退":
                    secondEarlyTime = returnTime(settingDown) - returnTime(actualTime.getTime());
                    if (detail.getSecond_time_up() == null) {
                        secondAbsenteeismTime = settingDown - settingUp - secondEarlyTime;
                    }
                    earlyCount++;
                    break;
                case "旷工":
                    if (actualTime == null) {
                        secondAbsenteeismTime = settingDown - settingUp - secondLateTime;
                    } else {
                        if (detail.getSecond_time_up() == null) {
                            secondAbsenteeismTime = settingDown - settingUp;
                        } else {
                            secondAbsenteeismTime += returnTime(actualTime.getTime()) - returnTime(settingDown);
                        }
                    }
                    absenteeismCount++;
                    break;
            }
        }

        //计算第三次上班
        if (detail.getThirdUpType() != null) {

            long settingUp = detail.getShould_third_time_up().getTime();
            long settingDown = detail.getShould_third_time_down().getTime();

            Time actualTime = detail.getThird_time_up();
            if (schedule.getAcrossDay()) {
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getThirdUpType().getName()) {
                case "迟到":
                    thirdLateTime = returnTime(actualTime.getTime()) - returnTime(settingUp);
                    lateCount++;
                    break;
                case "旷工":
                    if (actualTime == null) {
                        thirdAbsenteeismTime = settingDown - settingUp;
                    } else {
                        thirdAbsenteeismTime = returnTime(actualTime.getTime()) - returnTime(settingUp);
                    }
                    absenteeismCount++;
                    break;
            }
        }

        //计算第三次下班
        if (detail.getThirdDownType() != null) {

            long settingUp = detail.getShould_third_time_up().getTime();
            long settingDown = detail.getShould_third_time_down().getTime();

            Time actualTime = detail.getThird_time_down();
            if (schedule.getAcrossDay()) {
                if (settingDown < settingUp) {
                    settingDown += ONE_DAY_TIME;
                }
            }

            switch (detail.getThirdDownType().getName()) {
                case "早退":
                    thirdEarlyTime = returnTime(settingDown) - returnTime(actualTime.getTime());
                    if (detail.getThird_time_up() == null) {
                        thirdAbsenteeismTime = settingDown - settingUp - thirdEarlyTime;
                    }
                    earlyCount++;
                    break;
                case "旷工":
                    if (actualTime == null) {
                        thirdAbsenteeismTime = settingDown - settingUp - thirdLateTime;
                    } else {
                        if (detail.getThird_time_up() == null) {
                            thirdAbsenteeismTime = settingDown - settingUp;
                        } else {
                            thirdAbsenteeismTime += returnTime(settingDown) - returnTime(actualTime.getTime());
                        }
                    }
                    absenteeismCount++;
                    break;
            }
        }

        lateTime = firstLateTime + secondLateTime + thirdLateTime;
        earlyTime = firstEarlyTime + secondEarlyTime + thirdEarlyTime;
        absenteeismTime = firstAbsenteeismTime + secondAbsenteeismTime + thirdAbsenteeismTime;

        detail.setLateCount(lateCount);
        detail.setLateTime(lateTime);

        detail.setEarlyCount(earlyCount);
        detail.setEarlyTime(earlyTime);

        detail.setAbsenteeismCount(absenteeismCount);
        detail.setAbsenteeismTime(absenteeismTime);

        if (earlyCount == 0 && lateCount == 0 && absenteeismCount == 0) {
            detail.setAttendanceType(typeDao.getNormalType());
        } else {
            detail.setAttendanceType(typeDao.getAbnormalType());
        }

        //统计请假时间
        long levelTime = StringUtil.nullLong(detail.getLeave_annual())
                + StringUtil.nullLong(detail.getLeave_sick())
                + StringUtil.nullLong(detail.getLeave_delivery())
                + StringUtil.nullLong(detail.getLeave_funeral())
                + StringUtil.nullLong(detail.getLeave_married())
                + StringUtil.nullLong(detail.getLeave_business())
                + StringUtil.nullLong(detail.getLeave_injury())
                + StringUtil.nullLong(detail.getLeave_personal())
                + StringUtil.nullLong(detail.getLeave_rest());

        detail.setActual_attendance_time(schedule.getAttendanceTime() - lateTime - earlyTime - absenteeismTime - levelTime);

    }


    /**
     * 功能：计算传入的detail对应的加班记录，并计算加班结果
     *
     * 对上方法: calculateDetail
     *
     * @param detail
     */
    public void calculateOverTimeByDetail(AttendanceDetail detail) {
        java.util.Date endDate = DateUtil.getLastTimeInDay(detail.getDate());
        List<OverTimeRecord> overTimeRecords = overTimeDao.findByDateAndResource(detail.getDate(), endDate, detail.getResource());
        OverTimeSetting setting = overTimeSettingDao.get(OverTimeSetting.class, 1);
        long offsetTimeUp = setting.getOffsetTimeUp();
        long offsetTimeDown = setting.getOffsetTimeDown();
        int calculateType = setting.getCalculateType();

        //与对应的加班记录做关联
        Set<OverTimeRecord> recordSet = detail.getOverTimeRecords() == null ? new HashSet<>() : detail.getOverTimeRecords();

        //判断是否连班 获取当天班次的最后一班
        AttendanceSchedule schedule = detail.getSchedule();
        long lastTimeUp = 0;
        boolean isLink = false;
        int type = schedule.getScheduleType();
        switch (type) {
            case 1:
                lastTimeUp = schedule.getFirst_time_down().getTime();
                break;
            case 2:
                lastTimeUp = schedule.getSecond_time_down().getTime();
                break;
            case 3:
                lastTimeUp = schedule.getThird_time_down().getTime();
                break;
        }

        for (OverTimeRecord record : overTimeRecords) {
            long timeUp = record.getDate().getTime();
            long timeDown = record.getDate().getTime();

            //因为可能加班下班的打卡时间超出原申请的结束时间，所以将endDate设为加班结束时间所在日期的23:59:59
            java.util.Date recordLastTime = DateUtil.getLastTimeInDay(record.getDate());
            long beginTimeUp = timeUp - offsetTimeUp;
            long endTimeDown = timeDown + offsetTimeDown;
            //目前连班的判断是加班开始时间必须等于最后一个班次的时间才能识别为连班
            if (lastTimeUp == timeUp) {
                isLink = true;
            }

            //符合条件的打卡集合
            List<Long> timeList = new ArrayList<>();

            //获取处于 beginTimeUp与结束时间所在日期的最后时刻之间的打卡记录
            List<AttendanceRecord> records = recordDao.findByResourceAndDate(detail.getResource(),
                    new java.util.Date(beginTimeUp), recordLastTime);

            for (AttendanceRecord attendanceRecord : records) {
                long time = attendanceRecord.getDate().getTime();
                if(isLink) {
                    if (time >= timeUp && time <= endTimeDown) {
                        timeList.add(time);
                    }
                }else {
                    if (time >= beginTimeUp && time <= endTimeDown) {
                        timeList.add(time);
                    }
                }
            }
            Collections.sort(timeList);
            long actualUp = 0;
            long actualDown = 0;
            int timeSize = timeList.size();


            if(timeSize < 1) {
                record.setStatus(OverTimeRecord.Status.abnormal);
                record.setActualCount(0L);
                record.setRemark("无相应打卡数据");
            } else if (timeSize == 1) {
                if (isLink) {
                    //连班
                    actualUp = timeUp;
                    actualDown = timeList.get(0);
                    record.setTimeUp(new java.util.Date(actualUp));
                    record.setTimeDown(new java.util.Date(actualDown));
                    record.setActualCount(actualDown - actualUp);
                } else {
                    actualUp = timeList.get(0);
                    record.setTimeUp(new java.util.Date(actualUp));
                    record.setActualCount(0L);
                    record.setStatus(OverTimeRecord.Status.abnormal);
                    record.setRemark("下班卡为空");
                }
            } else {
                if (isLink) {
                    actualUp = timeUp;
                } else {
                    actualUp = timeList.get(0);
                }

                actualDown = timeList.get(timeSize - 1);
                record.setTimeUp(new java.util.Date(actualUp));
                record.setTimeDown(new java.util.Date(actualDown));

                //实际加班时间
                long offsetTime = actualDown - timeUp;
                //如果实际加班时数大于申请加班时数
                if (offsetTime >= record.getCount()) {
                    record.setStatus(OverTimeRecord.Status.success);
                    //按登记的时间算加班时间
                    if (setting.getCalculateType() == 1) {
                        record.setActualCount(record.getCount());
                    }
                    //按打卡时间计算
                    else if (setting.getCalculateType() == 2) {
                        record.setActualCount(offsetTime);
                    }
                } else {
                    record.setStatus(OverTimeRecord.Status.abnormal);
                    record.setActualCount(offsetTime);
                    record.setRemark("实际加班时间小于申请加班时间!");
                }
            }
            //将请假记录设为连班状态，主要用于之后的判断

            record.setLink(isLink);
            //更新detail里的加班数据
            updateDetailByOverTimeRecord(detail,record);
            recordSet.add(record);
            overTimeDao.update(record);
        }
        detail.setOverTimeRecords(recordSet);
        detailDao.update(detail);

    }

    /**
     * 功能：根据加班的数据来更新考勤明细的加班时数
     *
     * 使用情景：一个是正常的加班计算，一个是用户进行加班异常的修改触发
     *
     * 上层方法：calculateOverTimeByDetail    ，  updateRecord
     * @param detail
     * @param record
     */
    public void updateDetailByOverTimeRecord(AttendanceDetail detail, OverTimeRecord record) {
        boolean isLink = record.getLink();

        if(isLink){
            //连班必定为平时加班
            detail.setOvertime_normal(StringUtil.nullLong(detail.getOvertime_normal()) + record.getActualCount());
            switch (detail.getSchedule().getScheduleType()) {
                case 1:
                    detail.setFirst_time_down(new Time(record.getTimeUp().getTime()));
                    detail.setFirstDownType(typeDao.getNormalType());
                    break;
                case 2:
                    detail.setSecond_time_down(new Time(record.getTimeUp().getTime()));
                    detail.setSecondDownType(typeDao.getNormalType());
                    break;
                case 3:
                    detail.setThird_time_down(new Time(record.getTimeUp().getTime()));
                    detail.setThirdDownType(typeDao.getNormalType());
                    break;
            }
        }else{
            switch (record.getType()) {
                case 1:
                    detail.setOvertime_normal(StringUtil.nullLong(detail.getOvertime_normal()) + record.getActualCount());
                    break;
                case 2:
                    detail.setOvertime_weekend(StringUtil.nullLong(detail.getOvertime_weekend()) + record.getActualCount());
                    break;
                case 3:
                    detail.setOvertime_festival(StringUtil.nullLong(detail.getOvertime_festival()) + record.getActualCount());
                    break;
            }
        }
        //双方添加关联
        record.setDetail(detail);

    }

    public long returnTime(long l) {
        return l > ONE_DAY_TIME ? l%ONE_DAY_TIME : l;
    }
}
