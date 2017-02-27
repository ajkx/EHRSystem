package com.victory.ehrsystem.entity.attendance;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ajkx on 2017/2/27.
 */
@Entity
public class DateRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
