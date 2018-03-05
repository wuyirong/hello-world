package com.tentcoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * icon的Controller
 * @author Administrator
 * @date 2018/2/6 0006
 */
@Controller
public class IconController {

    /**
     * 返回layui的所有图标
     * @return
     */
    //@RequiresRoles("test")
    @RequestMapping("/getIcon")
    public String getIcon(){
        return "demo/icon/iconList";
    }
}
