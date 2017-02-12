package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;
import java.sql.Date;

/**
 * 教育情况表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:22
 */
@Entity
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //学校名称
    @Column(name = "schoolName",nullable = false)
    private String schoolName;

    //学历
    @Column(name = "educationLevel")
    private String educationLevel;

    //毕业日期
    @Column(name = "graduationDate")
    private Date graduationDate;

    //专业
    @Column(name = "major")
    private String major;

    //关联的员工
    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId",referencedColumnName = "id",nullable = false)
    private HrmResource resource;

    public EducationInfo() {
    }

    public EducationInfo(String schoolName, String educationLevel, Date graduationDate, String major, HrmResource resource) {
        this.schoolName = schoolName;
        this.educationLevel = educationLevel;
        this.graduationDate = graduationDate;
        this.major = major;
        this.resource = resource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }
}
