package com.tentcoo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tentcoo.data.api.SystemMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
    private String            logoImg;
    @Reference
    private SystemMenuService systemMenuService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("logoImg", logoImg);
        model.addAttribute("menu",systemMenuService.getRootMenu());
        Subject subject = SecurityUtils.getSubject();
        return "demo/index";
    }
    @RequestMapping("/checkLogin")
    public String login(String username, String password) {
        return "redirect:/login.html";
    }
}
