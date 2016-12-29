package com.victory.ehrsystem.dao.sys.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.sys.SysRoleDao;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.entity.sys.User;

import java.io.Serializable;
import java.util.List;

/**
 * 角色数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-27 15:11
 */
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements SysRoleDao{

    @Override
    public List<User> findUsers(SysRole role) {
        return null;
    }

    @Override
    public String findRoleStr() {
        return null;
    }

    @Override
    public SysRole findOne(Serializable id) {
        return get(SysRole.class, id);
    }
}
