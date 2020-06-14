package com.webbleen.webblog.controller;

import com.webbleen.webblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-15 01:29
 * @description：
 */

@Controller
public class AboutShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/footer/newblogs")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newBlogList";
    }
}
