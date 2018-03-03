package com.tentcoo.gateway.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一拦截异常
 * Created by rover on 2018/3/3.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UnauthorizedException.class) // 处理访问方法时权限不足问题
    public String defaultErrorHandler(HttpServletRequest req, Exception e) {
        return "抱歉, 您无权访问该资源！---403";
    }

}
