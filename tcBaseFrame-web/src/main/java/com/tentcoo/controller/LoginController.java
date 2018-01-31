package com.tentcoo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @date 2018/1/29 0029
 */
@Controller
public class LoginController {
    @Value("${logoImg}")
    private String logoImg;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("logoImg",logoImg);
        return "demo/index";
    }

    @RequestMapping("/checkLogin")
    public String login(String username, String password) {
        return "redirect:/login.html";
    }
}
