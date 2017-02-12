package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 离职纪录表
 * Created by ajkx on 2017/2/11.
 */
@Entity
public class DismissRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resourceName",nullable = false)
    private String resourceName;

    private String workCode;

    private String deptName;

    private String positionName;

    private String newDeptName;

    private String newPositionName;

    private String reason;

    @Column(name = "executeDate",nullable = false)
    private Timestamp executeDate;

    @Column(nullable = false)
    private String creater;

    private String remark;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId", referencedColumnName = "id",nullable = false)
    private HrmResource resource;

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public DismissRecord() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getNewDeptName() {
        return newDeptName;
    }

    public void setNewDeptName(String newDeptName) {
        this.newDeptName = newDeptName;
    }

    public String getNewPositionName() {
        return newPositionName;
    }

    public void setNewPositionName(String newPositionName) {
        this.newPositionName = newPositionName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Timestamp executeDate) {
        this.executeDate = executeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
