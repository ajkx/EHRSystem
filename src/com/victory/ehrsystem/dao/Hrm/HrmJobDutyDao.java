package com.victory.ehrsystem.dao.Hrm;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.hrm.HrmJobDuty;
import com.victory.ehrsystem.entity.hrm.HrmJobGroup;

import java.util.List;

/**
 * 职务数据层接口
 *
 * @author ajkx_Du
 * @createDate 2016-10-19 17:19
 */
public interface HrmJobDutyDao extends BaseDao<HrmJobDuty>{
    /**
     * 根据职务类别来获取职务
     * @param group
     * @return
     */
    List<HrmJobDuty> findByHrmJobGroups(HrmJobGroup group);

}
