package com.victory.ehrsystem.service;


import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象service基类，提供通用方法
 *
 * @author ajkx_Du
 * @create 2016-10-27 16:05
 */
public abstract class BaseService<T> {

    @Autowired
    protected BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    /**
     * 保存并返回保存的实体ID
     *
     * @param entity
     * @return
     */
    public Serializable save(T entity) {
        return baseDao.save(entity);
    }

    /**
     * 保存并返回保存的实体
     *
     * @param entityClazz
     * @param entity
     * @return
     */
    public T save(Class<T> entityClazz, T entity) {

        return baseDao.get(entityClazz, baseDao.save(entity));
    }

    /**
     * 更新实体并返回实体
     * @param entityClazz
     * @param entity
     * @return
     */
    public T update(Class<T> entityClazz, T entity) {
        baseDao.update(entity);
        return entity;
    }

    /**
     * 删除ID对应的实体
     * @param entityClazz
     * @param id
     */
    public void delete(Class<T> entityClazz,Serializable id) {
        baseDao.delete(entityClazz, id);
    }

    /**
     * 删除实体
     * @param entity
     */
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    /**
     * 批量删除
     * @param entityClazz
     * @param ids
     */
    public void delete(Class<T> entityClazz,Serializable[] ids) {
        for(int i = 0; i < ids.length;i++) {
            baseDao.delete(entityClazz,ids[i]);
        }
    }

    /**
     * 找寻ID实体
     * @param entityClazz
     * @param id
     * @return
     */
    public T findOne(Class<T> entityClazz, Serializable id) {
        return baseDao.get(entityClazz, id);
    }

    /**
     * 找寻所有实体
     * @param entityClazz
     * @return
     */
    public List<T> findAll(Class<T> entityClazz) {
        return baseDao.findAll(entityClazz);
    }

    public PageInfo findByPage(Class<T> entityClazz, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        int pageNo = Integer.parseInt(request.getParameter("cPage"));
        int pageSize = Integer.parseInt(request.getParameter("pSize"));
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            if(name.equals("cPage") || name.equals("pSize") || name.equals("tPage") || name.equals("tSize")){
                continue;
            }
            map.put(name, request.getParameter(name));
        }
        return baseDao.findByPage(entityClazz, map, pageNo, pageSize);
    }
    public List<T> findAllByPage(Class<T> entityClazz,int pageNo,int pageSize){
        return baseDao.findAllByPage(entityClazz, pageNo, pageSize);
    }
    /**
     * 统计实体总数
     * @param entityClazz
     * @return
     */
    public Long count(Class entityClazz) {
        return baseDao.findCount(entityClazz);
    }

}
