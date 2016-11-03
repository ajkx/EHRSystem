package com.victory.ehrsystem.domain.hrm;

import javax.persistence.*;

/**
 * 人员信息表
 *
 * @author ajkx_Du
 * @create 2016-10-19 15:12
 */
@Entity
public class HrmResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="lastname")
    private String name;

    @Column(name="workcode")
    private String workcode;

    @ManyToOne(targetEntity = HrmSubCompany.class)
    @JoinColumn(name = "subcompanyid")
    private HrmSubCompany subCompanyid;

    @ManyToOne(targetEntity = HrmDepartment.class)
    @JoinColumn(name = "departmentid")
    private HrmDepartment departmentid;

    @ManyToOne(targetEntity = HrmJobTitles.class)
    @JoinColumn(name = "jobtitle")
    private HrmJobTitles jobtitle;

    @ManyToOne(targetEntity = HrmJobCall.class)
    @JoinColumn(name = "jobcall")
    private HrmJobCall jobcall;

    @ManyToOne(targetEntity = HrmUsekind.class)
    @JoinColumn(name = "usekind")
    private HrmUsekind usekind;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "managerid")
    private HrmResource managerid;

    @Column(name = "managerstr")
    private String managerstr;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "assistantid")
    private HrmResource assistantid;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sex")
    private Character sex;

    @Column(name = "birthday")
    private Character[] birthday;

    @Column(name = "nationality")
    private Integer nationality;

    @Column(name = "maritalstatus")
    private Character maritalstatus;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @ManyToOne(targetEntity = HrmLocation.class)
    @JoinColumn(name = "locationid")
    private HrmLocation locationid;

    @Column(name = "certificatenum")
    private String certificatenum;

    @Column(name = "nativeplace")
    private String nativeplace;

    @Column(name = "homeaddress")
    private String homeaddress;

    @Column(name = "residentphone")
    private String residentphone;

    @Column(name = "folk")
    private String folk;

    @Column(name = "regresidentplace")
    private String regresidentplace;

    @Column(name = "height",length = 20)
    private String height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "healthinfo")
    private Character healthinfo;

    @Column(name = "createdate")
    private Character[] createdate;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "createrid")
    private HrmResource createrid;

    @OneToOne(targetEntity = HrmContract.class,mappedBy = "man")
    private HrmContract contract;

    public HrmResource() {
    }

    public HrmResource(String name, String workcode, HrmSubCompany subCompanyid, HrmDepartment departmentid, HrmJobTitles jobtitle, HrmJobCall jobcall, HrmUsekind usekind, HrmResource managerid, String managerstr, HrmResource assistantid, Integer status, Character sex, Character[] birthday, Integer nationality, Character maritalstatus, String telephone, String mobile, String email, HrmLocation locationid, String certificatenum, String nativeplace, String homeaddress, String residentphone, String folk, String regresidentplace, String height, Integer weight, Character healthinfo, Character[] createdate, HrmResource createrid, HrmContract contract) {
        this.name = name;
        this.workcode = workcode;
        this.subCompanyid = subCompanyid;
        this.departmentid = departmentid;
        this.jobtitle = jobtitle;
        this.jobcall = jobcall;
        this.usekind = usekind;
        this.managerid = managerid;
        this.managerstr = managerstr;
        this.assistantid = assistantid;
        this.status = status;
        this.sex = sex;
        this.birthday = birthday;
        this.nationality = nationality;
        this.maritalstatus = maritalstatus;
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.locationid = locationid;
        this.certificatenum = certificatenum;
        this.nativeplace = nativeplace;
        this.homeaddress = homeaddress;
        this.residentphone = residentphone;
        this.folk = folk;
        this.regresidentplace = regresidentplace;
        this.height = height;
        this.weight = weight;
        this.healthinfo = healthinfo;
        this.createdate = createdate;
        this.createrid = createrid;
        this.contract = contract;
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

    public String getWorkcode() {
        return workcode;
    }

    public void setWorkcode(String workcode) {
        this.workcode = workcode;
    }

    public HrmSubCompany getSubCompanyid() {
        return subCompanyid;
    }

    public void setSubCompanyid(HrmSubCompany subCompanyid) {
        this.subCompanyid = subCompanyid;
    }

    public HrmDepartment getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(HrmDepartment departmentid) {
        this.departmentid = departmentid;
    }

    public HrmJobTitles getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(HrmJobTitles jobtitle) {
        this.jobtitle = jobtitle;
    }

    public HrmJobCall getJobcall() {
        return jobcall;
    }

    public void setJobcall(HrmJobCall jobcall) {
        this.jobcall = jobcall;
    }

    public HrmUsekind getUsekind() {
        return usekind;
    }

    public void setUsekind(HrmUsekind usekind) {
        this.usekind = usekind;
    }

    public HrmResource getManagerid() {
        return managerid;
    }

    public void setManagerid(HrmResource managerid) {
        this.managerid = managerid;
    }

    public String getManagerstr() {
        return managerstr;
    }

    public void setManagerstr(String managerstr) {
        this.managerstr = managerstr;
    }

    public HrmResource getAssistantid() {
        return assistantid;
    }

    public void setAssistantid(HrmResource assistantid) {
        this.assistantid = assistantid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Character[] getBirthday() {
        return birthday;
    }

    public void setBirthday(Character[] birthday) {
        this.birthday = birthday;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public Character getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(Character maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HrmLocation getLocationid() {
        return locationid;
    }

    public void setLocationid(HrmLocation locationid) {
        this.locationid = locationid;
    }

    public String getCertificatenum() {
        return certificatenum;
    }

    public void setCertificatenum(String certificatenum) {
        this.certificatenum = certificatenum;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getResidentphone() {
        return residentphone;
    }

    public void setResidentphone(String residentphone) {
        this.residentphone = residentphone;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getRegresidentplace() {
        return regresidentplace;
    }

    public void setRegresidentplace(String regresidentplace) {
        this.regresidentplace = regresidentplace;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Character getHealthinfo() {
        return healthinfo;
    }

    public void setHealthinfo(Character healthinfo) {
        this.healthinfo = healthinfo;
    }

    public Character[] getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Character[] createdate) {
        this.createdate = createdate;
    }

    public HrmResource getCreaterid() {
        return createrid;
    }

    public void setCreaterid(HrmResource createrid) {
        this.createrid = createrid;
    }

    public HrmContract getContract() {
        return contract;
    }

    public void setContract(HrmContract contract) {
        this.contract = contract;
    }
}


