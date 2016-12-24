package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;

/**
 * 学历表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:22
 */
@Entity
public class HrmEducationLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",length = 200)
    private String description;

    public HrmEducationLevel() {
    }

    public HrmEducationLevel(String name, String description) {

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
