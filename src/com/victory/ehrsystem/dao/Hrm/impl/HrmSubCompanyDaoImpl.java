package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmSubCompanyDao;
import com.victory.ehrsystem.domain.hrm.HrmSubCompany;

import java.util.List;

/**
 * 分部数据操作类
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:25
 */
public class HrmSubCompanyDaoImpl extends BaseDaoImpl<HrmSubCompany> implements HrmSubCompanyDao{
    @Override
    public List<HrmSubCompany> findByHrmSubCompany(HrmSubCompany supcompany) {
        return find("select s from HrmSubCompany s where s.supid = ?0",supcompany);
    }
}
