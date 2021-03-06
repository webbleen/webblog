package com.webbleen.webblog.service;

import com.webbleen.webblog.entity.Blog;
import com.webbleen.webblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 10:54
 * @description：
 */

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable, BlogQuery query);

    Page<Blog> listBlog(Pageable pageable, String query);

    Page<Blog> listBlog(Pageable pageable, Long tagId);

    List<Blog> listRecommendBlogTop(Integer size);

    LinkedHashMap<String, List<Blog>> archiveBlog();

    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
