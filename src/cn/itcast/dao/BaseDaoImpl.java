package cn.itcast.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class tClass;
	
	public BaseDaoImpl() {
		Class clazz = this.getClass();
		Type sType = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) sType;
		Type[] types = pType.getActualTypeArguments();
		Class pClass = (Class) types[0];
		this.tClass = pClass;
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T findById(int id) {
		return (T) this.getHibernateTemplate().get(tClass, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+tClass.getSimpleName());
	}

}
