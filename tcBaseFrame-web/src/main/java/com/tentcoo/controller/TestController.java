package com.tentcoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rover on 2018/1/24.
 */
@Controller
@RequestMapping("/view")
public class TestController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, ModelMap model) {
        model.addAttribute("name", name);
        System.out.println("GRETTINGS, "+ name +"................!");
        return "demo/greeting";
    }

}
