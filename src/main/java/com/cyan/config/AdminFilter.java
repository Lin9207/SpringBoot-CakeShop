package com.cyan.config;

import com.cyan.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*",filterName = "adminFilter")
public class AdminFilter  implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 获取当前登录的用户
        Users user = (Users) req.getSession().getAttribute("loginUser");
        // 如果已登录 并且是 管理员则放行
        if (user != null && user.isIsadmin()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 非管理员重定向到登录页
            resp.sendRedirect(req.getContextPath()+"/adminLogin.html");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
