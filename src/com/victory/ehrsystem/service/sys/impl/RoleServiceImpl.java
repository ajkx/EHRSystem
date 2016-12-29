package com.victory.ehrsystem.service.sys.impl;

import com.victory.ehrsystem.dao.sys.SysRoleDao;
import com.victory.ehrsystem.entity.sys.SysResource;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色数据处理层
 *
 * @author ajkx_Du
 * @create 2016-11-01 17:18
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private ResourceService resourceService;

    @Override
    public SysRole createRole(SysRole role) {
        sysRoleDao.save(role);
        return role;
    }

    @Override
    public SysRole updateRole(SysRole role) {
        sysRoleDao.update(role);
        return role;
    }

    @Override
    public void deleteRole(Serializable id) {
        sysRoleDao.delete(SysRole.class,id);
    }

    @Override
    public SysRole findOne(Serializable id) {
        return sysRoleDao.findOne(id);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleDao.findAll(SysRole.class);
    }

    //@Override
    //public Set<String> findRoles(Serializable[] roleIds) {
    //    Set<String> roles = new HashSet<String>();
    //    for (Serializable roleId : roleIds) {
    //        SysRole role = findOne(roleId);
    //        if (role != null) {
    //            roles.add(role.getName());
    //        }
    //    }
    //    return roles;
    //}

    @Override
    public Set<String> findRoles(Set<SysRole> roles) {
        Set<String> role_str = new HashSet<String>();
        for (SysRole role : roles) {
            role_str.add(role.getName());
        }
        return role_str;
    }

    //@Override
    //public Set<String> findPermissions(Serializable[] roleIds) {
    //    Set<Long> resourceIds = new HashSet<Long>();
    //    for (Serializable roleId : roleIds) {
    //        SysRole role = findOne(roleId);
    //        if (role != null) {
    //            resourceIds.addAll(role.getResourcesStr());
    //        }
    //    }
    //    return resourceService.findPermissions(resourceIds);
    //}
    @Override
    public Set<String> findPermissions(Set<SysRole> roles) {
        Set<SysResource> resourceIds = new HashSet<SysResource>();
        for (SysRole role : roles) {
            resourceIds.addAll(role.getResources());
        }
        return resourceService.findPermissions(resourceIds);
    }
}
