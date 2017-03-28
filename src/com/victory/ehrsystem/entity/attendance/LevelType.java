package com.victory.ehrsystem.entity.attendance;

import javax.persistence.*;

/**
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:11:38
 */
@Entity
public class LevelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String name;

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
}
