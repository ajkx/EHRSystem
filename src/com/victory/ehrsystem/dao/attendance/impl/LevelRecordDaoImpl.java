package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.LevelRecordDao;
import com.victory.ehrsystem.dao.attendance.OverTimeDao;
import com.victory.ehrsystem.entity.attendance.LevelRecord;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.vo.PageInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.List;

/**
 * Created by ajkx on 2017/2/27.
 */
public class LevelRecordDaoImpl extends BaseDaoImpl<LevelRecord> implements LevelRecordDao{

    @Override
    public List<LevelRecord> findByDate(java.util.Date beginDate, java.util.Date endDate) {
        return find("select r from LevelRecord r where date <= ?0 and status = ?1",endDate, OverTimeRecord.Status.normal);
    }

    @Override
    public List<LevelRecord> findByDateAndResource(java.util.Date beginDate, java.util.Date endDate,HrmResource resource) {
        return find("select r from LevelRecord r where resource = ?0 and date between ?1 and ?2 or endDate between ?1 and ?2 " +
                "or ?1 between date and endDate or ?2 between date and endDate",resource,beginDate,endDate);
    }
}
