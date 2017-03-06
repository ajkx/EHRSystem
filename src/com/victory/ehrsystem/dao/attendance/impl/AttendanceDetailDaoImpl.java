package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
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
        return find("select a from AttendanceDetail where resourceId = ?0",resource);
    }

    @Override
    public List<AttendanceDetail> findByDate(Date date) {
        return find("select a from AttendanceDetail where date = ?0",date);
    }

    @Override
    public PageInfo findByPage(Date beginDate, Date endDate, List<HrmResource> resources,int pageNo,int pageSize) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria1 = session.createCriteria(AttendanceDetail.class);
        Criteria criteria2 = session.createCriteria(AttendanceDetail.class);

        criteria1.add(Restrictions.between("date", beginDate, endDate));
        if(resources != null && resources.size() != 0){
            criteria1.add(Restrictions.in("resourceId", resources));
        }


        criteria2.add(Restrictions.between("date", beginDate, endDate));
        if(resources != null && resources.size() != 0) {
            criteria2.add(Restrictions.in("resourceId", resources));
        }

        criteria1.setFirstResult((pageNo - 1) * pageSize);
        criteria1.setMaxResults(pageSize);

        Long totals = (Long) criteria2.setProjection(Projections.rowCount()).uniqueResult();
        PageInfo pageInfo = new PageInfo(totals, criteria1.list());
        return pageInfo;
    }
}
