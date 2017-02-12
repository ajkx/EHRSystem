package com.victory.ehrsystem.dao.sys;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.entity.sys.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ajkx_Du on 2016/10/27.
 */
public interface SysRoleDao extends BaseDao<SysRole> {
    public List<User> findUsers(SysRole role);

    public String findRoleStr();

    public SysRole findOne(Serializable id);
}
