package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.consume.Card;
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

    @ManyToOne(targetEntity = Card.class)
    @JoinColumn(name="card")
    private Card card;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId",nullable = false)
    private HrmResource resourceId;

    @Column(name = "machineNo")
    private String machineNo;

    @Column(name = "punchDate")
    private Date punchDate;

    @Column(name = "punchTime")
    private Time punchTime;

    public AttendanceRecord() {
    }

    public AttendanceRecord(Card card, HrmResource resourceId, String machineNo, Date punchDate, Time punchTime) {
        this.card = card;
        this.resourceId = resourceId;
        this.machineNo = machineNo;
        this.punchDate = punchDate;
        this.punchTime = punchTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public HrmResource getResourceId() {
        return resourceId;
    }

    public void setResourceId(HrmResource resourceId) {
        this.resourceId = resourceId;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
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
