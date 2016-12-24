package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmJobTitlesDao;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmJobTitles;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 岗位数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:23
 */
public class HrmJobTitlesDaoImpl extends BaseDaoImpl<HrmJobTitles> implements HrmJobTitlesDao{
    @Override
    public List<HrmJobTitles> findBySubCompany(HrmSubCompany subcompany) {
        return find("select t from HrmJobTitles t where t.depid in (select d from HrmDepartment d where d.subcomid = ?0)",subcompany);
    }

    @Override
    public List<HrmJobTitles> findByDepartment(HrmDepartment department) {
        return find("select j from HrmJobTitles j where j.depid = ?0",department);
    }

}
