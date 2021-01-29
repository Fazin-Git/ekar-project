package com.ekar.test.app.threads.producer;

import com.ekar.test.app.config.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.ekar.test.app.service.impl.ProducerConsumerServiceImpl.counter;

@Slf4j
@RequiredArgsConstructor
public class Producer implements Runnable{

    private final ApplicationConfig config;

    @SneakyThrows
    @Override
    public void run() {
        int counterCurrentValue = counter.get();
        if (counterCurrentValue<= config.getCounterMin() || counterCurrentValue>= config.getCounterMax()){
            return;
        }
        int val = counter.incrementAndGet();
        log.info("Producer incremented counter value : {}",val);
        Thread.sleep(1000);
    }
}
