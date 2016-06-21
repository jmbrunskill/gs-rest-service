package com.test.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.user.dao.UserDao;
import com.test.user.dao.model.User;

@RestController
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
	this.userDao = userDao;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam("username")String username, @RequestParam("password")String password) throws Exception {
	User user = userDao.findByUsername(username);
	
	if (user == null) {
	    throw new Exception("User not found");
	}
	
	if (!user.getPassword().equals(password)) {
	    throw new Exception("Password not correct");
	}
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 409
    @ExceptionHandler(Exception.class)
    public String errorHandler(HttpServletRequest req, Exception exception) {
	return exception.getMessage();
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 409
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void dbErrorHandler() {
	
    }
}
