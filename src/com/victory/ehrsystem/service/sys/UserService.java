package com.victory.ehrsystem.service.sys;

import com.victory.ehrsystem.dao.sys.UserDao;
import com.victory.ehrsystem.entity.sys.User;
import com.victory.ehrsystem.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
