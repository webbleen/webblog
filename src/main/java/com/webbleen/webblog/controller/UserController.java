package com.webbleen.webblog.controller;

import com.webbleen.webblog.dao.UserMapper;
import com.webbleen.webblog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    private static final String SLASH_SYMBOL = "/";


    @RequestMapping("getUser")
    public String getUser(String username) {
        User user = userMapper.findUserByUsername(username);
        return user != null ? username + "的ID是：" + user.getId() : "不存在用户名为" + username + "的用户";
    }
}
