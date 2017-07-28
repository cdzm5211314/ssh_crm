package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.User;

public interface UserDao {

	User findByUsernameAndPassword(User user);

	List<User> findAll();

}
