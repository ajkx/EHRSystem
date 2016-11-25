package com.victory.ehrsystem.domain.hrm;

import javax.persistence.*;

/**
 * 分部表
 *
 * @author ajkx_Du
 * @create 2016-10-19 9:49
 */
@Entity
public class HrmSubCompany{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subcompanyname",nullable = false,length = 200)
    private String name;

    @Column(name = "subcompanydesc",length = 200)
    private String desc;

    @ManyToOne(targetEntity = HrmSubCompany.class)
    @JoinColumn(name = "supsubcomid")
    private HrmSubCompany supid;

    @Column(name = "cancel")
    private boolean cancel;

    public HrmSubCompany() {
    }

    public HrmSubCompany(String name, String desc, HrmSubCompany supid, boolean cancel) {
        this.name = name;
        this.desc = desc;
        this.supid = supid;
        this.cancel = cancel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSupid(HrmSubCompany supid) {
        this.supid = supid;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public HrmSubCompany getSupid() {
        return supid;
    }

    public boolean getCancel() {
        return cancel;
    }
}



