package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.attendance.AttendanceType;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.vo.PageInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */

public class AttendanceDetailDaoImpl extends BaseDaoImpl<AttendanceDetail> implements AttendanceDetailDao{
    @Override
    public List<AttendanceDetail> findByHrmResource(HrmResource resource) {
        return find("select a from AttendanceDetail where resource = ?0",resource);
    }

    @Override
    public List<AttendanceDetail> findByDate(Date date) {
        return find("select a from AttendanceDetail where date = ?0",date);
    }

    @Override
    public List<AttendanceDetail> findByHrmResourceAndDate(HrmResource resource, Date date) {
        return find("select a from AttendanceDetail a where resource = ?0 and date = ?1",resource,date);
    }

    @Override
    public List<AttendanceDetail> findAcrossDayByDate(AttendanceType type,Date date) {
        return find("select a from AttendanceDetail a where attendanceType = ?0 and date <= ?1",type,date);
    }

    @Override
    public PageInfo findDetail(Date beginDate, Date endDate, List<HrmResource> resources, int pageNo, int pageSize) {
//        Session session = getSessionFactory().getCurrentSession();
//        Criteria criteria = session.createCriteria(AttendanceDetail.class);
//        Criteria criteria2 = session.createCriteria(AttendanceDetail.class);
//
//        criteria.add(Restrictions.between("date", beginDate, endDate));
//        if(resources != null && resources.size() != 0){
//            criteria.add(Restrictions.in("resourceId", resources));
//        }
//
//
//        criteria2.add(Restrictions.between("date", beginDate, endDate));
//        if(resources != null && resources.size() != 0) {
//            criteria2.add(Restrictions.in("resourceId", resources));
//        }
//
//        criteria.setFirstResult((pageNo - 1) * pageSize);
//        criteria.setMaxResults(pageSize);
//        List list = criteria.list();
//        Long totals = (Long) criteria2.setProjection(Projections.rowCount()).uniqueResult();
//        PageInfo pageInfo = new PageInfo(totals, list);
//        return pageInfo;
//        return findByList(AttendanceDetail.class, beginDate, endDate, resources, pageNo, pageSize);
        return null;
    }

    @Override
    public PageInfo findCollect(Date beginDate, Date endDate, List<HrmResource> resources, int pageNo, int pageSize) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(AttendanceDetail.class);

        criteria.add(Restrictions.between("date", beginDate, endDate));
        if(resources != null && resources.size() != 0){
            criteria.add(Restrictions.in("resource", resources));
        }
        //获取总数
        criteria.setProjection(Projections.projectionList().add(Projections.countDistinct("resource")));
        long totals = (long) criteria.uniqueResult();

        criteria.setProjection(Projections.projectionList()
                .add(Projections.sum("should_attendance_day"))
                .add(Projections.sum("actual_attendance_day"))
                .add(Projections.sum("should_attendance_time"))
                .add(Projections.sum("actual_attendance_time"))
                .add(Projections.sum("lateCount"))
                .add(Projections.sum("lateTime"))
                .add(Projections.sum("earlyCount"))
                .add(Projections.sum("earlyTime"))
                .add(Projections.sum("absenteeismCount"))
                .add(Projections.sum("absenteeismTime"))
                .add(Projections.sum("overtime_normal"))
                .add(Projections.sum("overtime_weekend"))
                .add(Projections.sum("overtime_festival"))
                .add(Projections.sum("leave_personal"))
                .add(Projections.sum("leave_rest"))
                .add(Projections.sum("leave_business"))
                .add(Projections.groupProperty("resource"))
        );
        criteria.setFirstResult((pageNo - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        PageInfo pageInfo = new PageInfo(totals, criteria.list());
        return pageInfo;
    }
}
