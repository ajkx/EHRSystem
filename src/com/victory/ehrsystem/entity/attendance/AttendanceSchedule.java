package com.victory.ehrsystem.entity.attendance;

import javax.persistence.*;
import java.sql.Time;

/**
 * 班次表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:35
 */
@Entity
public class AttendanceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String simplename;

    @Column(nullable = false)
    private int scheduleType = 1;

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

    //上班有效打卡范围
    @Column
    private long scope_up;

    //下班有效打卡范围
    @Column
    private long scope_down;

    //出勤合计时间
    @Column
    private int attendanceTime;

    //下班是否需要打卡
    @Column
    private boolean isPunch;

    //迟到早退的限定时间
    @Column
    private int offsetTime;

    @Column
    private Boolean acrossDay;
    public AttendanceSchedule() {
    }

    public AttendanceSchedule(String name, String simplename, int scheduleType, Time first_time_up, Time first_time_down, Time second_time_up, Time second_time_down, Time third_time_up, Time third_time_down, long scope_up, long scope_down, int attendanceTime, boolean isPunch, int offsetTime, Boolean acrossDay) {
        this.name = name;
        this.simplename = simplename;
        this.scheduleType = scheduleType;
        this.first_time_up = first_time_up;
        this.first_time_down = first_time_down;
        this.second_time_up = second_time_up;
        this.second_time_down = second_time_down;
        this.third_time_up = third_time_up;
        this.third_time_down = third_time_down;
        this.scope_up = scope_up;
        this.scope_down = scope_down;
        this.attendanceTime = attendanceTime;
        this.isPunch = isPunch;
        this.offsetTime = offsetTime;
        this.acrossDay = acrossDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimplename() {
        return simplename;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
    }

    public int getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(int scheduleType) {
        this.scheduleType = scheduleType;
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

    public int getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(int attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public long getScope_up() {
        return scope_up;
    }

    public void setScope_up(long scope_up) {
        this.scope_up = scope_up;
    }

    public long getScope_down() {
        return scope_down;
    }

    public void setScope_down(long scope_down) {
        this.scope_down = scope_down;
    }

    public int getOffsetTime() {
        return offsetTime;
    }

    public void setOffsetTime(int offsetTime) {
        this.offsetTime = offsetTime;
    }

    public boolean getPunch() {
        return isPunch;
    }

    public void setPunch(boolean punch) {
        isPunch = punch;
    }

    public Boolean getAcrossDay() {
        return acrossDay;
    }

    public void setAcrossDay(Boolean acrossDay) {
        this.acrossDay = acrossDay;
    }
}
