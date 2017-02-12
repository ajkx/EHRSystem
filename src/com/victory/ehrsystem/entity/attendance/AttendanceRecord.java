package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.hrm.HrmResource;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

/**
 * 打卡原记录表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:35
 */
@Entity
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cardNo",nullable = false)
    private String card;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceid",nullable = false)
    private HrmResource resourceid;

    @Column(name = "mechineno")
    private String mechineNo;

    @Column(name = "punchdate")
    private Date punchDate;

    @Column(name = "punchtime")
    private Time punchTime;

    public AttendanceRecord(String card, HrmResource resourceid, String mechineNo, Date punchDate, Time punchTime) {
        this.card = card;
        this.resourceid = resourceid;
        this.mechineNo = mechineNo;
        this.punchDate = punchDate;
        this.punchTime = punchTime;
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

    public HrmResource getResourceid() {
        return resourceid;
    }

    public void setResourceid(HrmResource resourceid) {
        this.resourceid = resourceid;
    }

    public String getMechineNo() {
        return mechineNo;
    }

    public void setMechineNo(String mechineNo) {
        this.mechineNo = mechineNo;
    }

    public Date getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(Date punchDate) {
        this.punchDate = punchDate;
    }

    public Time getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Time punchTime) {
        this.punchTime = punchTime;
    }
}
