package com.victory.ehrsystem.service.sys;

import com.victory.ehrsystem.dao.sys.SysRoleDao;
import com.victory.ehrsystem.entity.sys.SysResource;
import com.victory.ehrsystem.entity.sys.SysRole;
import com.victory.ehrsystem.entity.sys.User;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * 角色数据处理层
 *
 * @author ajkx_Du
 * @create 2016-11-01 17:18
 */
@Service
public class RoleService extends BaseService<SysRole>{
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private ResourceService resourceService;

    public SysRole createRole(SysRole role) {
        sysRoleDao.save(role);
        return role;
    }

    public SysRole updateRole(SysRole role) {
        sysRoleDao.update(role);
        return role;
    }

    @Override
    public PageInfo findByPage(Class<SysRole> entityClazz, HttpServletRequest request) {
        PageInfo info = super.findByPage(entityClazz, request);
        List<SysRole> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (SysRole role : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId());
            map.put("name", role.getName());
            map.put("description", StringUtil.nullString(role.getDescription()));
            List<Map<String, Object>> users = new ArrayList<>();
            for (User user : role.getUsers()) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", user.getId());
                temp.put("name", user.getName());
                users.add(temp);
            }
            map.put("users", users);
            List<Map<String, Object>> resources = new ArrayList<>();
            for (SysResource resource : role.getResources()) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", resource.getId());
                temp.put("name", resource.getName());
                resources.add(temp);
            }
            map.put("resources", resources);
            mapList.add(map);
        }
        info.setData(mapList);
        return info;

    }

    public void deleteRole(Serializable id) {
        sysRoleDao.delete(SysRole.class,id);
    }

    public SysRole findOne(Serializable id) {
        return sysRoleDao.findOne(id);
    }

    public List<SysRole> findAll() {
        return sysRoleDao.findAll(SysRole.class);
    }

    //@Override
    //public Set<String> findRoles(Serializable[] roleIds) {
    //    Set<String> roles = new HashSet<String>();
    //    for (Serializable roleId : roleIds) {
    //        SysRole role = findOne(roleId);
    //        if (role != null) {
    //            roles.add(role.getName());
    //        }
    //    }
    //    return roles;
    //}

    public Set<String> findRoles(Set<SysRole> roles) {
        Set<String> role_str = new HashSet<String>();
        for (SysRole role : roles) {
            role_str.add(role.getName());
        }
        return role_str;
    }

    //@Override
    //public Set<String> findPermissions(Serializable[] roleIds) {
    //    Set<Long> resourceIds = new HashSet<Long>();
    //    for (Serializable roleId : roleIds) {
    //        SysRole role = findOne(roleId);
    //        if (role != null) {
    //            resourceIds.addAll(role.getResourcesStr());
    //        }
    //    }
    //    return resourceService.findPermissions(resourceIds);
    //}
    public Set<String> findPermissions(Set<SysRole> roles) {
        Set<SysResource> resourceIds = new HashSet<SysResource>();
        for (SysRole role : roles) {
            resourceIds.addAll(role.getResources());
        }
        return resourceService.findPermissions(resourceIds);
    }
}
