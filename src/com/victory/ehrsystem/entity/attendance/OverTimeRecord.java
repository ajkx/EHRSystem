package com.victory.ehrsystem.entity.attendance;

import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.sys.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by ajkx
 * Date: 2017/3/10.
 * Time:18:48
 */
@Entity
@Table(name = "OverTimeRecord")
public class OverTimeRecord {

    public static enum Status {
        normal("待计算"), success("完成"),error("作废"),abnormal("异常");
        private final String info;

        private Status(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    ////    申请人
//    @ManyToMany(targetEntity = HrmResource.class)
//    @JoinTable(name = "overTimeRecord_resource",
//            joinColumns = @JoinColumn(name = "overTimeRecord_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "resource_id",referencedColumnName = "id"))
//    private Set<HrmResource resource;
    @ManyToOne(targetEntity = HrmResource.class)
    @JoinColumn(name = "resourceId")
    private HrmResource resource;

    //1代表平时加班 2代表周末加班 3代表节日加班
    @Column
    private Integer type;

//    加班开始日期 时间
    @Column(name = "beginDate")
    private Date date;

    //加班结束日期 时间
    @Column(name = "endDate")
    private Date endDate;

    //实际上班打卡时间
    @Column
    private Date timeUp;

    //实际下班打卡时间
    @Column
    private Date timeDown;

//    计划加班时数
    @Column
    private Long count;

//    实际加班时数
    @Column
    private Long actualCount;

    //加班原因
    @Column
    private String reason;

    //状态
    @Column
    private Status status;

    //备注
    @Column
    private String remark;

    @ManyToOne(targetEntity = AttendanceDetail.class)
    @JoinColumn(name = "detailId",referencedColumnName = "id")
    private AttendanceDetail detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HrmResource getResource() {
        return resource;
    }

    public void setResource(HrmResource resource) {
        this.resource = resource;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date beginDate) {
        this.date = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTimeUp() {
        return timeUp;
    }

    public void setTimeUp(Date timeUp) {
        this.timeUp = timeUp;
    }

    public Date getTimeDown() {
        return timeDown;
    }

    public void setTimeDown(Date timeDown) {
        this.timeDown = timeDown;
    }

    public Long getActualCount() {
        return actualCount;
    }

    public void setActualCount(Long actualCount) {
        this.actualCount = actualCount;
    }

    public AttendanceDetail getDetail() {
        return detail;
    }

    public void setDetail(AttendanceDetail detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;

        if(obj != null && obj.getClass() == OverTimeRecord.class){
            return this.id == ((OverTimeRecord) obj).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
