package com.victory.ehrsystem.dao.Hrm;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 人员信息数据层接口
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:19
 */
public interface HrmResourceDao extends BaseDao<HrmResource>{

    /**
     * 通过分部查询
     * @param subCompany
     * @return
     */
    List<HrmResource> findBySubCompany(HrmSubCompany subCompany);

    /**
     * 通过部门查询
     * @param department
     * @return
     */
    List<HrmResource> findByDepartment(HrmDepartment department);


    List<HrmResource> findAllWorking();
}
