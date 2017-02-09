package com.victory.ehrsystem.service.sys;

import com.victory.ehrsystem.dao.sys.SysModuleDao;
import com.victory.ehrsystem.dao.sys.SysResourceDao;
import com.victory.ehrsystem.entity.sys.SysModule;
import com.victory.ehrsystem.entity.sys.SysResource;
import com.victory.ehrsystem.util.StringUtil;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * 权限资源数据处理层
 * 仅提供通过角色找寻权限 和BaseService里的基本增删改查
 *
 * @author ajkx_Du
 * @create 2016-11-01 9:09
 */
@Service
public class ResourceService {
    @Autowired
    private SysResourceDao sysResourceDao;

    @Autowired
    private SysModuleDao sysModuleDao;

    
    public SysResource createResource(SysResource resource) {
        sysResourceDao.save(resource);
        return resource;
    }

    
    public SysResource updateResource(SysResource resource) {
        sysResourceDao.update(resource);
        return resource;
    }

    public void deleteResource(Serializable resourceId) {
        sysResourceDao.delete(SysResource.class, resourceId);
    }

    public SysResource findOne(Serializable id) {
        return sysResourceDao.findOne(id);
    }

    public List<SysResource> findAll() {
        return sysResourceDao.findAll(SysResource.class);
    }


    public Set<String> findPermissions(Set<SysResource> resources) {
        Set<String> permissions = new HashSet<>();
        for (SysResource resource : resources) {
            if (resource != null && !StringUtil.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    public Map<SysModule, ArrayList<SysResource>> findMenus(Set<String> permissions) {
        List<SysResource> allResources = findAll();

        LinkedHashMap<SysModule,ArrayList<SysResource>> menus = new LinkedHashMap<SysModule,ArrayList<SysResource>>();

        List<SysResource> resources = new ArrayList<SysResource>();
        List<SysModule> modulename = new ArrayList<SysModule>();

        //获取该用户有为menu的资源
        for (SysResource resource : allResources) {
            //if (resource.isRootNode()) {
            //    continue;
            //}
            if (resource.getParent_id() != null) {
                continue;
            }
            if (resource.getType() != SysResource.ResourceType.menu) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            if(!modulename.contains(resource.getModule()) && resource.getModule() != null){
                modulename.add(resource.getModule());
            }
            resources.add(resource);
        }

        for (SysModule module : modulename) {
            ArrayList<SysResource> list = new ArrayList<SysResource>();
            for (SysResource resource : resources) {
                if (resource.getModule().getId() == module.getId()) {
                    list.add(resource);
                }
            }
            menus.put(module, list);
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
