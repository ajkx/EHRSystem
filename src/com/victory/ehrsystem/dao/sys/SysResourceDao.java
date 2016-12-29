package com.victory.ehrsystem.dao.sys;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.sys.SysResource;

import java.io.Serializable;

/**
 * Created by ajkx_Du on 2016/10/27.
 */
public interface SysResourceDao extends BaseDao<SysResource>{
    public SysResource findOne(Serializable id);
}
