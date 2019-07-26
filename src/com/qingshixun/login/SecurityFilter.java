package com.qingshixun.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 安全过滤器，控制未登录情况下不允许访问资源
 */
@WebFilter("/*")
public class SecurityFilter implements Filter {

    /**
     * 过滤器销毁
     */
    public void destroy() {

    }

    /**
     * 执行安全控制
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 获取 HttpSession 对象
        HttpSession session = httpRequest.getSession();
        // 获取当前访问的 URL 地址
        StringBuffer requestURL = httpRequest.getRequestURL();

        // 排除登录页面 login.jsp 和登录处理 doLogin action 以及 images 目录
        if (requestURL.indexOf("login.jsp") > 0 || requestURL.indexOf("LoginServletKotlin") > 0
                || requestURL.indexOf("images") > 0) {
            chain.doFilter(request, response);
            return;
        }

        // 如果 Session 中有 username 值，则表明当前用户已经登录，可以直接访问，否则调转到登录页面
        if (session.getAttribute("username") != null) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    /**
     * 过滤器初始化
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}