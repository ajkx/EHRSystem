package com.victory.ehrsystem.domain.hrm;

import javax.persistence.*;

/**
 * 部门表
 *
 * @author ajkx_Du
 * @create 2016-10-19 10:20
 */
@Entity
public class HrmDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "departmentname",nullable = false,length = 200)
    private String name;

    @ManyToOne(targetEntity = HrmSubCompany.class)
    @JoinColumn(name = "subcompanyid",nullable = false)
    private HrmSubCompany subcomid;

    @ManyToOne(targetEntity = HrmDepartment.class)
    @JoinColumn(name = "supdepid")
    private HrmDepartment supid;

    @Column(name = "cancel")
    private boolean cancel;

    public HrmDepartment() {
    }

    public HrmDepartment(String name, HrmSubCompany subcomid, HrmDepartment supid, boolean cancel) {
        this.name = name;
        this.subcomid = subcomid;
        this.supid = supid;
        this.cancel = cancel;
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

    public HrmSubCompany getSubcomid() {
        return subcomid;
    }

    public void setSubcomid(HrmSubCompany subcomid) {
        this.subcomid = subcomid;
    }

    public HrmDepartment getSupid() {
        return supid;
    }

    public void setSupid(HrmDepartment supid) {
        this.supid = supid;
    }

    public boolean getCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
