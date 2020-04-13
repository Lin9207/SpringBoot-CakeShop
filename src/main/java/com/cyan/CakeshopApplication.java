package com.cyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 使用@ServletComponentScan，自动扫描带有(@WebServlet, @WebFilter, and @WebListener)注解的类，完成注册。
public class CakeshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeshopApplication.class, args);
    }

}
