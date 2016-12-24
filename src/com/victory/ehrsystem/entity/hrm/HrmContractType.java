package com.victory.ehrsystem.entity.hrm;

import javax.persistence.*;

/**
 * 合同类别表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:45
 */
@Entity
public class HrmContractType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "typename")
    private String name;

    @Column(name = "ishirecontract")
    private char isrecontract;

    @Column(name = "saveurl")
    private String url;

    @ManyToOne(targetEntity = HrmContractTemplet.class)
    @JoinColumn(name = "contracttempletid")
    private HrmContractTemplet templet;

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

    public char getIsrecontract() {
        return isrecontract;
    }

    public void setIsrecontract(char isrecontract) {
        this.isrecontract = isrecontract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HrmContractTemplet getTemplet() {
        return templet;
    }

    public void setTemplet(HrmContractTemplet templet) {
        this.templet = templet;
    }

    public HrmContractType() {

    }

    public HrmContractType(String name, char isrecontract, String url, HrmContractTemplet templet) {

        this.name = name;
        this.isrecontract = isrecontract;
        this.url = url;
        this.templet = templet;
    }
}
