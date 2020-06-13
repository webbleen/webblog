package com.webbleen.webblog.dao;

import com.webbleen.webblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 11:04
 * @description：
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
}
