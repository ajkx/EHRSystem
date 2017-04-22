package com.victory.ehrsystem.entity.attendance;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ajkx
 * Date: 2017/4/22.
 * Time:16:04
 */
@Entity(name = "CustomHoliday")
public class CustomHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(unique = true)
    private Date date;

    @Column
    private String description;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;

        if(obj != null && obj.getClass() == CustomHoliday.class){
            return this.id == ((CustomHoliday) obj).getId() || this.date == ((CustomHoliday)obj).getDate();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode() * 19 +this.date.hashCode() * 11;
    }
}
