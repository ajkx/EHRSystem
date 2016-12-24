package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;

/**
 * 职务表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:22
 */
@Entity
public class HrmJobActivities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jobactivityname", nullable = false)
    private String name;

    @Column(name = "jobactivitymark", length = 200)
    private String description;

    @ManyToOne(targetEntity = HrmJobGroups.class)
    @JoinColumn(name = "jobgroupid")
    private HrmJobGroups groupid;

    public HrmJobActivities() {
    }

    public HrmJobActivities(String name, String description, HrmJobGroups groupid) {
        this.name = name;
        this.description = description;
        this.groupid = groupid;
    }

    public HrmJobGroups getGroupid() {

        return groupid;
    }

    public void setGroupid(HrmJobGroups groupid) {
        this.groupid = groupid;
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
