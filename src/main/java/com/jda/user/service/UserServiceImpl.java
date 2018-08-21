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
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	public User findByToken(String token) {
		return userDao.findByToken(token);
	}
	public void saveUser(User user) {
		 userDao.saveUser(user);
	}
	public void savePassword(User user) {
		 userDao.savePassword(user);
	}
}
