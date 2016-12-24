package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmJobActivitiesDao;
import com.victory.ehrsystem.entity.hrm.HrmJobActivities;
import com.victory.ehrsystem.entity.hrm.HrmJobGroups;

import java.util.List;

/**
 * 职务数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:23
 */
public class HrmJobActivitiesDaoImpl extends BaseDaoImpl<HrmJobActivities> implements HrmJobActivitiesDao{
    @Override
    public List<HrmJobActivities> findByHrmJobGroups(HrmJobGroups group) {
        return find("select a from HrmJobActivities a where a.groupid = ?0",group);
    }

}
