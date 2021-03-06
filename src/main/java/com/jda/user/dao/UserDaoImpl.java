package com.jda.user.dao;

import com.jda.user.model.Login;
import com.jda.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	@Transactional
	public void register(User user) {
		String sql = "insert into dhiraj values(?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, user.getUsername(), generator(user.getPassword()), user.getFirstname(),
				user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone(),user.getToken());
	}
	public String generator(String password)
	{
		BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
		String hashedPassword = pwdEncoder.encode(password);
		return hashedPassword;
	}
	
	@Override
	@Transactional
	public User validateUser(Login login) {
		String sql = "select * from dhiraj where username='" + login.getUsername()
				+ "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}
	
	@Override
	@Transactional
	public User findByEmail(String email) {
		String sql = "select * from dhiraj where email='" + email + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}
	@Override
	public void saveUser(User user) {
		String sql="update dhiraj set token='"+user.getToken()+"'where email='"+user.getEmail()+"'";
		jdbcTemplate.update(sql);
				
	}
	@Override
	@Transactional
	public void savePassword(User user) {
		String sql="update dhiraj set password='"+generator(user.getPassword())+"'where email='"+user.getEmail()+"'";
		//String sql="update dhiraj set password=12345 where email='"+user.getEmail()+"'";
		jdbcTemplate.update(sql);
				
	}
	@Override
	@Transactional
	public User findByToken(String token) {
		String sql = "select * from dhiraj where token='" + token + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

}
class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setPhone(rs.getString("phone"));
		user.setToken(rs.getString("token"));
		return user;
	}
	
	
}
