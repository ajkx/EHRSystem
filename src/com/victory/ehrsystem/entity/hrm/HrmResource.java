package com.victory.ehrsystem.entity.hrm;

import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.sys.User;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 人员信息表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 15:12
 */
@Entity
public class HrmResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //=======基本信息=======
    //姓名
    @Column(name = "name")
    private String name;

    //工号
    @Column(name = "workCode")
    private String workCode;

    //性别
    @Column(name = "sex")
    private String sex;

    //生日
    @Column(name = "birthday")
    private String birthday;

    //婚姻状态
    @Column(name = "maritalStatus")
    private String maritalStatus;

    //移动电话
    @Column(name = "mobile")
    private String mobile;

    //电子邮件
    @Column(name = "email")
    private String email;

    //身份证号码
    @Column(name = "certificateNum")
    private String certificateNum;

    //户口所在地
    @Column(name = "nativePlace")
    private String nativePlace;

    //家庭住址
    @Column(name = "homeAddress")
    private String homeAddress;

    //家庭电话
    @Column(name = "homePhone")
    private String homePhone;

    //民族
    @Column(name = "folk")
    private String folk;

    //暂住地
    @Column(name = "nowStayPlace")
    private String nowStayPlace;

    //身高
    @Column(name = "height", length = 20)
    private String height;

    //体重
    @Column(name = "weight")
    private Integer weight;

    //健康情况
    @Column(name = "healthInfo")
    private String healthInfo;


    //=======工作信息========
    //分部id
    @ManyToOne(targetEntity = HrmSubCompany.class)
    @JoinColumn(name = "subCompanyId")
    private HrmSubCompany subCompany;

    //部门id
    @ManyToOne(targetEntity = HrmDepartment.class)
    @JoinColumn(name = "departmentId")
    private HrmDepartment department;
    //职位id
    @ManyToOne(targetEntity = HrmJobPosition.class)
    @JoinColumn(name = "jobPosition")
    private HrmJobPosition jobPosition;

    //上级id
    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "manager")
    private HrmResource manager;

    //上级的递归id
    @Column(name = "managerStr")
    private String managerStr;

    //助手id
    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "assistantId")
    private HrmResource assistantId;

    //用工性质
    @ManyToOne(targetEntity = HrmUseKind.class)
    @JoinColumn(name = "useKind")
    private HrmUseKind useKind;

    //办公地点
    @ManyToOne(targetEntity = HrmLocation.class)
    @JoinColumn(name = "locationId")
    private HrmLocation locationId;

    //办公电话
    @Column(name = "officePhone")
    private String officePhone;

    //状态 0 代表试用 1 代表正式 2 代表离职
    @Column(name = "status")
    private Integer status;

    //招聘来源
    @Column(name = "recruit")
    private String recruit;

    //入职日期
    @Column(name = "entryDate")
    private Date entryDate;

    //试用期限，用数字表示，如果是1 则为一个月
    @Column(name = "probationPeriod")
    private Integer probationPeriod;

    //转正日期
    @Column(name = "officialDate")
    private Date officialDate;


    //========银行信息========
    //开户银行
    private String bankName;

    //银行账号
    private String bankAccount;

    //========简历信息=========
    //教育情况
    @OneToMany(targetEntity = EducationInfo.class,mappedBy = "resource")
    private Set<EducationInfo> educationInfos;

    //工作经历
    //双向1 - N 由N的一端控制关联属性
    @OneToMany(targetEntity = WorkInfo.class, mappedBy = "resource")
    private Set<WorkInfo> workInfos;

    @OneToMany(targetEntity = SkillInfo.class, mappedBy = "resource")
    private Set<SkillInfo> skillInfos;

    //========职业资格=========
    //社会职称
    @OneToMany(targetEntity = JobCall.class, mappedBy = "resource")
    private Set<JobCall> jobCalls;

    //========合同信息=========
    @OneToMany(targetEntity = HrmContract.class, mappedBy = "resource")
    private Set<HrmContract> contracts;

    //========人员变动信息=========
    //岗位/部门调动纪录
    @OneToMany(targetEntity = TransferRecord.class, mappedBy = "resource")
    private Set<TransferRecord> transferRecords;

    //离职纪录
    @OneToMany(targetEntity = DismissRecord.class, mappedBy = "resource")
    private Set<DismissRecord> dismissRecords;

    //返聘记录
    @OneToMany(targetEntity = ReEmployRecord.class, mappedBy = "resource")
    private Set<ReEmployRecord> reEmployRecords;

    //========系统信息=========
    //创建日期
    @Column(name = "createDate",nullable = false)
    private Timestamp createDate;

    //创建人
    @Column(name = "creater",nullable = false)
    private String creater;

    //绑定的操作员
    @OneToOne(targetEntity = User.class)
    private User user;

    //========考勤信息==========
    //恒定的排班
    @ManyToOne(targetEntity = AttendanceGroup.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "attendanceGroup", referencedColumnName = "id")
    private AttendanceGroup attendanceGroup;

    public AttendanceGroup getAttendanceGroup() {
        return attendanceGroup;
    }

    public void setAttendanceGroup(AttendanceGroup attendanceGroup) {
        this.attendanceGroup = attendanceGroup;
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

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getNowStayPlace() {
        return nowStayPlace;
    }

    public void setNowStayPlace(String nowStayPlace) {
        this.nowStayPlace = nowStayPlace;
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

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo = healthInfo;
    }

    public HrmSubCompany getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(HrmSubCompany subCompany) {
        this.subCompany = subCompany;
    }

    public HrmDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HrmDepartment department) {
        this.department = department;
    }

    public HrmJobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(HrmJobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public HrmResource getManager() {
        return manager;
    }

    public void setManager(HrmResource manager) {
        this.manager = manager;
    }

    public String getManagerStr() {
        return managerStr;
    }

    public void setManagerStr(String managerStr) {
        this.managerStr = managerStr;
    }

    public HrmResource getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(HrmResource assistantId) {
        this.assistantId = assistantId;
    }

    public HrmUseKind getUseKind() {
        return useKind;
    }

    public void setUseKind(HrmUseKind useKind) {
        this.useKind = useKind;
    }

    public HrmLocation getLocationId() {
        return locationId;
    }

    public void setLocationId(HrmLocation locationId) {
        this.locationId = locationId;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRecruit() {
        return recruit;
    }

    public void setRecruit(String recruit) {
        this.recruit = recruit;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getProbationPeriod() {
        return probationPeriod;
    }

    public void setProbationPeriod(Integer probationPeriod) {
        this.probationPeriod = probationPeriod;
    }

    public Date getOfficialDate() {
        return officialDate;
    }

    public void setOfficialDate(Date officialDate) {
        this.officialDate = officialDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Set<EducationInfo> getEducationInfos() {
        return educationInfos;
    }

    public void setEducationInfos(Set<EducationInfo> educationInfos) {
        this.educationInfos = educationInfos;
    }

    public Set<WorkInfo> getWorkInfos() {
        return workInfos;
    }

    public void setWorkInfos(Set<WorkInfo> workInfos) {
        this.workInfos = workInfos;
    }

    public Set<SkillInfo> getSkillInfos() {
        return skillInfos;
    }

    public void setSkillInfos(Set<SkillInfo> skillInfos) {
        this.skillInfos = skillInfos;
    }

    public Set<JobCall> getJobCalls() {
        return jobCalls;
    }

    public void setJobCalls(Set<JobCall> jobCalls) {
        this.jobCalls = jobCalls;
    }

    public Set<HrmContract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<HrmContract> contracts) {
        this.contracts = contracts;
    }

    public Set<TransferRecord> getTransferRecords() {
        return transferRecords;
    }

    public void setTransferRecords(Set<TransferRecord> transferRecords) {
        this.transferRecords = transferRecords;
    }

    public Set<DismissRecord> getDismissRecords() {
        return dismissRecords;
    }

    public void setDismissRecords(Set<DismissRecord> dismissRecords) {
        this.dismissRecords = dismissRecords;
    }

    public Set<ReEmployRecord> getReEmployRecords() {
        return reEmployRecords;
    }

    public void setReEmployRecords(Set<ReEmployRecord> reEmployRecords) {
        this.reEmployRecords = reEmployRecords;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}


