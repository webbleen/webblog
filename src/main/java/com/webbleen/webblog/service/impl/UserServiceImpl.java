package com.webbleen.webblog.service.impl;

import com.webbleen.webblog.dao.UserRepository;
import com.webbleen.webblog.entity.User;
import com.webbleen.webblog.service.UserService;
import com.webbleen.webblog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 10:54
 * @description：
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
