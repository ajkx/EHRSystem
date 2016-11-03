package com.victory.ehrsystem.service.sys.impl;

import com.victory.ehrsystem.dao.sys.SysResourceDao;
import com.victory.ehrsystem.domain.sys.SysResource;
import com.victory.ehrsystem.service.sys.ResourceService;
import com.victory.ehrsystem.util.StringUtil;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限资源数据处理层
 * 仅提供通过角色找寻权限 和BaseService里的基本增删改查
 *
 * @author ajkx_Du
 * @create 2016-11-01 9:09
 */
public class ResourceServiceImpl implements ResourceService {

    private SysResourceDao sysResourceDao;

    @Override
    public SysResource createResource(SysResource resource) {
        sysResourceDao.save(resource);
        return resource;
    }

    @Override
    public SysResource updateResource(SysResource resource) {
        sysResourceDao.update(resource);
        return resource;
    }

    @Override
    public void deleteResource(Serializable resourceId) {
        sysResourceDao.delete(SysResource.class, resourceId);
    }

    @Override
    public SysResource findOne(Serializable id) {
        return sysResourceDao.findOne(id);
    }

    @Override
    public List<SysResource> findAll() {
        return sysResourceDao.findAll(SysResource.class);
    }


    @Override
    public Set<String> findPermissions(Set<Long> resourdIds) {
        Set<String> permissions = new HashSet<>();
        for (Long resourceId : resourdIds) {
            SysResource resource = findOne(resourceId);
            if (resource != null && !StringUtil.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;

    }

    @Override
    public List<SysResource> findMenus(Set<String> permissions) {
        List<SysResource> allResources = findAll();
        List<SysResource> menus = new ArrayList<SysResource>();
        for (SysResource resource : allResources) {
            if (resource.isRootNode()) {
                continue;
            }
            if (resource.getType() != SysResource.ResourceType.menu) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, SysResource resource) {
        if (StringUtil.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
