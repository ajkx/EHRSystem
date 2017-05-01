package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by ajkx on 2017/5/1.
 * 异动纪录表（包括调动，离职，转正，返聘）
 */
@Entity
public class ChangeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ChangeType type;

    // ------共有字段
    @Column(name = "resourceName",nullable = false)
    private String resourceName;

    private String workCode;

    private String reason;

    @Column(name = "executeDate",nullable = false)
    private Date executeDate;

    private String remark;

    @Column(name = "createDate",nullable = false)
    private java.util.Date createDate;

    @Column(nullable = false)
    private String creater;

    //关联的员工
    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId", referencedColumnName = "id",nullable = false)
    private HrmResource resource;

    // ----调动字段-----
    private String deptName;

    private String positionName;

    private String newDeptName;

    private String newPositionName;
}
