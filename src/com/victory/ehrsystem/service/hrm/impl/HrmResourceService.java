package com.victory.ehrsystem.service.hrm.impl;

import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ajkx_Du
 * @create 2016-11-21 15:00
 */
@Service
public class HrmResourceService extends BaseService<HrmResource>{

    @Autowired
    private HrmResourceDao hrmResourceDao;

    public List<HrmResource> findBySchedule(AttendanceSchedule schedule){
        return null;
    }

    public List<HrmResource> findNoManager(){
        return hrmResourceDao.findNoManager();
    }

    @Override
    public PageInfo findByPage(Class<HrmResource> entityClazz, HttpServletRequest request) {
        PageInfo info = super.findByPage(entityClazz, request);
        List<HrmResource> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (HrmResource resource : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",resource.getId());
            map.put("name", resource.getName());
            map.put("workCode",StringUtil.nullString(resource.getWorkCode()));
            map.put("subCompany", resource.getSubCompany() == null ? "" :resource.getSubCompany().getName());
            map.put("department", resource.getDepartment() == null ? "" :resource.getDepartment().getName());
            map.put("manager", resource.getManager() == null ? "" :resource.getManager().getName());
            map.put("jobPosition", resource.getJobPosition() == null ? "" :resource.getJobPosition().getName());
            map.put("status", StringUtil.nullString(resource.getStatus()+""));
            map.put("officePhone", StringUtil.nullString(resource.getOfficePhone()));
            map.put("mobile", StringUtil.nullString(resource.getMobile()));
            map.put("email", StringUtil.nullString(resource.getEmail()));
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public List<HrmResource> findBySubCompany(HrmSubCompany subCompany) {
        return hrmResourceDao.findBySubCompany(subCompany);
    }

    public List<HrmResource> findByDepartment(HrmDepartment department) {
        return hrmResourceDao.findByDepartment(department);
    }

    public List<HrmResource> findByName(String name) {
        String[] names = name.split(",");
        return hrmResourceDao.findByName(names);
    }
}
