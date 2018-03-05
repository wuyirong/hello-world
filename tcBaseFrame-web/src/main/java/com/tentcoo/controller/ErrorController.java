package com.tentcoo.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Administrator
 * @date 2018/2/11 0011
 */
@ControllerAdvice
public class ErrorController {

    /**
     * 统一处理未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        e.printStackTrace();
        System.out.println("其他错误");
        return "redirect:/error.html";
    }

    /**
     * 无权限访问抛出异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String handle(Exception e){
        e.printStackTrace();
        System.out.println("没有权限跳到该页面");
        return "redirect:/nopermission.html";
    }


}
