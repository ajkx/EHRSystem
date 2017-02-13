package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 调动纪录表
 * Created by ajkx on 2017/2/11.
 */
@Entity
public class ReEmployRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reason;

    @Column(name = "executeDate",nullable = false)
    private Date executeDate;

    private String remark;

    @Column(name = "createDate",nullable = false)
    private Timestamp createDate;

    @Column(nullable = false)
    private String creater;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId", referencedColumnName = "id",nullable = false)
    private HrmResource resource;

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public ReEmployRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
