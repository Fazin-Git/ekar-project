package com.ekar.test.app.threads;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

@Getter
@Builder
@Slf4j
public class ThreadPool {

    private final ExecutorService producers;
    private final ExecutorService consumers;

    public void terminateProducerAndConsumers(){
        producers.shutdown();
        consumers.shutdown();
        log.info("Terminated producers and consumers");
    }
}
