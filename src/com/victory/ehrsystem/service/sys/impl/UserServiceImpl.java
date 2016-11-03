package com.victory.ehrsystem.service.sys.impl;

import com.victory.ehrsystem.dao.sys.UserDao;
import com.victory.ehrsystem.domain.sys.User;
import com.victory.ehrsystem.service.sys.RoleService;
import com.victory.ehrsystem.service.sys.UserService;

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
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private PasswordHelper passwordHelper;
    private RoleService roleService;

    @Override
    public User createUser(User user) {
        userDao.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        userDao.update(user);
        return user;
    }

    @Override
    public void delete(Serializable id) {
        userDao.delete(User.class,id);
    }

    @Override
    public User findOne(Serializable id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll(User.class);
    }

    @Override
    public void changePassword(Serializable id, String newPassword) {
        User user = userDao.findOne(id);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        //将获取的角色集合转换为数组
        return roleService.findRoles(user.getRoleIdsStr().toArray(new Long[0]));
    }

    @Override
    public Set<String> findPermissions(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleIdsStr().toArray(new Long[0]));
    }
}
