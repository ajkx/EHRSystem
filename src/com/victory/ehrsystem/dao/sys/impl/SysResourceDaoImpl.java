package com.victory.ehrsystem.dao.sys.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.sys.SysResourceDao;
import com.victory.ehrsystem.domain.sys.SysResource;

import java.io.Serializable;

/**
 * 权限数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-27 15:11
 */
public class SysResourceDaoImpl extends BaseDaoImpl<SysResource> implements SysResourceDao {
    @Override
    public SysResource findOne(Serializable id) {
        return get(SysResource.class, id);
    }
}
