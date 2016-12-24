package com.victory.ehrsystem.entity.attendance;


import com.victory.ehrsystem.entity.hrm.HrmResource;

import javax.persistence.*;
import java.sql.Date;

/**
 * 人员的具体排班表
 *
 * @author ajkx_Du
 * @create 2016-12-09 18:59
 */


@Entity
public class AttendanceScheduleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //员工id
    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceid",nullable = false)
    private HrmResource resource;

    //班次ID
    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "scheduleid",nullable = false)
    private AttendanceSchedule schedule;

    //排班的日期
    @Column
    private Date date;

    public AttendanceScheduleInfo() {
    }

    public AttendanceScheduleInfo(HrmResource resource, AttendanceSchedule schedule, Date date) {
        this.resource = resource;
        this.schedule = schedule;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
