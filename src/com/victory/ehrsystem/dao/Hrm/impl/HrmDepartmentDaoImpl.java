package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmDepartmentDao;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 部门数据操作类
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:25
 */
public class HrmDepartmentDaoImpl extends BaseDaoImpl<HrmDepartment> implements HrmDepartmentDao{
    @Override
    public List<HrmDepartment> findByHrmSubCompany(HrmSubCompany supcompany) {
        return find("select d from HrmDepartment d where d.subcomid = ?0",supcompany);
    }

    @Override
    public List<HrmDepartment> findRootDepByHrmSubCompany(HrmSubCompany supcompany) {
        return find("select d from HrmDepartment d where d.subcomid = ?0 and d.supid is null",supcompany);
    }

    @Override
    public List<HrmDepartment> findBysupDepartment(HrmDepartment supdepartment) {
        return find("select d from HrmDepartment d where d.supid = ?0",supdepartment);
    }


}
