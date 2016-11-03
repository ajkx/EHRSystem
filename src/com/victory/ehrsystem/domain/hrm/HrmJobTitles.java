package com.victory.ehrsystem.domain.hrm;

import javax.persistence.*;


/**
 * 岗位表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:14
 */
@Entity
public class HrmJobTitles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jobtitlename",nullable = false)
    private String name;

    @ManyToOne(targetEntity = HrmDepartment.class)
    @JoinColumn(name = "jobdepartmentid",nullable = false)
    private HrmDepartment depid;

    @ManyToOne(targetEntity = HrmJobActivities.class)
    @JoinColumn(name = "jobactivityid")
    private HrmJobActivities jobtypeid;

    @Column(name = "jobresponsibility",length = 200)
    private String respon;

    @Column(name = "jobcompetency",length = 200)
    private String required;

    @Column(name = "jobtitleremark",length = 200)
    private String remark;

    public HrmJobTitles() {
    }

    public HrmJobTitles(String name, HrmDepartment depid, HrmJobActivities jobtypeid, String respon, String required, String remark) {

        this.name = name;
        this.depid = depid;
        this.jobtypeid = jobtypeid;
        this.respon = respon;
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

    public HrmDepartment getDepid() {
        return depid;
    }

    public void setDepid(HrmDepartment depid) {
        this.depid = depid;
    }

    public HrmJobActivities getJobtypeid() {
        return jobtypeid;
    }

    public void setJobtypeid(HrmJobActivities jobtypeid) {
        this.jobtypeid = jobtypeid;
    }

    public String getRespon() {
        return respon;
    }

    public void setRespon(String respon) {
        this.respon = respon;
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
