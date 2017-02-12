package com.victory.ehrsystem.dao.Hrm.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.Hrm.HrmJobDutyDao;
import com.victory.ehrsystem.entity.hrm.HrmJobDuty;
import com.victory.ehrsystem.entity.hrm.HrmJobGroup;

import java.util.List;

/**
 * 职务数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:23
 */
public class HrmJobDutyDaoImpl extends BaseDaoImpl<HrmJobDuty> implements HrmJobDutyDao {
    @Override
    public List<HrmJobDuty> findByHrmJobGroups(HrmJobGroup group) {
        return find("select a from HrmJobDuty a where a.groupId = ?0",group);
    }

}
