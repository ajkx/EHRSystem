package com.victory.ehrsystem.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ajkx_Du on 2016/10/19.
 */
public interface BaseDao<T> {

    /**
     * 根据类名和ID号获取持久化类实体
     * @param entityClazz
     * @param id
     * @return
     */
    T get(Class<T> entityClazz, Serializable id);

    /**
     * 保存新的持久化类实体
     * @param entity
     * @return
     */
    Serializable save(T entity);

    /**
     * 更新实体
     * @param entity
     */
    void update(T entity);

    /**
     * 删除实体
     * @param entity
     */
    void delete(T entity);

    /**
     * 根据类名和ID删除实体
     * @param entityClazz
     * @param id
     */
    void delete(Class<T> entityClazz, Serializable id);

    /**
     * 找出该类所有实体
     * @param entityClazz
     * @return
     */
    List<T> findAll(Class<T> entityClazz);

    /**
     * 总数
     * @param entityClazz
     * @return
     */
    long findCount(Class<T> entityClazz);


}
