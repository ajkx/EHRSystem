package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.hrm.HrmResource;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * 每人每天的打卡明细表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:35
 */
@Entity
public class AttendanceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cardNo")
    private String card;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId",nullable = false)
    private HrmResource resourceId;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "scheduleId")
    private AttendanceSchedule schedule;
    //日期
    @Column
    private Date date;

    //第一次上班打卡时间
    @Column
    private Time first_time_up;

    //第一次下班打卡时间
    @Column
    private Time first_time_down;

    //第二次上班打卡时间
    @Column
    private Time second_time_up;

    //第二次下班打卡时间
    @Column
    private Time second_time_down;

    //第三次上班打卡时间
    @Column
    private Time third_time_up;

    //第三次下班打卡时间
    @Column
    private Time third_time_down;

    //迟到时间
    @Column
    private Long lateTime;

    //迟到次数
    @Column
    private Integer lateCount;

    //早退时间
    @Column
    private Long earlyTime;

    //早退次数
    @Column
    private Integer earlyCount;

    //旷工时间
    @Column
    private Long absenteeismTime;

    //旷工次数
    @Column
    private Integer absenteeismCount;

    //平时加班时间
    @Column
    private Long overtime_normal;

    //周末加班时间
    @Column
    private Long overtime_weekend;

    //节日加班时间
    @Column
    private Long overtime_festival;

    //事假
    @Column
    private Float leave_personal;

    //病假
    @Column
    private Float leave_sick;

    //出差
    @Column
    private Float leave_business;

    //工伤
    @Column
    private Float leave_injury;

    //产假
    @Column
    private Float leave_delivery;

    //婚假
    @Column
    private Float leave_married;

    //丧假
    @Column
    private Float leave_funeral;

    //年假
    @Column
    private Float leave_annual;

    //规定出勤小时
    @Column
    private Float should_attendance_time;

    //规定出勤天数
    @Column
    private Integer should_attendance_day = 1;

    //实际出勤小时
    @Column
    private Float actual_attendance_time;

    //实际出勤天数
    @Column
    private Integer actual_attendance_day;

    //出勤类别
    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="typeId")
    private AttendanceType attendanceType;

    public AttendanceDetail() {
    }

    public AttendanceDetail(String card, HrmResource resourceid, Date date, Time first_time_up, Time first_time_down, Time second_time_up, Time second_time_down, Time third_time_up, Time third_time_down, long lateTime, int lateCount, long earlyTime, int earlyCount, long absenteeismTime, int absenteeismCount, long overtime_normal, long overtime_weekend, long overtime_festival, float leave_personal, float leave_sick, float leave_business, float leave_injury, float leave_delivery, float leave_married, float leave_funeral, float leave_annual, float should_attendance_time, int should_attendance_day, float actual_attendance_time, int actual_attendance_day, AttendanceType attendanceType) {
        this.card = card;
        this.resourceId = resourceid;
        this.date = date;
        this.first_time_up = first_time_up;
        this.first_time_down = first_time_down;
        this.second_time_up = second_time_up;
        this.second_time_down = second_time_down;
        this.third_time_up = third_time_up;
        this.third_time_down = third_time_down;
        this.lateTime = lateTime;
        this.lateCount = lateCount;
        this.earlyTime = earlyTime;
        this.earlyCount = earlyCount;
        this.absenteeismTime = absenteeismTime;
        this.absenteeismCount = absenteeismCount;
        this.overtime_normal = overtime_normal;
        this.overtime_weekend = overtime_weekend;
        this.overtime_festival = overtime_festival;
        this.leave_personal = leave_personal;
        this.leave_sick = leave_sick;
        this.leave_business = leave_business;
        this.leave_injury = leave_injury;
        this.leave_delivery = leave_delivery;
        this.leave_married = leave_married;
        this.leave_funeral = leave_funeral;
        this.leave_annual = leave_annual;
        this.should_attendance_time = should_attendance_time;
        this.should_attendance_day = should_attendance_day;
        this.actual_attendance_time = actual_attendance_time;
        this.actual_attendance_day = actual_attendance_day;
        this.attendanceType = attendanceType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public HrmResource getResourceId() {
        return resourceId;
    }

    public void setResourceId(HrmResource resourceId) {
        this.resourceId = resourceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getFirst_time_up() {
        return first_time_up;
    }

    public void setFirst_time_up(Time first_time_up) {
        this.first_time_up = first_time_up;
    }

    public Time getFirst_time_down() {
        return first_time_down;
    }

    public void setFirst_time_down(Time first_time_down) {
        this.first_time_down = first_time_down;
    }

    public Time getSecond_time_up() {
        return second_time_up;
    }

    public void setSecond_time_up(Time second_time_up) {
        this.second_time_up = second_time_up;
    }

    public Time getSecond_time_down() {
        return second_time_down;
    }

    public void setSecond_time_down(Time second_time_down) {
        this.second_time_down = second_time_down;
    }

    public Time getThird_time_up() {
        return third_time_up;
    }

    public void setThird_time_up(Time third_time_up) {
        this.third_time_up = third_time_up;
    }

    public Time getThird_time_down() {
        return third_time_down;
    }

    public void setThird_time_down(Time third_time_down) {
        this.third_time_down = third_time_down;
    }

    public Long getLateTime() {
        return lateTime;
    }

    public void setLateTime(Long lateTime) {
        this.lateTime = lateTime;
    }

    public Integer getLateCount() {
        return lateCount;
    }

    public void setLateCount(Integer lateCount) {
        this.lateCount = lateCount;
    }

    public Long getEarlyTime() {
        return earlyTime;
    }

    public void setEarlyTime(Long earlyTime) {
        this.earlyTime = earlyTime;
    }

    public Integer getEarlyCount() {
        return earlyCount;
    }

    public void setEarlyCount(Integer earlyCount) {
        this.earlyCount = earlyCount;
    }

    public Long getAbsenteeismTime() {
        return absenteeismTime;
    }

    public void setAbsenteeismTime(Long absenteeismTime) {
        this.absenteeismTime = absenteeismTime;
    }

    public Integer getAbsenteeismCount() {
        return absenteeismCount;
    }

    public void setAbsenteeismCount(Integer absenteeismCount) {
        this.absenteeismCount = absenteeismCount;
    }

    public Long getOvertime_normal() {
        return overtime_normal;
    }

    public void setOvertime_normal(Long overtime_normal) {
        this.overtime_normal = overtime_normal;
    }

    public Long getOvertime_weekend() {
        return overtime_weekend;
    }

    public void setOvertime_weekend(Long overtime_weekend) {
        this.overtime_weekend = overtime_weekend;
    }

    public Long getOvertime_festival() {
        return overtime_festival;
    }

    public void setOvertime_festival(Long overtime_festival) {
        this.overtime_festival = overtime_festival;
    }

    public Float getLeave_personal() {
        return leave_personal;
    }

    public void setLeave_personal(Float leave_personal) {
        this.leave_personal = leave_personal;
    }

    public Float getLeave_sick() {
        return leave_sick;
    }

    public void setLeave_sick(Float leave_sick) {
        this.leave_sick = leave_sick;
    }

    public Float getLeave_business() {
        return leave_business;
    }

    public void setLeave_business(Float leave_business) {
        this.leave_business = leave_business;
    }

    public Float getLeave_injury() {
        return leave_injury;
    }

    public void setLeave_injury(Float leave_injury) {
        this.leave_injury = leave_injury;
    }

    public Float getLeave_delivery() {
        return leave_delivery;
    }

    public void setLeave_delivery(Float leave_delivery) {
        this.leave_delivery = leave_delivery;
    }

    public Float getLeave_married() {
        return leave_married;
    }

    public void setLeave_married(Float leave_married) {
        this.leave_married = leave_married;
    }

    public Float getLeave_funeral() {
        return leave_funeral;
    }

    public void setLeave_funeral(Float leave_funeral) {
        this.leave_funeral = leave_funeral;
    }

    public Float getLeave_annual() {
        return leave_annual;
    }

    public void setLeave_annual(Float leave_annual) {
        this.leave_annual = leave_annual;
    }

    public Float getShould_attendance_time() {
        return should_attendance_time;
    }

    public void setShould_attendance_time(Float should_attendance_time) {
        this.should_attendance_time = should_attendance_time;
    }

    public Integer getShould_attendance_day() {
        return should_attendance_day;
    }

    public void setShould_attendance_day(Integer should_attendance_day) {
        this.should_attendance_day = should_attendance_day;
    }

    public Float getActual_attendance_time() {
        return actual_attendance_time;
    }

    public void setActual_attendance_time(Float actual_attendance_time) {
        this.actual_attendance_time = actual_attendance_time;
    }

    public Integer getActual_attendance_day() {
        return actual_attendance_day;
    }

    public void setActual_attendance_day(Integer actual_attendance_day) {
        this.actual_attendance_day = actual_attendance_day;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    public AttendanceSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(AttendanceSchedule schedule) {
        this.schedule = schedule;
    }
}
