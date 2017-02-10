package com.victory.ehrsystem.service.sys;

import com.victory.ehrsystem.dao.sys.UserDao;
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
 * 用户基本操作数据处理层
 *
 * @author ajkx_Du
 * @create 2016-10-29 9:05
 */
@Service
public class UserService extends BaseService<User>{

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    public User createUser(User user) {
        passwordHelper.encryptPassword(user);
        userDao.save(user);
        return user;
    }

    public User updateUser(User user) {
        userDao.update(user);
        return user;
    }

    @Override
    public PageInfo findByPage(Class<User> entityClazz, HttpServletRequest request) {
        PageInfo info = super.findByPage(entityClazz, request);
        List<User> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (User user : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            map.put("hrmResource", user.getHrmResource());
            List<Map<String, Object>> roles = new ArrayList<>();
            for (SysRole role : user.getRoleids()) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", role.getId());
                temp.put("name", role.getName());
                roles.add(temp);
            }
            map.put("roles", roles);
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

    public User findOne(Serializable id) {
        return userDao.findOne(id);
    }

    public void delete(Serializable id) {
        userDao.delete(User.class,id);
    }

    public void changePassword(Serializable id, String newPassword) {
        User user = userDao.findOne(id);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Set<String> findRoles(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(user.getRoleids());
    }

    public Set<String> findPermissions(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleids());
    }
}
