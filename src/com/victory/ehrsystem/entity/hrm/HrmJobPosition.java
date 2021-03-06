package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;


/**
 * 职位（岗位）表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:14
 */
@Entity
public class HrmJobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToOne(targetEntity = HrmDepartment.class)
    @JoinColumn(name = "department",nullable = false)
    private HrmDepartment department;

    @ManyToOne(targetEntity = HrmJobDuty.class)
    @JoinColumn(name = "jobDuty")
    private HrmJobDuty jobDuty;

    //职责描述
    @Column(name = "responsibility",length = 200)
    private String responsibility;

    //职责要求
    @Column(name = "required",length = 200)
    private String required;

    //备注
    @Column(name = "remark",length = 200)
    private String remark;

    public HrmJobPosition() {
    }

    public HrmJobPosition(String name, HrmDepartment department, HrmJobDuty jobDuty, String responsibility, String required, String remark) {
        this.name = name;
        this.department = department;
        this.jobDuty = jobDuty;
        this.responsibility = responsibility;
        this.required = required;
        this.remark = remark;
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

    public HrmDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HrmDepartment department) {
        this.department = department;
    }

    public HrmJobDuty getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(HrmJobDuty jobDuty) {
        this.jobDuty = jobDuty;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
