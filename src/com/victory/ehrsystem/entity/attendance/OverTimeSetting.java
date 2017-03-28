package com.victory.ehrsystem.entity.attendance;

import javax.persistence.*;

/**
 * Created by ajkx
 * Date: 2017/3/25.
 * Time:14:42
 */
@Entity
public class OverTimeSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Long offsetTimeUp;

    @Column
    private Long offsetTimeDown;

    @Column
    private Integer calculateType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOffsetTimeUp() {
        return offsetTimeUp;
    }

    public void setOffsetTimeUp(Long offsetTimeUp) {
        this.offsetTimeUp = offsetTimeUp;
    }

    public Long getOffsetTimeDown() {
        return offsetTimeDown;
    }

    public void setOffsetTimeDown(Long offsetTimeDown) {
        this.offsetTimeDown = offsetTimeDown;
    }

    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }
}
