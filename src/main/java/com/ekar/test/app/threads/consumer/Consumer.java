package com.ekar.test.app.threads.consumer;

import com.ekar.test.app.config.ApplicationConfig;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.ekar.test.app.service.impl.ProducerConsumerServiceImpl.counter;

@Slf4j
@AllArgsConstructor
public class Consumer implements Runnable{

    private final ApplicationConfig config;

    @SneakyThrows
    @Override
    public void run() {
        int counterCurrentValue = counter.get();
        if (counterCurrentValue<= config.getCounterMin() || counterCurrentValue>= config.getCounterMax()){
            return;
        }
        int val = counter.decrementAndGet();
        log.info("Consumer counter: {}",val);
        Thread.sleep(1000);
    }
}
