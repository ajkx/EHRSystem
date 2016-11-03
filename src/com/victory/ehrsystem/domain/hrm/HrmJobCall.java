package com.victory.ehrsystem.domain.hrm;

import javax.persistence.*;

/**
 * 职称表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:22
 */
@Entity
public class HrmJobCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",length = 200)
    private String description;

    public HrmJobCall() {
    }

    public HrmJobCall(String name, String description) {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
