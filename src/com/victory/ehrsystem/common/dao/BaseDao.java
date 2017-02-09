package com.victory.ehrsystem.common.dao;

import com.victory.ehrsystem.vo.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
     * 找出所有实体并分页
     * @param entityClazz
     * @return
     */
    List<T> findAllByPage(Class<T> entityClazz,int pageNo,int pageSize);
    /**
     * 总数
     * @param entityClazz
     * @return
     */
    long findCount(Class<T> entityClazz);

    /**
     * 动态条件查询并分页
     * @param entityClazz
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo findByPage(Class<T> entityClazz, Map<String, String> map, int pageNo, int pageSize);


}
