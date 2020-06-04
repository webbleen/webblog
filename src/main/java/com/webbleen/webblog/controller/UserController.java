package com.webbleen.webblog.controller;

import com.webbleen.webblog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final String SLASH_SYMBOL = "/";


    @RequestMapping("users")
    public String users(Model model) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User(i, "webb"+i, Integer.toString(20+i), "Tykyo, Japan"));
        }
        model.addAttribute("users", userList);
        return "users";
    }
}
