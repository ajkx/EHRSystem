package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ajkx
 * Date: 2017/3/1.
 * Time:8:22
 */
@Entity
public class AcrossDaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = HrmResource.class)
    private HrmResource resource;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    private AttendanceSchedule schedule;

    @Column
    private Date date;

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
