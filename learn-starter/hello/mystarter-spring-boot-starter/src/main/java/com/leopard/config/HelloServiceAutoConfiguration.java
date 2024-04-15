package com.leopard.config;

import com.leopard.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/7/1 11:03
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    private HelloProperties helloProperties;

    public HelloServiceAutoConfiguration(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService() {
        return new HelloService(helloProperties.getName(),helloProperties.getAddress());
    }
}
