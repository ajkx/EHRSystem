package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.dao.attendance.*;
import com.victory.ehrsystem.entity.attendance.*;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.util.ClassUtil;
import com.victory.ehrsystem.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx
 * Date: 2016/12/14.
 * Time:15:41
 */
@Service
public class AttendanceManager {

    @Autowired
    private AttendanceDetailDao attendanceDetailDao;
    @Autowired
    private AttendanceRecordDao attendanceRecordDao;
    @Autowired
    private AttendanceScheduleDao attendanceScheduleDao;
    @Autowired
    private AttendanceScheduleInfoDao attendanceScheduleInfoDao;
    @Autowired
    private AttendanceTypeDao attendanceTypeDao;

    @Autowired
    private HrmResourceDao hrmResourceDao;


    /**
     * 正常班次的自动打卡，都是读取昨天的信息
     */
    public void autoAttendance() {
        //yyyy-mm-dd格式日期
        Date date = DateUtil.getYesterday();
        List<HrmResource> resources = hrmResourceDao.findAllWorking();
        //遍历在职人员
        for (HrmResource resource : resources) {
            List<AttendanceScheduleInfo> temp = attendanceScheduleInfoDao.findOneByNameAndDate(resource, date);
            AttendanceSchedule schedule = temp.get(0).getSchedule();
            //判断班次是否为跨天，是的话就continue
            if(schedule.getAcrossDay()){
                continue;
            }
            //获取班次类型
            int scheduleType = schedule.getScheduleType();

            //初始化明细数据
            AttendanceDetail detail = new AttendanceDetail();
            detail.setResourceid(resource);
            detail.setDate(date);
            detail.setShould_attendance_time(schedule.getAttendanceTime());
            detail.setShould_attendance_day(1);

            //特殊班次不用进行考勤
            if (scheduleType == 0) {
                continue;
            } else if (scheduleType == 1) {
                //一天一班制
                executeAttendance(detail, schedule, schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(),"First");
            } else if (scheduleType == 2) {
                //一天两班制
                executeAttendance(detail, schedule, schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(),"First");
                executeAttendance(detail, schedule, schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(),"Second");
            } else if (scheduleType == 3) {
                //一天三班制
                executeAttendance(detail, schedule, schedule.getFirst_time_up().getTime(), schedule.getFirst_time_down().getTime(),"First");
                executeAttendance(detail, schedule, schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(), "Second");
                executeAttendance(detail, schedule, schedule.getSecond_time_up().getTime(), schedule.getSecond_time_down().getTime(),"Third");
            }
            //记录明细实际出勤天数
            if(detail.getActual_attendance_time() != 0){
                detail.setActual_attendance_day((int) (detail.getActual_attendance_time() / detail.getShould_attendance_time()));
            }
        }
    }

