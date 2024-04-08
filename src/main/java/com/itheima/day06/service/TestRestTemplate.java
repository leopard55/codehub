package com.itheima.day06.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRestTemplate {

    static String PREFIX = "http://localhost:8080";

    static Logger log = LoggerFactory.getLogger(TestRestTemplate.class);

    public static void main(String[] args) throws InterruptedException {
        RestTemplate template = new RestTemplate();
        log.debug("begin...");
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()->{
            ResponseEntity<Resp> resp1 =
                    template.getForEntity(PREFIX + "/user/1", Resp.class);
            log.debug("{}", resp1.getBody());
            latch.countDown();
        });
        executorService.submit(()->{
            ResponseEntity<Resp> resp2 =
                    template.getForEntity(PREFIX + "/order/2", Resp.class);
            log.debug("{}", resp2.getBody());
            latch.countDown();
        });
        executorService.submit(()->{
            ResponseEntity<Resp> resp3 =
                    template.getForEntity(PREFIX + "/logistics/5", Resp.class);
            log.debug("{}", resp3.getBody());
            latch.countDown();
        });
        latch.await();
        log.debug("汇总");
    }

    static class Resp {
        String data;

        public Resp() {
        }

        @JsonCreator
        public Resp(@JsonProperty("data") String arg0) { // arg0 参数名编译后丢失，无法映射
            this.data = arg0;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Resp{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }
}
