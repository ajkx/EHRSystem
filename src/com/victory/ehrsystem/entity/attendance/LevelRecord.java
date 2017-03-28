package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.hrm.HrmResource;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:29
 */
@Entity
public class LevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId")
    private HrmResource resource;

    @ManyToOne(targetEntity = LevelType.class)
    @JoinColumn(name = "type")
    private LevelType type;

    @Column(name = "beginDate")
    private Date date;

    @Column(name = "endDate")
    private Date endDate;

    //请假时数
    @Column
    private Long count;

    //请假原因
    @Column
    private String reason;

    //状态
    @Column
    private OverTimeRecord.Status status;

    //备注
    @Column
    private String remark;

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

    public LevelType getType() {
        return type;
    }

    public void setType(LevelType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public OverTimeRecord.Status getStatus() {
        return status;
    }

    public void setStatus(OverTimeRecord.Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
