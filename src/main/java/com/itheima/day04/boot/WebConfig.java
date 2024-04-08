package com.itheima.day04.boot;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class WebConfig {

    // 创建 tomcat web 服务器
    @Bean
    ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet) {
        // 匹配不到其他路径的请求，都会匹配到 /
        return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
    }

}
