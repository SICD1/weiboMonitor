package com.sicdlib.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Yh on 2016/9/8.
 */
public interface IBaseDAO<T> {

    /**
     * 获取session
     * @return
     */
    Session getCurrentSession();
    /**
     * 执行sql查找
     * @param sql
     * @return
     */
    List<T> getSqlList(String sql);

    /**
     * 插入对象
     * @param o
     * @return 插入的主键
     */
    Serializable save(T o);

    /**
     * 删除数据对象
     * @param o
     */
    void delete(T o);

    /**
     * 更新数据对象
     * @param o
     */
    void update(T o);

    /**
     * 若存在则更新对象，不存在则插入对象
     * @param o
     */
    void saveOrUpdate(T o);

    /**
     * 根据主键 id 获取特定对象
     * @param c
     * @param id
     * @return
     */
    T get(Class<T> c, Serializable id);

    /**
     * 根据HQL查询
     * @param hql
     * @return
     */
    T get(String hql);

    /**
     * 根据HQL和参数查询
     * @param hql
     * @param params
     * @return
     */
    T get(String hql, Map<String, Object> params);

    /**
     * 查询，获取List
     * @param hql
     * @return
     */
    List<T> find(String hql);

    /**
     * 查询，获取List
     * @param hql
     * @param params
     * @return
     */
    List<T> find(String hql, Map<String, Object> params);

    /**
     * 查询从第from开始，总共rows条记录
     * @param hql
     * @param from
     * @param rows
     * @return
     */
    List<T> find(String hql, int from, int rows);

    /**
     * 查询从第from开始，总共rows条记录， 带参数
     * @param hql
     * @param params
     * @param from
     * @param rows
     * @return
     */
    List<T> find(String hql, Map<String, Object> params, int from,
                 int rows);

    /**
     * 返回查询的记录数
     * @param hql
     * @return
     */
    Long count(String hql);

    /**
     * 返回查询的记录数，带参数
     * @param hql
     * @param params
     * @return
     */
    Long count(String hql, Map<String, Object> params);

    /**
     * 执行更新语句
     * @param hql
     * @return
     */
    int executeHql(String hql);

    /**
     * 执行更新语句，带参数
     * @param hql
     * @param params
     * @return
     */
    int executeHql(String hql, Map<String, Object> params);

    /**
     * 对对象c进行条件查询
     * @param c
     * @param where
     * @param orderBy
     * @param params
     * @return
     */
    T get(Class<T> c, String where, String orderBy, String... params);

    /**
     * 对对象进行条件查询，返回list
     * @param c
     * @param where
     * @param orderBy
     * @param params
     * @return
     */
    List<T> find(Class<T> c, String where, String orderBy,
                 String... params);

    /**
     * 执行SQL， 带参数
     * @param sql
     * @param params
     * @return
     */
    int executeSql(String sql, Map<String, Object> params);

    /**
     * 执行SQL，不带参数
     * @param sql
     * @return
     */
    int executeSql(String sql);

    /**
     * 返回执行SQL的记录数
     * @param sql
     * @param params
     * @return
     */
    BigInteger countSql(String sql, Map<String, Object> params);
}
