package com.victory.ehrsystem.service.hrm;

import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by ajkx_Du on 2016/11/18.
 */
public interface OrganizationService {

    public HrmSubCompany findOne_SubCompany(Serializable id);

    public List<HrmSubCompany> findAll_SubCompany();

    public HrmSubCompany update_SubCompany(HrmSubCompany subCompany);

    public HrmSubCompany create_SubCompany(HrmSubCompany subCompany);

    public void delete_SubCompany(HrmSubCompany subCompany);


    public HrmDepartment findOne_Department(Serializable id);

    public List<HrmDepartment> findAll_Department();

    public HrmDepartment update_Department(HrmDepartment department);

    public HrmDepartment create_Department(HrmDepartment department);

    public void delete_Department(HrmDepartment department);

    /**
     * 组织架构树菜单初次载入读取的最顶层的分部
     * @return
     */
    public List<HrmSubCompany> findAllSubCompany_NoParent();


    /**
     * 根据选择分部 查询下属所有分部和部门
     * @param subCompany
     * @return
     */
    public LinkedHashMap<String, ArrayList> findALLOrganization(HrmSubCompany subCompany);

    /**
     * 根据选择的部门 查询下属所有部门
     * @param department
     * @return
     */
    public List<HrmDepartment> findAllDepartmentByDepartment(HrmDepartment department);

    /**
     * 根据选择的分部，查询下属所有部门
     * @param subCompany
     * @return
     */
    public List<HrmDepartment> findAllDepartmentBySubcompany(HrmSubCompany subCompany);

    /**
     *根据选择的分部，查询下属所有分部
     */
    public List<HrmSubCompany> findAllSubcompanyBySubcompany(HrmSubCompany subCompany);

    /**
     * 找出分部下的根部门
     * @param subCompany
     * @return
     */
    public List<HrmDepartment> findRootDepartmentBySubcompany(HrmSubCompany subCompany);
}
