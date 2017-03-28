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
    @JoinColumn(name = "resource",nullable = false)
    private HrmResource resource;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "scheduleId")
    private AttendanceSchedule schedule;
    //日期
    @Column
    private Date date;

    //第一次上班打卡时间
    @Column
    private Time first_time_up;

    //第一次上班打卡结果
    //出勤类别
    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="firstUpType")
    private AttendanceType firstUpType;

    //第一次下班打卡时间
    @Column
    private Time first_time_down;

    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="firstDownType")
    private AttendanceType firstDownType;

    //第二次上班打卡时间
    @Column
    private Time second_time_up;

    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="SecondUpType")
    private AttendanceType secondUpType;

    //第二次下班打卡时间
    @Column
    private Time second_time_down;

    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="SecordDownType")
    private AttendanceType secondDownType;

    //第三次上班打卡时间
    @Column
    private Time third_time_up;

    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="ThirdUpType")
    private AttendanceType thirdUpType;

    //第三次下班打卡时间
    @Column
    private Time third_time_down;

    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="ThirdDownType")
    private AttendanceType thirdDownType;

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
    private Long leave_personal;

    //调休
    @Column
    private Long leave_rest;

    //病假
    @Column
    private Long leave_sick;

    //出差
    @Column
    private Long leave_business;

    //工伤
    @Column
    private Long leave_injury;

    //产假
    @Column
    private Long leave_delivery;

    //婚假
    @Column
    private Long leave_married;

    //丧假
    @Column
    private Long leave_funeral;

    //年假
    @Column
    private Long leave_annual;

    //规定出勤小时
    @Column
    private Long should_attendance_time;

    //规定出勤天数
    @Column
    private Integer should_attendance_day;

    //实际出勤小时
    @Column
    private Long actual_attendance_time;

    //实际出勤天数
    @Column
    private Double actual_attendance_day;

    //出勤类别
    @ManyToOne(targetEntity = AttendanceType.class)
    @JoinColumn(name="typeId")
    private AttendanceType attendanceType;

    public AttendanceDetail() {
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

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public AttendanceSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(AttendanceSchedule schedule) {
        this.schedule = schedule;
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

    public Long getLeave_personal() {
        return leave_personal;
    }

    public void setLeave_personal(Long leave_personal) {
        this.leave_personal = leave_personal;
    }

    public Long getLeave_sick() {
        return leave_sick;
    }

    public void setLeave_sick(Long leave_sick) {
        this.leave_sick = leave_sick;
    }

    public Long getLeave_business() {
        return leave_business;
    }

    public void setLeave_business(Long leave_business) {
        this.leave_business = leave_business;
    }

    public Long getLeave_injury() {
        return leave_injury;
    }

    public void setLeave_injury(Long leave_injury) {
        this.leave_injury = leave_injury;
    }

    public Long getLeave_delivery() {
        return leave_delivery;
    }

    public void setLeave_delivery(Long leave_delivery) {
        this.leave_delivery = leave_delivery;
    }

    public Long getLeave_married() {
        return leave_married;
    }

    public void setLeave_married(Long leave_married) {
        this.leave_married = leave_married;
    }

    public Long getLeave_funeral() {
        return leave_funeral;
    }

    public void setLeave_funeral(Long leave_funeral) {
        this.leave_funeral = leave_funeral;
    }

    public Long getLeave_annual() {
        return leave_annual;
    }

    public void setLeave_annual(Long leave_annual) {
        this.leave_annual = leave_annual;
    }

    public Long getShould_attendance_time() {
        return should_attendance_time;
    }

    public void setShould_attendance_time(Long should_attendance_time) {
        this.should_attendance_time = should_attendance_time;
    }

    public Integer getShould_attendance_day() {
        return should_attendance_day;
    }

    public void setShould_attendance_day(Integer should_attendance_day) {
        this.should_attendance_day = should_attendance_day;
    }

    public Long getActual_attendance_time() {
        return actual_attendance_time;
    }

    public void setActual_attendance_time(Long actual_attendance_time) {
        this.actual_attendance_time = actual_attendance_time;
    }

    public Double getActual_attendance_day() {
        return actual_attendance_day;
    }

    public void setActual_attendance_day(Double actual_attendance_day) {
        this.actual_attendance_day = actual_attendance_day;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    public Long getLeave_rest() {
        return leave_rest;
    }

    public void setLeave_rest(Long leave_rest) {
        this.leave_rest = leave_rest;
    }

    public AttendanceType getFirstUpType() {
        return firstUpType;
    }

    public void setFirstUpType(AttendanceType firstUpType) {
        this.firstUpType = firstUpType;
    }

    public AttendanceType getFirstDownType() {
        return firstDownType;
    }

    public void setFirstDownType(AttendanceType firstDownType) {
        this.firstDownType = firstDownType;
    }

    public AttendanceType getSecondUpType() {
        return secondUpType;
    }

    public void setSecondUpType(AttendanceType secondUpType) {
        this.secondUpType = secondUpType;
    }

    public AttendanceType getSecondDownType() {
        return secondDownType;
    }

    public void setSecondDownType(AttendanceType secondDownType) {
        this.secondDownType = secondDownType;
    }

    public AttendanceType getThirdUpType() {
        return thirdUpType;
    }

    public void setThirdUpType(AttendanceType thirdUpType) {
        this.thirdUpType = thirdUpType;
    }

    public AttendanceType getThirdDownType() {
        return thirdDownType;
    }

    public void setThirdDownType(AttendanceType thirdDownType) {
        this.thirdDownType = thirdDownType;
    }
}
