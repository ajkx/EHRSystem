package com.victory.ehrsystem.dao.Hrm;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.domain.hrm.HrmJobActivities;
import com.victory.ehrsystem.domain.hrm.HrmJobGroups;

import java.util.List;

/**
 * 职务数据层接口
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:19
 */
public interface HrmJobActivitiesDao extends BaseDao<HrmJobActivities>{
    /**
     * 根据职务类别来获取职务
     * @param group
     * @return
     */
    List<HrmJobActivities> findByHrmJobGroups(HrmJobGroups group);

}
