package com.webbleen.webblog.dao;

import com.webbleen.webblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User findUserByUsername(String username);

    void updateUserByUsername(User user);

    void deleteUserByUsername(String username);

//    void saveUser(User user);

    List<User> getUserList();
}
