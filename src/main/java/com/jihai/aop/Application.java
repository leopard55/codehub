package com.jihai.aop;

import com.itheima.ioc.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
//    public static void main(String[] args) {
//        new SpringApplication(Application.class).run(args);
//    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) {
        SaveOrder saveOrder = new SaveOrder();
        saveOrder.setId(1L);
        orderService.saveOrder(saveOrder);

        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.setOrderId(2L);
        orderService.updateOrder(updateOrder);
    }
}