    /**
     * 分班次执行AttendanceDetail的插入数据
     * @param detail
     * @param schedule
     * @param time_up(每个班制的上班时间)
     * @param time_down(每个班制的下班时间)
     * @param order(调用的第几次的SET方法名,首页母要大写)
     */
    private void executeAttendance(AttendanceDetail detail,AttendanceSchedule schedule,long time_up,long time_down,String order){
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
        long offsetTime = 0;


        //SET方法名称
        String method_up = "set"+order+"_time_up";
        String method_down = "set"+order+"_time_down";


        //遍历该员工当天的所有打卡记录
        List<AttendanceRecord> records = attendanceRecordDao.findByResourceAndDate(detail.getResourceid(), detail.getDate());
        for(AttendanceRecord record : records){
            long time = record.getPunchTime().getTime();
            //如果打卡时间，可能存在加班的情况
            if (time < beginTime || time > endTime) {
                continue;
            }
            //上班有效打卡时间范围
            else if (beginTime <= time && time < time_up) {
                //因为数据都是按时间的先后顺序找出来的，而存符合的时间是存最早的那个，因此下面的可以直接first_time_up不等于0就continue的，但为了保险起见，做一个大小判断
                if (actual_time_up != 0) {
                    if (actual_time_up < time) {
                        actual_time_up = time;
                    }
                } else {
                    actual_time_up = time;
                }
            }
            //上班期间的偏差打卡
            else if (time_up <= time && time < time_down) {
                if (offsetTime != 0) {
                    if (offsetTime < time) {
                        offsetTime = time;
                    }
                }else{
                    offsetTime = time;
                }
            }
            //下班有效打卡时间范围
            else if (time_down <= time && time < endTime) {
                if (actual_time_down != 0) {
                    if(actual_time_down < time){
                        actual_time_down = time;
                    }
                }else{
                    actual_time_down = time;
                }
            }
        }


        if(actual_time_up != 0 && actual_time_down != 0){
            //正常进行打卡的信息设置
            detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,1));
            detail.setActual_attendance_time((time_down - time_up)/6000 + detail.getActual_attendance_time());
            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(actual_time_up));
            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(actual_time_down));
        }

        else if (actual_time_up == 0 && actual_time_down != 0) {
            //如果上班卡为空，下班卡不为空，

            //存在偏差时间
            if(offsetTime != 0){
                long defaultOffsetTime = time_up + schedule.getOffsetTime();
                if(offsetTime > defaultOffsetTime){
                    //旷工信息设置
                    detail.setAbsenteeismTime((offsetTime - defaultOffsetTime) + detail.getAbsenteeismTime());
                    detail.setAbsenteeismCount(1);
                    detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,3));
                }else{
                    //迟到信息设置
                    detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,4));
                    detail.setLateTime((offsetTime - time_up) + detail.getLateTime());
                    detail.setLateCount(1 + detail.getLateCount());
                }
            }
            //如果没有偏差时间，记录旷工信息
            else{
                detail.setAbsenteeismTime((time_down - time_up) + detail.getAbsenteeismTime());
                detail.setAbsenteeismCount(1);
                detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,3));
            }
            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(offsetTime));
            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(actual_time_down));
        }

        else if (actual_time_up != 0 && actual_time_down == 0) {
            //上班卡不为空，下班卡为空

            if (offsetTime != 0) {
                long defaultOffsetTime = time_down - schedule.getOffsetTime();
                if(offsetTime < defaultOffsetTime){
                    //旷工信息设置
                    detail.setAbsenteeismTime((defaultOffsetTime - offsetTime) + detail.getAbsenteeismTime());
                    detail.setAbsenteeismCount(1);
                    detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,3));
                }else{
                    //早退信息设置
                    detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,2));
                    detail.setEarlyTime((time_down - offsetTime) + detail.getEarlyTime());
                    detail.setEarlyCount(1 + detail.getEarlyCount());
                }
            }else{
                //旷工信息设置
                detail.setAbsenteeismTime((time_down - time_up) + detail.getAbsenteeismTime());
                detail.setAbsenteeismCount(1);
                detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,3));
            }
            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(actual_time_up));
            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(offsetTime));
        }

        else{
            //如果上下班的卡都没打，记录出勤类型为旷工，旷工时间为班制出勤时间，旷工次数+1
            detail.setAbsenteeismTime((time_down - time_up) + detail.getAbsenteeismTime());
            detail.setAbsenteeismCount(1);
            detail.setAttendanceType(attendanceTypeDao.get(AttendanceType.class,3));
            ClassUtil.invokeMethod(detail,method_up,Time.class,new Time(0));
            ClassUtil.invokeMethod(detail,method_down,Time.class,new Time(0));
        }

    }

    /**
     * 跨天自动考勤计算
     */
    private void autoAttendanceForAcrossDay(){
        Date yesterday = DateUtil.getYesterday();
        Date beforeYesterday = DateUtil.getBeforeYesterday();
        List<HrmResource> resources = hrmResourceDao.findAllWorking();
        for (HrmResource resource : resources) {
           List<AttendanceScheduleInfo> temp = attendanceScheduleInfoDao.findOneByNameAndDate(resource, yesterday);
            AttendanceSchedule schedule = temp.get(0).getSchedule();
            //不是跨天的continue
            if(!schedule.getAcrossDay()){
                continue;
            }
            //获取班次类型
            int scheduleType = schedule.getScheduleType();

            //特殊班次不用进行考勤
            if (scheduleType == 0) {
                continue;
            } else if (scheduleType == 1) {
                AttendanceDetail detail = new AttendanceDetail();
                detail.setResourceid(resource);
                detail.setDate(yesterday);
                detail.setShould_attendance_time(schedule.getAttendanceTime());
                oneScheduleAttendance(resource, yesterday, schedule);
            } else if (scheduleType == 2) {
                twoScheduleAttendance(resource, yesterday, schedule);
            } else if (scheduleType == 3) {
                threeScheduleAttendance(resource, yesterday, schedule);
            }
        }

    }




    //一班制
    private void oneScheduleAttendance(HrmResource resource, java.sql.Date date, AttendanceSchedule schedule) {
        //上下班规定时间
        long first_time_up_long = schedule.getFirst_time_up().getTime();
        long first_time_down_long = schedule.getFirst_time_down().getTime();
        //打卡范围时间
        long scope_up = schedule.getScope_up();
        long scope_down = schedule.getScope_down();

        //上班开始打卡时间
        long first_begin = first_time_up_long - scope_up;

        //下班结束打卡时间
        long first_end = first_time_down_long + scope_down;

        //筛选完成的信息
        //上下班时间
        long first_time_up = 0;
        long first_time_down = 0;
        //偏差时间
        long offsetTime = 0;


        //遍历该员工当天的所有打卡记录
        List<AttendanceRecord> records = attendanceRecordDao.findByResourceAndDate(resource, date);
//        for (AttendanceRecord record : records) {
//
//            long time = record.getPunchTime().getTime();
//            //如果打卡时间，可能存在加班的情况
//            if (time < first_begin || time > first_end) {
//                continue;
//            }
//            //上班有效打卡时间范围
//            else if (first_begin <= time && time < first_time_up_long) {
//                //因为数据都是按时间的先后顺序找出来的，而存符合的时间是存最早的那个，因此下面的可以直接first_time_up不等于0就continue的，但为了保险起见，做一个大小判断
//                if (first_time_up != 0) {
//                    if (first_time_up < time) {
//                        first_time_up = time;
//                    }
//                } else {
//                    first_time_up = time;
//                }
//            }
//            //上班期间的偏差打卡
//            else if (first_time_up_long <= time && time < first_time_down_long) {
//                if (offsetTime != 0) {
//                    if (offsetTime < time) {
//                        offsetTime = time;
//                    }
//                }else{
//                    offsetTime = time;
//                }
//            }
//            //下班有效打卡时间范围
//            else if (first_time_down_long <= time && time < first_end) {
//                if (first_time_down != 0) {
//                    if(first_time_down < time){
//                        first_time_down = time;
//                    }
//                }else{
//                    first_time_down = time;
//                }
//            }
//        }

        //保存该条纪录
        AttendanceDetail detail = new AttendanceDetail();
        detail.setResourceid(resource);
        detail.setDate(date);
        detail.setFirst_time_up(new Time(first_time_up));
        detail.setFirst_time_down(new Time(first_time_down));
    }

    //两班制
    private void twoScheduleAttendance(HrmResource resource, java.sql.Date date, AttendanceSchedule schedule) {
        //遍历该员工当天的所有打卡记录
        List<AttendanceRecord> records = attendanceRecordDao.findByResourceAndDate(resource, date);
        for (AttendanceRecord record : records) {
            long time = record.getPunchTime().getTime();
        }
    }

    //三班制
    private void threeScheduleAttendance(HrmResource resource, java.sql.Date date, AttendanceSchedule schedule) {
        //遍历该员工当天的所有打卡记录
        List<AttendanceRecord> records = attendanceRecordDao.findByResourceAndDate(resource, date);
        for (AttendanceRecord record : records) {
            long time = record.getPunchTime().getTime();
        }
    }

}
