package com.cyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 自动扫描带有(@WebServlet,@WebFilter,@WebListener)注解的类，完成注册。
public class CakeshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(CakeshopApplication.class, args);
    }
}
