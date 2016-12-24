package com.victory.ehrsystem.entity.sys;

import javax.persistence.*;

/**
 * 模块名称
 * @author ajkx_Du
 * @create 2016-11-12 8:11
 */
@Entity
@Table(name = "Sys_module")
public class SysModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne(targetEntity = SysModule.class)
    @JoinColumn(name = "parent_id")
    private SysModule parent_id;

    @Column
    private String icon;

    public SysModule() {
    }

    public SysModule(String name, SysModule parent_id,String icon) {
        this.name = name;
        this.parent_id = parent_id;
        this.icon = icon;
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

    public SysModule getParent_id() {
        return parent_id;
    }

    public void setParent_id(SysModule parent_id) {
        this.parent_id = parent_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
