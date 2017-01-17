package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

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
        return find("select r from HrmResource r where r.subCompanyid = ?0",subCompany);
    }

    @Override
    public List<HrmResource> findByDepartment(HrmDepartment department) {
        return find("select r from HrmResource r where r.departmentid = ?0",department);
    }

    @Override
    public List<HrmResource> findAllWorking() {
        return find("select r from HrmResource r where r.status in (0,1,2,3)");
    }

    @Override
    public List<HrmResource> findNoSchedule() {
        return find("select r from HrmResource r where r.schedule is null");
    }

    @Override
    public List<HrmResource> findBySchedule(AttendanceSchedule schedule) {
        return find("select r from HrmResource r where r.schedule =?0",schedule);
    }

    @Override
    public List<HrmResource> findNoManager() {
        return find("select r from HrmResource r where r.user is null");
    }


}
