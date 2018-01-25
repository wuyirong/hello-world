package com.tentcoo.utils.xss;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤
 * Created by rover on 2018/1/25.
 */
@WebFilter(filterName="xssFilter", urlPatterns={"/*"})
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        //
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpRequestWrapper xssRequest = new XssHttpRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
        //
    }

}
