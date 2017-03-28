package com.victory.ehrsystem.entity.sys;

import javax.persistence.*;

/**
 * Created by ajkx
 * Date: 2017/3/9.
 * Time:10:25
 */
@Entity
public class SysOperateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String userName;


}
