package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.DateRecordDao;
import com.victory.ehrsystem.dao.attendance.OverTimeDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.attendance.DateRecord;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.vo.PageInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public class OverTimeDaoImpl extends BaseDaoImpl<OverTimeRecord> implements OverTimeDao{

//    获取加班结束日期小于等于endDate并且为待计算的数据，防止跨天加班计算错误，保证结束日期有打卡数据
    @Override
    public List<OverTimeRecord> findByDateAndResource(java.util.Date beginDate, java.util.Date endDate,HrmResource resource) {
        return find("select r from OverTimeRecord r where date between ?0 and ?1 and resource = ?2",beginDate, endDate, resource);
    }

    @Override
    public List<OverTimeRecord> findByDateForRepeat(java.util.Date beginDate, java.util.Date endDate,HrmResource resource) {
        return find("select r from OverTimeRecord r where resource = ?2 and date between ?0 and ?1 or endDate between ?0 and ?1",beginDate,endDate,resource);
    }

    //没用
    @Override
    public PageInfo list(Date beginDate, Date endDate, List<Integer> resources, int pageNo, int pageSize) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria1 = session.createCriteria(OverTimeRecord.class);
        Criteria criteria2 = session.createCriteria(OverTimeRecord.class);
        criteria1.add(Restrictions.between("date", beginDate, endDate));

        if(resources != null && resources.size() != 0){
            criteria1.add(Restrictions.in("resource", resources));
        }


        criteria2.add(Restrictions.between("date", beginDate, endDate));
        if(resources != null && resources.size() != 0) {
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
