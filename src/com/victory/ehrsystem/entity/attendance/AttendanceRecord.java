package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.consume.Card;
import com.victory.ehrsystem.entity.hrm.HrmResource;

import javax.persistence.*;
import java.util.Date;


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
    @JoinColumn(name = "resource",nullable = false)
    private HrmResource resource;

    @Column(name = "machineNo")
    private String machineNo;

    @Column(name = "date")
    private Date date;

    //1为从考勤机读取，2为签卡类型
    @Column(name = "type")
    private Integer type;

    @Column
    private String reason;

    public AttendanceRecord() {
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

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
