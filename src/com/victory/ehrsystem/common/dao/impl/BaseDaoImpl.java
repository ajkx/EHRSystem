package com.victory.ehrsystem.common.dao.impl;

import com.victory.ehrsystem.common.dao.BaseDao;
import com.victory.ehrsystem.vo.PageInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 实现DAO组件的基类的实现类
 *
 * @author ajkx_Du
 * @create 2016-10-19 16:25
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    //依赖注入sessionFactory
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T get(Class entityClazz, Serializable id) {
        return (T) getSessionFactory().getCurrentSession().get(entityClazz, id);
    }

    @Override
    public Serializable save(T entity) {
        return getSessionFactory().getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    @Override
    public void delete(Class entityClazz, Serializable id) {
        getSessionFactory().getCurrentSession()
                .createQuery("delete " + entityClazz.getSimpleName() + " en where en.id = ?1")
                .setParameter("1", id)
                .executeUpdate();
    }

    @Override
    public List findAll(Class entityClazz) {
        return find("select en from "+entityClazz.getSimpleName()+" en");
    }

    @Override
    public List<T> findAllByPage(Class<T> entityClazz, int pageNo, int pageSize) {
        return getSessionFactory().getCurrentSession()
                .createQuery("from "+entityClazz.getSimpleName())
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageNo * pageSize)
                .list();
    }

    @Override
    public long findCount(Class entityClazz) {
        List<?> l = find("select count(*) from " + entityClazz.getSimpleName());
        if (l != null && l.size() == 1) {
            return (Long)l.get(0);
        }
        return 0;
    }

    /**
     * 自定义find方法
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql) {
        List<T> list = null;
        try {
            list = getSessionFactory().getCurrentSession().createQuery(hql).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String hql, Object... params) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + "", params[i]);
        }
        return (List<T>) query.list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql, int pageNo, int pageSize) {
        return getSessionFactory().getCurrentSession()
                .createQuery(hql)
                .setFirstResult((pageSize - 1) * pageNo)
                .setMaxResults(pageSize)
                .list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByPage(String hql, int pageNo, int pageSize, Object... params) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + "", params[0]);
        }
        return query.setFirstResult((pageSize - 1) * pageNo)
                .setMaxResults(pageSize).list();
    }

    /**
     * 通过名称查询
     * @param entityClazz
     * @param name
     * @return
     */
    protected List<T> findByName(Class entityClazz, String name) {
        return find("select en from " + entityClazz.getSimpleName() + " en where en.name = ?0", name);
    }

    /**
     * 动态拼接HQL查询条件
     *
     * @param entityClazz
     * @param values
     * @return
     */
    protected List<T> findByDynamiccondition(Class entityClazz, Map<String, Object> values) {
        StringBuilder queryHql = new StringBuilder();

        queryHql.append("from " + entityClazz.getSimpleName());

        //是否具有where语句，不使用where 1 = 1，影响数据库的索引引用
        boolean hasWhere = false;

        for (Entry<String, Object> entry : values.entrySet()) {
            if (!entry.getValue().equals("")) {
                if (!hasWhere) {
                    hasWhere = searchHelper(queryHql, hasWhere);
                    queryHql.append(entry.getKey() + "=:" + entry.getKey());
                }
            }
        }

        Query query = getSessionFactory().getCurrentSession().createQuery(queryHql.toString());
        for (Entry<String, Object> entry : values.entrySet()) {
            if (!entry.getValue().equals("")) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return query.list();
    }

    /**
     * 判断HQL是否已具有 where 子句，没有则加上，有则加上 and 子句
     *
     * @param queryHql
     * @param haswhere
     * @return
     */
    private boolean searchHelper(StringBuilder queryHql, boolean haswhere) {
        if (!haswhere) {
            queryHql.append(" where ");
        } else {
            queryHql.append(" and ");
        }
        return true;
    }


    @Override
    public PageInfo findByPage(Class<T> entityClazz, Map<String,String> map, int pageNo, int pageSize){
        return getCriteria(entityClazz, map,pageNo,pageSize);
    }
    public PageInfo getCriteria(Class entityClazz,Map<String,String> map,int pageNo,int pageSize){
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClazz);
        Criteria criteria1 = session.createCriteria(entityClazz);

        for (String key : map.keySet()) {

            String prop = "";
            try {
                prop = entityClazz.getDeclaredField(key).getGenericType().getTypeName();
            }catch (Exception e){
                e.printStackTrace();
            }
            if(prop.equals("java.lang.String")){
                criteria.add(Restrictions.ilike(key,map.get(key), MatchMode.ANYWHERE));
                criteria1.add(Restrictions.ilike(key,map.get(key), MatchMode.ANYWHERE));
            }
            else if(prop.equals("java.lang.Integer")){
                criteria.add(Restrictions.eq(key,Integer.parseInt(map.get(key))));
                criteria1.add(Restrictions.eq(key,Integer.parseInt(map.get(key))));
            }
        }
        criteria.setFirstResult((pageNo - 1) * pageSize);
        criteria.setMaxResults(pageNo * pageSize);
        //获取分页前查询的总数
        Long totals = (Long) criteria1.setProjection(Projections.rowCount()).uniqueResult();
        return new PageInfo(totals,criteria.list());
    }
}
