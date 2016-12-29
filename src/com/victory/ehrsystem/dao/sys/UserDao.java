package com.victory.ehrsystem.dao.sys;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.entity.sys.User;

import java.io.Serializable;

/**
 * Created by ajkx_Du on 2016/10/27.
 */
public interface UserDao extends BaseDao<User>{

    public User findByUsername(String username);

    public User findOne(Serializable id);
}
