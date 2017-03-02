package com.victory.ehrsystem.entity.attendance;

import javax.persistence.*;

/**
 * Created by ajkx
 * Date: 2016/12/14.
 * Time:11:02
 */
@Entity
public class AttendanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String name;

    //比较类别的优先级
    @Column
    private Integer priority;
    public AttendanceType() {
    }

    public AttendanceType(String name,int priority) {
        this.name = name;
        this.priority = priority;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
