package com.victory.ehrsystem.service.sys;

import com.victory.ehrsystem.entity.sys.User;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author ajkx_Du
 * @create 2016-11-03 20:31
 */
public interface UserService {

    public User createUser(User user);

    public User updateUser(User user);

    public void delete(Serializable id);

    /**
     * 修改密码
     * @param id
     * @param newPassword
     */
    public void changePassword(Serializable id, String newPassword);

    User findOne(Serializable id);

    List<User> findAll();

    /**
     * 根据用户名寻找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名寻找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名寻找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
}
