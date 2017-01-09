package com.victory.ehrsystem.service.hrm.impl;

import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-11-21 15:00
 */
@Service
public class HrmResourceService extends BaseService<HrmResource>{

    public List<HrmResource> findBySchedule(AttendanceSchedule schedule){
        return null;
    }
}
