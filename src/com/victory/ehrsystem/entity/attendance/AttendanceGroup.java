package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.hrm.HrmResource;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by ajkx on 2017/2/16.
 */
@Entity
public class AttendanceGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "groupName",nullable = false)
    private String name;

    //1 代表 固定班制 2 代表 自由排班  3 代表 自由打卡
    @Column(name="groupType",nullable = false)
    private Integer groupType;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "monday")
    private AttendanceSchedule monday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "tuesday")
    private AttendanceSchedule tuesday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "wednesday")
    private AttendanceSchedule wednesday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "thursday")
    private AttendanceSchedule thursday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "friday")
    private AttendanceSchedule friday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "saturday")
    private AttendanceSchedule saturday;

    @ManyToOne(targetEntity = AttendanceSchedule.class)
    @JoinColumn(name = "sunday")
    private AttendanceSchedule sunday;

    @Column(name = "autoOff")
    private Boolean isAuto;

    private String description;
    //相关人员
    @OneToMany(targetEntity = HrmResource.class)
    @JoinTable(name = "group_resource",
            joinColumns =@JoinColumn(name = "group_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id",referencedColumnName = "id",unique = true))
    private Set<HrmResource> resources;

    @Column(name = "resourceStr")
    private String resourceStr;

    //自由排班相关的班次
    @ManyToMany(targetEntity = AttendanceSchedule.class)
    @JoinTable(name = "attendanceGroup_Schedule",
        joinColumns = @JoinColumn(name="group_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "schedule_id",referencedColumnName = "id"))
    private Set<AttendanceSchedule> schedules;

    //必须打卡的日期
    @ElementCollection(targetClass = Date.class)
    @CollectionTable(name="mustPunchDate",joinColumns = @JoinColumn(name = "group_id",nullable = false))
    @Column(name = "punchDate")
    private Set<Date> mustPunchDate;

    //不需要打卡的日期
    @ElementCollection(targetClass = Date.class)
    @CollectionTable(name="noNeedPunchDate",joinColumns = @JoinColumn(name = "group_id",nullable = false))
    @Column(name = "noNeedDate")
    private Set<Date> noNeedPunchDate;

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

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public AttendanceSchedule getMonday() {
        return monday;
    }

    public void setMonday(AttendanceSchedule monday) {
        this.monday = monday;
    }

    public AttendanceSchedule getTuesday() {
        return tuesday;
    }

    public void setTuesday(AttendanceSchedule tuesday) {
        this.tuesday = tuesday;
    }

    public AttendanceSchedule getWednesday() {
        return wednesday;
    }

    public void setWednesday(AttendanceSchedule wednesday) {
        this.wednesday = wednesday;
    }

    public AttendanceSchedule getThursday() {
        return thursday;
    }

    public void setThursday(AttendanceSchedule thursday) {
        this.thursday = thursday;
    }

    public AttendanceSchedule getFriday() {
        return friday;
    }

    public void setFriday(AttendanceSchedule friday) {
        this.friday = friday;
    }

    public AttendanceSchedule getSaturday() {
        return saturday;
    }

    public void setSaturday(AttendanceSchedule saturday) {
        this.saturday = saturday;
    }

    public AttendanceSchedule getSunday() {
        return sunday;
    }

    public void setSunday(AttendanceSchedule sunday) {
        this.sunday = sunday;
    }

    public Boolean getAuto() {
        return isAuto;
    }

    public void setAuto(Boolean auto) {
        isAuto = auto;
    }

    public Set<HrmResource> getResources() {
        return resources;
    }

    public void setResources(Set<HrmResource> resources) {
        this.resources = resources;
    }

    public Set<AttendanceSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<AttendanceSchedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Date> getMustPunchDate() {
        return mustPunchDate;
    }

    public void setMustPunchDate(Set<Date> mustPunchDate) {
        this.mustPunchDate = mustPunchDate;
    }

    public Set<Date> getNoNeedPunchDate() {
        return noNeedPunchDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNoNeedPunchDate(Set<Date> noNeedPunchDate) {
        this.noNeedPunchDate = noNeedPunchDate;
    }

    public String getResourceStr() {
        return resourceStr;
    }

    public void setResourceStr(String resourceStr) {
        this.resourceStr = resourceStr;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;

        if(obj != null && obj.getClass() == AttendanceGroup.class){
            return this.id == ((AttendanceGroup) obj).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
