package com.victory.ehrsystem.dao.Hrm;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.util.List;

/**
 * 人员信息数据层接口
 *
 * @author ajkx_Du
 * @create 2016-10-19 17:19
 */
public interface HrmResourceDao extends BaseDao<HrmResource>{

    /**
     * 通过分部查询
     * @param subCompany
     * @return
     */
    List<HrmResource> findBySubCompany(HrmSubCompany subCompany);

    /**
     * 通过部门查询
     * @param department
     * @return
     */
    List<HrmResource> findByDepartment(HrmDepartment department);

    /**
     * 找出在职员工
     * @return
     */
    List<HrmResource> findAllWorking();

    /**
     * 找出没有排班的员工
     * @return
     */
    List<HrmResource> findNoSchedule();

    /**
     * 根据排班找出相应的员工
     * @param schedule
     * @return
     */
    List<HrmResource> findBySchedule(AttendanceSchedule schedule);

    /**
     * 找出所有非管理员的员工
     * @return
     */
    List<HrmResource> findNoManager();

    /**
     * name可为部门名称或者姓名
     * @param names
     * @return
     */
    List<HrmResource> findByName(String[] names);
}
