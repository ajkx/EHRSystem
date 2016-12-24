package com.victory.ehrsystem.service.hrm.impl;

import com.victory.ehrsystem.dao.Hrm.HrmDepartmentDao;
import com.victory.ehrsystem.dao.Hrm.HrmSubCompanyDao;
import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;
import com.victory.ehrsystem.service.hrm.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-11-18 15:06
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private HrmSubCompanyDao subCompanyDao;

    @Autowired
    private HrmDepartmentDao departmentDao;

    @Override
    public HrmSubCompany findOne_SubCompany(Serializable id) {
        return subCompanyDao.get(HrmSubCompany.class, id);
    }

    @Override
    public List<HrmSubCompany> findAll_SubCompany() {
        return subCompanyDao.findAll(HrmSubCompany.class);
    }

    @Override
    public HrmSubCompany update_SubCompany(HrmSubCompany subCompany) {
        subCompanyDao.update(subCompany);
        return subCompany;
    }

    @Override
    public HrmSubCompany create_SubCompany(HrmSubCompany subCompany) {
        subCompanyDao.save(subCompany);
        return subCompany;
    }

    @Override
    public void delete_SubCompany(HrmSubCompany subCompany) {
        subCompanyDao.delete(subCompany);
    }

    @Override
    public HrmDepartment findOne_Department(Serializable id) {
        return departmentDao.get(HrmDepartment.class, id);
    }

    @Override
    public List<HrmDepartment> findAll_Department() {
        return departmentDao.findAll(HrmDepartment.class);
    }

    @Override
    public HrmDepartment update_Department(HrmDepartment department) {
        departmentDao.update(department);
        return department;
    }

    @Override
    public HrmDepartment create_Department(HrmDepartment department) {
        departmentDao.save(department);
        return department;
    }

    @Override
    public void delete_Department(HrmDepartment department) {
        departmentDao.delete(department);
    }

    @Override
    public List<HrmSubCompany> findAllSubCompany_NoParent() {
        List<HrmSubCompany> list = subCompanyDao.findAll_NoParent();
        return list;
    }

    @Override
    public LinkedHashMap<String, ArrayList> findALLOrganization(HrmSubCompany subCompany) {
        List<HrmSubCompany> subList = findAllSubcompanyBySubcompany(subCompany);
        List<HrmDepartment> depList = findAllDepartmentBySubcompany(subCompany);

        LinkedHashMap<String,ArrayList> maps = new LinkedHashMap<>();
        maps.put("department", (ArrayList) depList);
        maps.put("subcompany", (ArrayList) subList);
        return maps;
    }

    @Override
    public List<HrmDepartment> findAllDepartmentByDepartment(HrmDepartment department) {
        return departmentDao.findBysupDepartment(department);
    }

    @Override
    public List<HrmDepartment> findAllDepartmentBySubcompany(HrmSubCompany subCompany) {
        return departmentDao.findByHrmSubCompany(subCompany);
    }

    @Override
    public List<HrmSubCompany> findAllSubcompanyBySubcompany(HrmSubCompany subCompany) {
        return subCompanyDao.findByHrmSubCompany(subCompany);
    }

    @Override
    public List<HrmDepartment> findRootDepartmentBySubcompany(HrmSubCompany subCompany) {
        return departmentDao.findRootDepByHrmSubCompany(subCompany);
    }
}
