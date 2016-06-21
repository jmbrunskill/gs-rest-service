package com.test.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.user.dao.UserDao;
import com.test.user.dao.model.User;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    
    private UserDao userDao;

    @Autowired
    public GreetingController (UserDao userDao) {
        this.userDao = userDao;
    }
    
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="username", defaultValue="World") String username) {
	User user = userDao.findByUsername(username);
        return String.format(template, user.getName());
    }
}
