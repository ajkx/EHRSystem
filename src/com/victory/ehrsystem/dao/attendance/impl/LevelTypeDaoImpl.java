package com.victory.ehrsystem.dao.attendance.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.attendance.AttendanceTypeDao;
import com.victory.ehrsystem.dao.attendance.LevelTypeDao;
import com.victory.ehrsystem.entity.attendance.AttendanceType;
import com.victory.ehrsystem.entity.attendance.LevelRecord;
import com.victory.ehrsystem.entity.attendance.LevelType;

/**
 * @author ajkx_Du
 * @create 2016-12-09 21:16
 */

public class LevelTypeDaoImpl extends BaseDaoImpl<LevelType> implements LevelTypeDao {

    @Override
    public LevelType personalType() {
        return get(LevelRecord.class,1);
    }

    @Override
    public LevelType sickType() {
        return get(LevelRecord.class,2);
    }

    @Override
    public LevelType businessType() {
        return get(LevelRecord.class,3);
    }

    @Override
    public LevelType injuryType() {
        return get(LevelRecord.class,4);
    }

    @Override
    public LevelType deliveryType() {
        return get(LevelRecord.class,5);
    }

    @Override
    public LevelType marriedType() {
        return get(LevelRecord.class,6);
    }

    @Override
    public LevelType funeralType() {
        return get(LevelRecord.class,7);
    }

    @Override
    public LevelType annualType() {
        return get(LevelRecord.class,8);
    }

    @Override
    public LevelType restType() {
        return get(LevelRecord.class,9);
    }
}
