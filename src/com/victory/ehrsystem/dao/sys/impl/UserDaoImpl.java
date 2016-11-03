package com.victory.ehrsystem.dao.sys.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.sys.UserDao;
import com.victory.ehrsystem.domain.sys.User;

import java.io.Serializable;

/**
 * 操作员数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-27 15:10
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User findByUsername(String username) {
        return findByName(User.class, username).get(0);
    }

    @Override
    public User findOne(Serializable id) {
        return get(User.class, id);
    }
}
