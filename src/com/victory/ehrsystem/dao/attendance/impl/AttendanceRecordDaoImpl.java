package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceRecordDao;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
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

public class AttendanceRecordDaoImpl extends BaseDaoImpl<AttendanceRecord> implements AttendanceRecordDao {
    @Override
    public List<AttendanceRecord> findByHrmResource(HrmResource resource) {
        return find("select a from AttendanceRecord a where resource = ?0",resource);
    }

    @Override
    public List<AttendanceRecord> findByDate(Date date) {
        return find("select a from AttendanceRecord a where punchDate = ?0",date);
    }

    @Override
    public List<AttendanceRecord> findByResourceAndDate(HrmResource resource, java.util.Date beginDate,java.util.Date endDate) {
        return find("select a from AttendanceRecord a where resource = ?0 and date between ?1 and ?2", resource, beginDate,endDate);
    }

    @Override
    public PageInfo listByMachine(Date beginDate, Date endDate, List<HrmResource> resources, int pageNo, int pageSize,int type) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria1 = session.createCriteria(AttendanceRecord.class);
        Criteria criteria2 = session.createCriteria(AttendanceRecord.class);
        if (type != 0) {
            criteria1.add(Restrictions.eq("type",type));
            criteria2.add(Restrictions.eq("type",type));
        }
        criteria1.add(Restrictions.between("date", beginDate, endDate));
        criteria2.add(Restrictions.between("date", beginDate, endDate));
        if(resources != null && resources.size() != 0){
            criteria1.add(Restrictions.in("resource", resources));
            criteria2.add(Restrictions.in("resource", resources));
        }




        criteria1.setFirstResult((pageNo - 1) * pageSize);
        criteria1.setMaxResults(pageSize);
        List list = criteria1.list();
        Long totals = (Long) criteria2.setProjection(Projections.rowCount()).uniqueResult();
        PageInfo pageInfo = new PageInfo(totals, list);
        return pageInfo;
    }
}
