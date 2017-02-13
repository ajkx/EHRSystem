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
    public List<HrmDepartment> findByHrmSubCompany(HrmSubCompany supCompany) {
        return find("select d from HrmDepartment d where d.subCompany = ?0",supCompany);
    }

    @Override
    public List<HrmDepartment> findRootDepByHrmSubCompany(HrmSubCompany supCompany) {
        return find("select d from HrmDepartment d where d.subCompany = ?0 and d.parent is null",supCompany);
    }

    @Override
    public List<HrmDepartment> findBySupDepartment(HrmDepartment supDepartment) {
        return find("select d from HrmDepartment d where d.parent = ?0",supDepartment);
    }


}
