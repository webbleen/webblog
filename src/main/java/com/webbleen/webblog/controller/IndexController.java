package com.webbleen.webblog.controller;

import com.webbleen.webblog.NotFoundException;
import com.webbleen.webblog.service.BlogService;
import com.webbleen.webblog.service.TagService;
import com.webbleen.webblog.service.TypeService;
import com.webbleen.webblog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 10:54
 * @description：
 */

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {

        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));//TODO:
        model.addAttribute("tags", tagService.listTagTop(10));//TODO:
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));//TODO:
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
