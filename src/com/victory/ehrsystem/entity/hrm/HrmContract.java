package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;
import java.sql.Date;

/**
 * 合同表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:53
 */
@Entity
public class HrmContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contractName")
    private String contractName;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId", referencedColumnName = "id",nullable = false)
    private HrmResource resource;

    @Column(name = "beginDate")
    private Date beginDate;

    @Column(name = "enddate")
    private Date endDate;

    @Column(name = "yearPeriod")
    private Integer yearPeriod;

    //保密等级
    @Column(name = "secureGrade")
    private String secureGrade;

    //签订日期
    @Column(name = "signDate")
    private Date signDate;

    @Column(name = "remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getYearPeriod() {
        return yearPeriod;
    }

    public void setYearPeriod(Integer yearPeriod) {
        this.yearPeriod = yearPeriod;
    }

    public String getSecureGrade() {
        return secureGrade;
    }

    public void setSecureGrade(String secureGrade) {
        this.secureGrade = secureGrade;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HrmContract(String contractName, HrmResource resource, Date beginDate, Date endDate, Integer yearPeriod, String secureGrade, Date signDate, String remark) {

        this.contractName = contractName;
        this.resource = resource;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.yearPeriod = yearPeriod;
        this.secureGrade = secureGrade;
        this.signDate = signDate;
        this.remark = remark;
    }

    public HrmContract() {

    }
}
