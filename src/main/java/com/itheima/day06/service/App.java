package com.itheima.day06.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RestController
    static class MyController {
        @GetMapping("/user/{id}")
        public Map<String, String> user(@PathVariable int id) throws InterruptedException {
            Thread.sleep(2000);
            return Map.of("data", id+"号用户信息");
        }

        @GetMapping("/order/{id}")
        public Map<String, String> order(@PathVariable int id) throws InterruptedException {
            Thread.sleep(1000);
            return Map.of("data", id+"号订单信息");
        }

        @GetMapping("/logistics/{id}")
        public Map<String, String> logistics(@PathVariable int id) throws InterruptedException {
            Thread.sleep(3000);
            return Map.of("data", id+"号物流信息");
        }
    }

}
