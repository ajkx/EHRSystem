package com.victory.ehrsystem.entity.hrm;

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
    private String workcode;

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
    @JoinColumn(name = "subcompanyId")
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
    @JoinColumn(name = "managerId")
    private HrmResource managerId;

    //上级的递归id
    @Column(name = "managerStr")
    private String managerStr;

    //助手id
    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "assistantId")
    private HrmResource assistantId;

    //用工性质
    @ManyToOne(targetEntity = HrmUsekind.class)
    @JoinColumn(name = "useKind")
    private HrmUsekind useKind;

    //办公地点
    @ManyToOne(targetEntity = HrmLocation.class)
    @JoinColumn(name = "locationId")
    private HrmLocation locationId;

    //办公电话
    @Column(name = "officePhone")
    private String officePhone;

    //状态
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

    //离职日期
    @Column(name = "dimissionDate")
    private Date dimissionDate;


    //========银行信息========
    //开户银行
    private String bankName;

    //银行账号
    private String bankAccount;

    //========简历信息=========
    //教育情况
    @OneToMany(targetEntity = EducationInfo.class)
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
    @ManyToOne(targetEntity = AttendanceSchedule.class)
    private AttendanceSchedule schedule;

    @OneToOne(targetEntity = HrmContract.class, mappedBy = "man")
    private HrmContract contract;


}


