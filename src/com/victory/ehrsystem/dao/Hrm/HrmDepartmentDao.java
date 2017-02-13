package com.victory.ehrsystem.dao.Hrm;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 部门数据层接口
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:19
 */
public interface HrmDepartmentDao extends BaseDao<HrmDepartment>{

    /**
     * 根据上级分部找寻下属所有部门
     * @param supCompany
     * @return
     */
    List<HrmDepartment> findByHrmSubCompany(HrmSubCompany supCompany);

    /**
     * 找分部下的根部门
     * @param supCompany
     * @return
     */
    List<HrmDepartment> findRootDepByHrmSubCompany(HrmSubCompany supCompany);

    /**
     * 通过上级分部来找下属部门
     * @param supDepartment
     * @return
     */
    List<HrmDepartment> findBySupDepartment(HrmDepartment supDepartment);

}
