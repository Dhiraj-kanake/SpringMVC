package com.jda.user.service;

import com.jda.user.model.Login;
import com.jda.user.model.User;

public interface UserService {

	void register(User user);

	User validateUser(Login login);
   User findByEmail(String email);
   User findByToken(String token);
	void saveUser(User user);
	 void savePassword(User user);
}
