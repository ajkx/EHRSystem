package com.victory.ehrsystem.dao.Hrm;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmJobPosition;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 岗位数据层接口
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:19
 */
public interface HrmJobPositionDao extends BaseDao<HrmJobPosition>{

    /**
     * 通过分部来找下属岗位
     * @param company
     * @return
     */
    List<HrmJobPosition> findBySubCompany(HrmSubCompany company);

    /**
     * 通过部门来找下属岗位
     * @param department
     * @return
     */
    List<HrmJobPosition> findByDepartment(HrmDepartment department);

}
