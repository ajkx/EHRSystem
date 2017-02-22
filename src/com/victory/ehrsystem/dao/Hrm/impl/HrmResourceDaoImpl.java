package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * 职称数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:23
 */
public class HrmResourceDaoImpl extends BaseDaoImpl<HrmResource> implements HrmResourceDao{
    @Override
    public List<HrmResource> findBySubCompany(HrmSubCompany subCompany) {
        return find("select r from HrmResource r where r.subCompany = ?0 and r.status != 2",subCompany);
    }

    @Override
    public List<HrmResource> findByDepartment(HrmDepartment department) {
        return find("select r from HrmResource r where r.department = ?0 and r.status != 2",department);
    }

    @Override
    public List<HrmResource> findAllWorking() {
        return find("select r from HrmResource r where r.status != 2");
    }

    @Override
    public List<HrmResource> findNoSchedule() {
        return find("select r from HrmResource r where r.schedule is null and r.status != 2");
    }

    @Override
    public List<HrmResource> findBySchedule(AttendanceSchedule schedule) {
        return find("select r from HrmResource r where r.schedule =?0 and r.status != 2",schedule);
    }

    @Override
    public List<HrmResource> findNoManager() {
        return find("select r from HrmResource r where r.user is null and r.status != 2");
    }

    @Override
    public List<HrmResource> findByName(String[] names) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(HrmResource.class);
        criteria.createAlias("department", "dept");
        Disjunction dis = Restrictions.disjunction();
        for (String temp : names) {
            dis.add(Restrictions.ilike("name", temp, MatchMode.ANYWHERE));
            dis.add(Restrictions.ilike("dept.name", temp, MatchMode.ANYWHERE));

        }
        criteria.add(dis);
        criteria.addOrder(Order.asc("dept.id"));
        criteria.add(Restrictions.ne("status", 2));
        return criteria.list();
    }


}
