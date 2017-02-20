package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;

/**
 * 分部表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 9:49
 */
@Entity
public class HrmSubCompany{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false,length = 200)
    private String name;

    @Column(name = "description",length = 200)
    private String description;

    @ManyToOne(targetEntity = HrmSubCompany.class)
    @JoinColumn(name = "parentSub")
    private HrmSubCompany parent;

    @Column(name = "cancel")
    private Boolean cancel;

    public HrmSubCompany() {
    }

    public HrmSubCompany(String name, String description, HrmSubCompany parent, boolean cancel) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.cancel = cancel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParent(HrmSubCompany parent) {
        this.parent = parent;
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

    public String getDescription() {
        return description;
    }

    public HrmSubCompany getParent() {
        return parent;
    }

    public boolean getCancel() {
        return cancel;
    }
}



