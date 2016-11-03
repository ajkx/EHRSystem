package com.victory.ehrsystem.dao.sys;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.domain.sys.SysRole;
import com.victory.ehrsystem.domain.sys.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ajkx_Du on 2016/10/27.
 */
public interface SysRoleDao extends BaseDao<SysRole>{
    public List<SysRole> findByUser(User user);

    public String findRoleStr();

    public SysRole findOne(Serializable id);
}
