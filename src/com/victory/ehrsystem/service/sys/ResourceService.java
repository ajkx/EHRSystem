package com.victory.ehrsystem.service.sys;

import com.victory.ehrsystem.domain.sys.SysModule;
import com.victory.ehrsystem.domain.sys.SysResource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ajkx_Du on 2016/11/3.
 */
public interface ResourceService {

    /**
     * 暂时不使用 create update delete
     * @param resource
     * @return
     */
    public SysResource createResource(SysResource resource);

    public SysResource updateResource(SysResource resource);

    public void deleteResource(Serializable resourceId);

    SysResource findOne(Serializable id);

    List<SysResource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resources
     * @return
     */
    Set<String> findPermissions(Set<SysResource> resources);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    Map<SysModule, ArrayList<SysResource>> findMenus(Set<String> permissions);
}
