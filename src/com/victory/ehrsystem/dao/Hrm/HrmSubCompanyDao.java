package com.victory.ehrsystem.dao.Hrm;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 分部数据层接口
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:19
 */
public interface HrmSubCompanyDao extends BaseDao<HrmSubCompany>{

    /**
     * 根据上级分部找寻下属所有分部
     * @param supcompany
     * @return
     */
    List<HrmSubCompany> findByHrmSubCompany(HrmSubCompany supcompany);

    /**
     *
     */
    List<HrmSubCompany> findAll_NoParent();
}
