package com.jda.user.dao;

import com.jda.user.model.Login;
import com.jda.user.model.User;

public interface UserDao {
	void register(User user);

	User validateUser(Login login);
	
 User findByEmail(String email);
 void saveUser(User user);
}
