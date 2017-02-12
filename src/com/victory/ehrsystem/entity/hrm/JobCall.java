package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;

/**
 * 社会职称表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:22
 */
@Entity
public class JobCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    //评定日期
    @Column(name = "date")
    private String date;

    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId", referencedColumnName = "id", nullable = false)
    private HrmResource resource;

    public JobCall() {
    }

    public JobCall(String name, String date) {
        this.name = name;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String description) {
        this.date = description;
    }
}
