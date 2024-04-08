package com.itheima.day04.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11Nio2Protocol;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

public class TestTomcat {

    static class MyServlet extends HttpServlet {

        // 请求对象，相应对象
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("你好, servlet");
        }
    }

    public static void main(String[] args) throws IOException, LifecycleException {
        Tomcat tomcat = new Tomcat(); // tomcat服务器
        tomcat.setBaseDir("tomcat"); // 设置主目录

        File tmp = Files.createTempDirectory("abc").toFile();
        tmp.deleteOnExit(); // 临时目录在程序退出时删除

        // 给tomcat服务器添加一个应用程序
        Context context = tomcat.addContext("", tmp.getAbsolutePath());
        context.addServletContainerInitializer(new ServletContainerInitializer() {
            @Override
            public void onStartup(Set<Class<?>> set, ServletContext ctx) throws ServletException {
                // 通过ServletContext添加servlet，过滤器
                ctx.addServlet("myServlet", new MyServlet())
                        .addMapping("/abc"); // 给servlet添加映射路径

            }
        }, null); // 添加初始化器

        tomcat.start(); //启动服务器

        // 连接器，设置协议，设置端口
        Connector connector = new Connector(new Http11Nio2Protocol());
        connector.setPort(8080);

        // 结合tomcat和连接器
        tomcat.setConnector(connector);

    }
}
