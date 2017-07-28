package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.User;

@Transactional
public class UserService {
	
	//注入
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User login(User user) {
		return userDao.findByUsernameAndPassword(user);
	}
	
	//查询所有用户
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	
	
	
}
