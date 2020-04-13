package com.cyan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    // web监控的配置处理
    // 设置后台管理页面，比如 登录账号、密码等
    @Bean
    public ServletRegistrationBean StatViewServlet(){

        // 表示进行druid监控的配置处理操作
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        // 设置初始化参数
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");    // 登录druid监控的账户
        initParams.put("loginPassword","admin123"); // 登录druid监控的密码
        initParams.put("allow","");                 // 允许所有人可以访问
        initParams.put("resetEnable","false");      // 是否可以重置数据源
        bean.setInitParameters(initParams);

        return bean;
    }

    // web 监控 filter 过滤器
    // 配置Web和Druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean WebStatFilter(){

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        // exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions","style/**,/druid/*");// '/druid/*'为Druid监控管理后台的Servlet路径
        bean.setInitParameters(initParams);

        // '/*' 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
