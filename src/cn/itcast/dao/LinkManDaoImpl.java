package cn.itcast.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {
	
	//添加联系人信息
	@Override
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	
	
	
}
