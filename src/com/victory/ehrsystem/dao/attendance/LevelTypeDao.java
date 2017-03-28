package com.victory.ehrsystem.dao.attendance;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.LevelType;


/**
 * 考勤明细DAO接口
 *
 * @author ajkx_Du
 * @create 2016-12-09 20:37
 */
public interface LevelTypeDao extends BaseDao<LevelType>{

    /**
     * 事假
     * @return
     */
    LevelType personalType();

    /**
     * 病假
     * @return
     */
    LevelType sickType();

    /**
     * 出差
     * @return
     */
    LevelType businessType();

    /**
     * 工伤假
     * @return
     */
    LevelType injuryType();

    /**
     * 产假
     * @return
     */
    LevelType deliveryType();

    /**
     * 婚假
     * @return
     */
    LevelType marriedType();

    /**
     * 丧假
     * @return
     */
    LevelType funeralType();

    /**
     * 年假
     * @return
     */
    LevelType annualType();

    /**
     * 调休
     * @return
     */
    LevelType restType();
}
