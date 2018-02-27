package com.tentcoo.gateway.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by rover on 2018/2/27.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        JSONObject header = new JSONObject();
        if(authException instanceof BadCredentialsException){ /**身份认证未通过*/
            header.put("errorcode","9002");
            header.put("errorinfo","用户名或密码错误，请重新输入！");
            json.put("header", header);
        }else{
            header.put("errorcode","9001");
            header.put("errorinfo","无效的token");
            json.put("header", header);
        }
        response.getWriter().write(JSONObject.toJSONString(json));
    }
}