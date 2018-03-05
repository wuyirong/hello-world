/***/
package com.tentcoo.security.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tentcoo.security.utils.UserUtil;
import com.tentcoo.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 表单验证（包含验证码）过滤类
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
    public static final String DEFAULT_MOBILE_PARAM  = "mobileLogin";
    public static final String DEFAULT_MESSAGE_PARAM = "message";

    private String captchaParam     = DEFAULT_CAPTCHA_PARAM;
    private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
    private String messageParam     = DEFAULT_MESSAGE_PARAM;

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password == null) {
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String  host       = StringUtils.getRemoteAddr((HttpServletRequest) request);
        String  captcha    = getCaptcha(request);
        boolean mobile     = isMobileLogin(request);
        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    public String getMobileLoginParam() {
        return mobileLoginParam;
    }

    protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, getMobileLoginParam());
    }

    public String getMessageParam() {
        return messageParam;
    }

    /**
     * 登录成功之后跳转URL
     */
    public String getSuccessUrl() {
        return super.getSuccessUrl();
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
//		Principal p = UserUtils.getPrincipal();
//		if (p != null && !p.isMobileLogin()){
        WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
//		}else{
//			super.issueSuccessRedirect(request, response);
//		}
    }

    /**
     * 登录失败调用事件
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        String                className = e.getClass().getName(), message = "";
        UsernamePasswordToken info      = (UsernamePasswordToken) token;
        //判断是否第一次登录失败
        Integer failureNum = (Integer) UserUtil.getCache(info.getUsername());
        if (failureNum == null) {
            UserUtil.putCache(info.getUsername(), 1);
        } else {
            UserUtil.removeCache(info.getUsername());
            UserUtil.putCache(info.getUsername(), failureNum + 1);
        }

        Map<String, Object> jsonMap = new HashMap<>();
        //定义一个json返回给页面
        jsonMap.put("success", false);
        if (IncorrectCredentialsException.class.getName().equals(className) || UnknownAccountException.class.getName().equals(className)) {
            message = "用户或密码错误, 请重试!!!";
            jsonMap.put("msg", "用户或密码错误, 请重试!!!");
        } else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "验证码错误")) {
            message = StringUtils.replace(e.getMessage(), "msg:", "");
            jsonMap.put("msg", e.getMessage());
        } else {
            message = "系统出现点问题，请稍后再试！";
            e.printStackTrace(); // 输出到控制台
            jsonMap.put("msg", "系统出现点问题，请稍后再试！");
        }
        try {
            jsonMap.put("failureNum", UserUtil.getCache(info.getUsername()));
            //把这个json发给页面上
            String json = new ObjectMapper().writeValueAsString(jsonMap);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(json);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        //返回false不执行下面的过滤器
        return false;
    }

    /**
     * 复写父类登录成功后执行的方法
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        UsernamePasswordToken info = (UsernamePasswordToken) token;
        //获取当前用户登录的用户名
        UserUtil.removeCache(info.getUsername());
        //复写父类登录成功后执行的方法,直接返回一个json
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("msg", "登录成功");
        jsonMap.put("success", true);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(new ObjectMapper().writeValueAsString(jsonMap));
        //返回false不执行下面的过滤器
        return false;
    }

    /**
     * 解决不允许重复登录问题
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //把request对象跟当前线程绑定
        // RequestUtil.threadLocal.set((HttpServletRequest) request);
        if (isLoginRequest(request, response)) {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

}