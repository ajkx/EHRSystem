package com.victory.ehrsystem.service.sys;

import com.victory.ehrsystem.domain.sys.SysRole;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by ajkx_Du on 2016/11/3.
 */
public interface RoleService {

    public SysRole createRole(SysRole role);

    public SysRole updateRole(SysRole role);

    public void deleteRole(Serializable id);

    public SysRole findOne(Serializable id);

    public List<SysRole> findAll();

    /**
     * 根据角色编号获取角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Serializable[] roleIds);

    Set<String> findPermissions(Serializable[] roleIds);
}
