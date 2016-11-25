package com.victory.ehrsystem.dao.sys.impl;

import com.victory.ehrsystem.common.dao.impl.BaseDaoImpl;
import com.victory.ehrsystem.dao.sys.UserDao;
import com.victory.ehrsystem.domain.sys.User;

import java.io.Serializable;
import java.util.List;

/**
 * 操作员数据操作层
 *
 * @author ajkx_Du
 * @create 2016-10-27 15:10
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User findByUsername(String username) {
        List<User> list = find("select u from User u where u.name = ?0", username);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public User findOne(Serializable id) {
        return get(User.class, id);
    }
}
