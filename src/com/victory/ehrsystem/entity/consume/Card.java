package com.victory.ehrsystem.entity.consume;

import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ajkx
 * Date: 2017/3/2.
 * Time:10:50
 */
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cardNo;

    @OneToOne(targetEntity = HrmResource.class)
    private HrmResource resource;

    @OneToMany(targetEntity = AttendanceDetail.class,mappedBy = "card")
    private Set<AttendanceRecord> records;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public Set<AttendanceRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<AttendanceRecord> records) {
        this.records = records;
    }
}
