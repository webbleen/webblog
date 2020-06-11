package com.webbleen.webblog.dao;

import com.webbleen.webblog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {

    ///User findByUsernameAndAndPassword(String username, String password);
}
