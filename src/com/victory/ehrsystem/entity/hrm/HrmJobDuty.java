package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;

/**
 * 职务表
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 14:22
 */
@Entity
public class HrmJobDuty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @ManyToOne(targetEntity = HrmJobGroup.class)
    @JoinColumn(name = "jobGroupId")
    private HrmJobGroup groupId;

    public HrmJobDuty() {
    }

    public HrmJobDuty(String name, String description, HrmJobGroup groupId) {
        this.name = name;
        this.description = description;
        this.groupId = groupId;
    }

    public HrmJobGroup getGroupId() {

        return groupId;
    }

    public void setGroupId(HrmJobGroup groupId) {
        this.groupId = groupId;
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
