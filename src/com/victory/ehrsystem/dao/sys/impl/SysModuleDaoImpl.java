package com.victory.ehrsystem.dao.sys.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.sys.SysModuleDao;
import com.victory.ehrsystem.domain.sys.SysModule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author ajkx_Du
 * @create 2016-11-12 9:39
 */
public class SysModuleDaoImpl extends BaseDaoImpl<SysModule> implements SysModuleDao{

    @Override
    public SysModule findOne(Serializable id) {
        return get(SysModule.class, id);
    }

    @Override
    public Map<String, ArrayList<String>> findModule() {
        return null;
    }

}
