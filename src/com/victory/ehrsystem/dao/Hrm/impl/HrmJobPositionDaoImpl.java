package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmJobPositionDao;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmJobPosition;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 岗位数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:23
 */
public class HrmJobPositionDaoImpl extends BaseDaoImpl<HrmJobPosition> implements HrmJobPositionDao {
    @Override
    public List<HrmJobPosition> findBySubCompany(HrmSubCompany subCompany) {
        return find("select t from HrmJobPosition t where t.depid in (select d from HrmDepartment d where d.subcomid = ?0)",subCompany);
    }

    @Override
    public List<HrmJobPosition> findByDepartment(HrmDepartment department) {
        return find("select j from HrmJobPosition j where j.depid = ?0",department);
    }

}
