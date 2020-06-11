package com.webbleen.webblog.service;

import com.webbleen.webblog.entity.User;

public interface UserService {

    User checkUser(String username, String password);
}
