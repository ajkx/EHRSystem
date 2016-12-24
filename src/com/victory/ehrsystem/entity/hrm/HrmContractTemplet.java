package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;

/**
 * 职称表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:22
 */
@Entity
public class HrmContractTemplet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "templetname",nullable = false)
    private String name;

    @Column(name = "templetdocid",length = 200)
    private int description;

    public HrmContractTemplet() {
    }

    public HrmContractTemplet(String name, int description) {

        this.name = name;
        this.description = description;
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

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }
}
