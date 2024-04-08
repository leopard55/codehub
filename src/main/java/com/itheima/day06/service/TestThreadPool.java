package com.itheima.day06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

import static com.itheima.day06.service.TestRestTemplate.PREFIX;

/**
 * 作用：主要目的重用线程
 *    ArrayBlockingQueue     底层数组  整个队列一把锁
 *    LinkedBlockingQueue    底层链表  队列头一把锁  队列尾一把锁   加，取任务不影响
 */
public class TestThreadPool {

    static Logger log = LoggerFactory.getLogger(TestThreadPool.class);

    public static void main(String[] args) throws InterruptedException {
        RestTemplate template = new RestTemplate();
        CountDownLatch latch = new CountDownLatch(3);

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(3,5,1000L, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<>(5), Executors.defaultThreadFactory());
        // 3个任务 -> 3个线程
        // 5 6 7 8 9 进入阻塞队列
        // 9 -> 新线程
        // 10 -> 新线程

        executorService.submit(()->{
            ResponseEntity<TestRestTemplate.Resp> resp1 =
                    template.getForEntity(PREFIX + "/user/1", TestRestTemplate.Resp.class);
            log.debug("{}", resp1.getBody());
            latch.countDown();
        });
        executorService.submit(()->{
            ResponseEntity<TestRestTemplate.Resp> resp2 =
                    template.getForEntity(PREFIX + "/order/2", TestRestTemplate.Resp.class);
            log.debug("{}", resp2.getBody());
            latch.countDown();
        });
        executorService.submit(()->{
            ResponseEntity<TestRestTemplate.Resp> resp3 =
                    template.getForEntity(PREFIX + "/logistics/5", TestRestTemplate.Resp.class);
            log.debug("{}", resp3.getBody());
            latch.countDown();
        });
        latch.await();
        log.debug("汇总");
    }
}
