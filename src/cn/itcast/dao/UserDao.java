package cn.itcast.dao;

import cn.itcast.entity.User;

public interface UserDao {

	User findByUsernameAndPassword(User user);

}
