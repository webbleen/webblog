package com.webbleen.webblog.service;

import com.webbleen.webblog.entity.Blog;
import com.webbleen.webblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 10:54
 * @description：
 */

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery query);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
