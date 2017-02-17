package com.victory.ehrsystem.vo;

/**
 * Created by ajkx
 * Date: 2017/1/4.
 * Time:20:50
 */
public class ScheduleVo {
    public Integer id;
    public String name;
    public Integer scheduleType = 1;
    public String first_up = "";
    public String first_down;
    public String second_up = "";
    public String second_down;
    public String third_up;
    public String third_down;
    public Long scope_up;
    public Long scope_down;
    public Integer attendanceTime = 0;
    public Integer isPunch;
    public Integer acrossDay = 0;
    public Integer offsetTime = 0;

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

    public Integer getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(Integer scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getFirst_up() {
        return first_up;
    }

    public void setFirst_up(String first_up) {
        this.first_up = first_up+":00";
    }

    public String getFirst_down() {
        return first_down;
    }

    public void setFirst_down(String first_down) {
        this.first_down = first_down+":00";
    }

    public String getSecond_up() {
        return second_up;
    }

    public void setSecond_up(String second_up) {
        this.second_up = second_up+":00";
    }

    public String getSecond_down() {
        return second_down;
    }

    public void setSecond_down(String second_down) {
        this.second_down = second_down+":00";
    }

    public String getThird_up() {
        return third_up;
    }

    public void setThird_up(String third_up) {
        this.third_up = third_up+":00";
    }

    public String getThird_down() {
        return third_down;
    }

    public void setThird_down(String third_down) {
        this.third_down = third_down+":00";
    }

    public Long getScope_up() {
        return scope_up;
    }

    public void setScope_up(Long scope_up) {
        this.scope_up = scope_up;
    }

    public Long getScope_down() {
        return scope_down;
    }

    public void setScope_down(Long scope_down) {
        this.scope_down = scope_down;
    }

    public Integer getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Integer attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public Integer getIsPunch() {
        return isPunch;
    }

    public void setIsPunch(Integer isPunch) {
        this.isPunch = isPunch;
    }

    public Integer getAcrossDay() {
        return acrossDay;
    }

    public void setAcrossDay(Integer acrossDay) {
        this.acrossDay = acrossDay;
    }

    public Integer getOffsetTime() {
        return offsetTime;
    }

    public void setOffsetTime(Integer offsetTime) {
        this.offsetTime = offsetTime;
    }

    @Override
    public String toString() {
        return "Location{" +
                ", name='" + name + '\'' +
                ", scheduleType'" + scheduleType + '\'' +
                ", first_up='" + first_up + '\'' +
                ", first_down='" + first_down + '\'' +
                ", second_up=" + second_up +
                ", second_down='" + second_down + '\'' +
                ", third_up='" + third_up + '\'' +
                ", third_down='" + third_down + '\'' +
                ", scope_up='" + scope_up + '\'' +
                ", scope_down='" + scope_down + '\'' +
                ", attendanceTime='" + attendanceTime + '\'' +
                ", isPunch='" + isPunch + '\'' +
                ", offsetTime='" + offsetTime + '\'' +
                '}';
    }
}
