package cn.itcast.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {
	
	//添加拜访数据
	@Override
	public void save(Visit visit) {
		this.getHibernateTemplate().save(visit);
	}
	
	//拜访列表
	@Override
	public List<Visit> findAll() {
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}

	
	
	
	
}
