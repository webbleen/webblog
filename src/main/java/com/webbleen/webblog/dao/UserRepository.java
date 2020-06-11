package com.webbleen.webblog.dao;

import com.webbleen.webblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndAndPassword(String username, String password);
}
