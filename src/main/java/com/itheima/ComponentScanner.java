package com.itheima;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * 职责：找到某个 package 下所有加了 @Component 注解的类，把它们加入容器管理
 */
public class ComponentScanner {

    /**
     * 扫描加了 @Component 注解的类，注册到 BeanFactory
     * @param beanFactory 容器
     * @param packageName 包名
     * @throws Exception
     */
    public static void scan(BeanFactory beanFactory, String packageName) throws Exception {
        List<File> files = getClassFiles(packageName);
        String p = packageName.replace(".", "\\");
        for (File f : files) {
//            System.out.println(f);
            // D:\黄埔班\mini-spring\target\classes\com\test\A.class => com.test.A
            String t = f.getAbsolutePath();
            t = t.substring(0, t.lastIndexOf(".class"));
            t = t.substring(t.lastIndexOf(p));
            t = t.replace("\\", ".");
//            System.out.println(t);
            Class<?> clz = Class.forName(t);
            if (clz.isAnnotationPresent(Component.class)) {
                beanFactory.register(clz);
            }
        }
    }

    /**
     * 根据包名，得到所有类文件
     * @param packageName 包名
     * @return 文件集合
     */
    private static List<File> getClassFiles(String packageName) throws URISyntaxException {
        packageName = packageName.replace(".", "/");
        URL resource = Thread.currentThread().getContextClassLoader().getResource(packageName);
        Path start =Path.of(resource.toURI());;
        try (Stream<Path> walk = Files.walk(start)) {
            List<File> list = walk.filter(p -> p.toString().endsWith(".class"))
                    .map(p -> p.toFile())
                    .toList();
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
