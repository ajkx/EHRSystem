package com.victory.ehrsystem.domain.hrm;

import javax.persistence.*;

/**
 * 办公地点表
 *
 * @author ajkx_Du
 * @create 2016-10-19 14:35
 */
@Entity
public class HrmLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "locationname",nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "postcode",length = 20)
    private String postcode;

    @Column(name = "locationcity")
    private String city;

    @Column(name = "countryid")
    private int countryid;

    @Column(name = "telephone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "showOrder")
    private double showOrder;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(double showOrder) {
        this.showOrder = showOrder;
    }

    public HrmLocation() {

    }

    public HrmLocation(String name, String address, String postcode, String city, int countryid, String phone, String fax, double showOrder) {

        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.countryid = countryid;
        this.phone = phone;
        this.fax = fax;
        this.showOrder = showOrder;
    }
}
