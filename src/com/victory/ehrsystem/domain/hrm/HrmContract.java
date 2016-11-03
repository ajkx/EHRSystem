package com.victory.ehrsystem.domain.hrm;

import javax.persistence.*;

/**
 * 合同表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:53
 */
@Entity
public class HrmContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contractname")
    private String name;

    @OneToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "contractman", unique = true)
    private HrmResource man;

    @Column(name = "contractstartdate")
    private Character[] startdate;

    @Column(name = "contractenddate")
    private Character[] enddate;

    @Column(name = "proenddate")
    private Character[] proenddate;

    @ManyToOne(targetEntity = HrmContractType.class)
    @JoinColumn(name = "contracttypeid")
    private HrmContractType typeid;

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

    public HrmResource getMan() {
        return man;
    }

    public void setMan(HrmResource man) {
        this.man = man;
    }

    public Character[] getStartdate() {
        return startdate;
    }

    public void setStartdate(Character[] startdate) {
        this.startdate = startdate;
    }

    public Character[] getEnddate() {
        return enddate;
    }

    public void setEnddate(Character[] enddate) {
        this.enddate = enddate;
    }

    public Character[] getProenddate() {
        return proenddate;
    }

    public void setProenddate(Character[] proenddate) {
        this.proenddate = proenddate;
    }

    public HrmContractType getTypeid() {
        return typeid;
    }

    public void setTypeid(HrmContractType typeid) {
        this.typeid = typeid;
    }

    public HrmContract(String name, HrmResource man, Character[] startdate, Character[] enddate, Character[] proenddate, HrmContractType typeid) {

        this.name = name;
        this.man = man;
        this.startdate = startdate;
        this.enddate = enddate;
        this.proenddate = proenddate;
        this.typeid = typeid;
    }

    public HrmContract() {

    }
}
