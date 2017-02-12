package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmSubCompanyDao;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 分部数据操作类
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:25
 */
public class HrmSubCompanyDaoImpl extends BaseDaoImpl<HrmSubCompany> implements HrmSubCompanyDao{
    @Override
    public List<HrmSubCompany> findByHrmSubCompany(HrmSubCompany subCompany) {
        return find("select s from HrmSubCompany s where s.parent = ?0",subCompany);
    }

    @Override
    public List<HrmSubCompany> findAll_NoParent() {
        return find("select s from HrmSubCompany s where s.parent is null");
    }
}
