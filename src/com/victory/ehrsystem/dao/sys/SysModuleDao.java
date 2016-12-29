package com.victory.ehrsystem.dao.sys;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.sys.SysModule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * 菜单模块数据层
 * Created by ajkx_Du on 2016/11/12.
 */
public interface SysModuleDao extends BaseDao<SysModule>{

    public SysModule findOne(Serializable id);

    public Map<String, ArrayList<String>> findModule();

}
