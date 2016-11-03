package com.victory.ehrsystem.dao.sys.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.sys.SysRoleDao;
import com.victory.ehrsystem.domain.sys.SysRole;
import com.victory.ehrsystem.domain.sys.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-27 15:11
 */
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements SysRoleDao{

    @Override
    public List<SysRole> findByUser(User user) {
        String temp = user.getRoleids();
        String[] roleArray = temp.split(",");
        List<SysRole> roles = new ArrayList<SysRole>();
        for(int i = 0; i < roleArray.length; i++) {
            SysRole temp1 = get(SysRole.class,Integer.parseInt(roleArray[i]));
            roles.add(temp1);
        }
        return roles;
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
