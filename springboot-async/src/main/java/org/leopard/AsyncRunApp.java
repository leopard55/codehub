package org.leopard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncRunApp {

    public static void main(String[] args) {
        SpringApplication.run(AsyncRunApp.class, args);
    }
}
