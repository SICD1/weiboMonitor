package com.sicdlib.dao.imple;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import com.sicdlib.dao.IBaseDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("baseDAO")
@Transactional
public class BaseDAO<T> implements IBaseDAO<T> {
    @Autowired
    SessionFactory sessionFactory;

    //Session session ;


    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<T> getSqlList(String sql) {
        return this.getCurrentSession().createSQLQuery(sql).list();
    }
    @Override
    public Serializable save(T o) {
        // TODO Auto-generated method stub
        return getCurrentSession().save(o);
    }

    @Override
    public void delete(T o) {
        // TODO Auto-generated method stub
        getCurrentSession().delete(o);
    }

    @Override
    public void update(T o) {
        getCurrentSession().update(o);

    }

    @Override
    public void saveOrUpdate(T o) {
        // TODO Auto-generated method stub
        getCurrentSession().saveOrUpdate(o);

    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Class<T> c, Serializable id) {
        // TODO Auto-generated method stub
        return (T) getCurrentSession().get(c, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(String hql) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        List<T> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(String hql, Map<String, Object> params) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        List<T> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, int from, int size) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        return query.setFirstResult(from).setMaxResults(size).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, Map<String, Object> params, int from, int rows) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        if(params != null && !params.isEmpty()) {
            for(String key: params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.setFirstResult(from).setMaxResults(rows).list();
    }

    @Override
    public Long count(String hql) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        return (Long)query.uniqueResult();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return (Long) query.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        return query.executeUpdate();
    }

    @Override
    public int executeHql(String hql, Map<String, Object> params) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.executeUpdate();
    }


    @Override
    public T get(Class<T> c, String where, String orderBy, String... params) {
        // TODO Auto-generated method stub
        List<T> list = find(c, where, orderBy, params);
        if (null == list || list.size() > 1) {
            return null;
        }
        return list.get(0);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<T> find(Class<T> c, String where, String orderBy, String... params) {
        // TODO Auto-generated method stub
        if (null == params || params.length == 0) {
            return null;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        for (String param : params) {
            sql.append(param);
            sql.append(",");
        }
        if (sql.toString().endsWith(",")) {
            sql.deleteCharAt(sql.toString().length() - 1);
        }
        sql.append(" from " + c.getAnnotation(Table.class).name());
        sql.append(" where " + where);
        if (null != orderBy && !"".equals(orderBy.trim())) {
            sql.append(" ORDER BY " + orderBy);
        }
        List<T> list = getCurrentSession().createSQLQuery(sql.toString()).list();
        List<T> tlist = new ArrayList<T>();
        for (T t : list) {
            Object[] objs = (Object[]) t;
            try {
                T instance = c.newInstance();
                for (int i = 0; i < params.length; i++) {
                    String param = params[i].substring(0, 1).toUpperCase() + params[i].substring(1, params[i].length());
                    Object obj = objs[i];

                    Method[] methods = instance.getClass().getMethods();
                    for (Method method : methods) {
                        method.setAccessible(true);
                        if (method.getName().equals("set" + param)) {
                            Class[] parameterTypes = method.getParameterTypes();
                            if (parameterTypes.length != 1) {
                                return null;
                            }
                            String paramTypeName = parameterTypes[0].getSimpleName();

                            if ("String".equals(paramTypeName)) {
                                method.invoke(instance, obj == null ? null : obj.toString());
                            } else if ("int".equals(paramTypeName)) {
                                method.invoke(instance, Integer.valueOf(obj.toString()));
                            } else if ("Date".equals(paramTypeName)) {
                                method.invoke(instance, obj == null ? null : (Date) obj);
                            } else if ("double".equals(paramTypeName)) {
                                method.invoke(instance, Double.valueOf(obj.toString()));
                            } else if ("Integer".equals(paramTypeName)) {
                                method.invoke(instance, obj == null ? null : Integer.valueOf(obj.toString()));
                            }
                        }
                    }
                }
                tlist.add(instance);
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }

        return tlist;
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int executeSql(String sql) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createSQLQuery(sql);
        return query.executeUpdate();
    }

    @Override
    public BigInteger countSql(String sql, Map<String, Object> params) {
        // TODO Auto-generated method stub
        Query query = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return (BigInteger) query.uniqueResult();
    }

}
