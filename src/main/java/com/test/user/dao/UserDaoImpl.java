package com.test.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test.user.dao.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public User findByUsername(String username) {
	return jdbcTemplate.queryForObject("SELECT * FROM user WHERE username = ?", new Object[] {username}, BeanPropertyRowMapper.newInstance(User.class));
    }

}
