package com.cyan.config;

import com.cyan.pojo.Type;
import com.cyan.service.inteface.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener //@WebListener注解，声明此类是一个监听器。
public class MySessionListener implements ServletContextListener {

    @Autowired
    private TypeService typeService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /*查询type分类表*/
        List<Type> list = typeService.selectAll();
        /*将查询结果添加到session域中*/
        sce.getServletContext().setAttribute("typeList",list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
