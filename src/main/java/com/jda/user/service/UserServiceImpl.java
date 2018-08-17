package com.jda.user.service;

import com.jda.user.dao.UserDao;
import com.jda.user.model.Login;
import com.jda.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
	@Autowired
	public UserDao userDao;

	public void register(User user) {
		userDao.register(user);
	}

	public User validateUser(Login login) {
		return userDao.validateUser(login);
	}
}
