package cn.itcast.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.User;

@SuppressWarnings("all")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	// 注入HibernateTemplate
	// private HibernateTemplate hibernateTemplate;
	// public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	// this.hibernateTemplate = hibernateTemplate;
	// }

	@Override
	public User findByUsernameAndPassword(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where username = ? and password = ?",
				user.getUsername(), user.getPassword());

		return list.isEmpty() ? null : list.get(0);
	}

	//查询所有用户
	@Override
	public List<User> findAll() {
		return (List<User>) this.getHibernateTemplate().find("from User");
	}

}
