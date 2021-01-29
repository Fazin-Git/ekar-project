package com.ekar.test.app.service.impl;

import com.ekar.test.app.config.ApplicationConfig;
import com.ekar.test.app.dto.ThreadsCountRequestDto;
import com.ekar.test.app.dto.UpdateCounterRequestDto;
import com.ekar.test.app.entity.CounterDetails;
import com.ekar.test.app.entity.RequestResponseLog;
import com.ekar.test.app.repository.CounterDetailsRepository;
import com.ekar.test.app.repository.RequestResponseLogRepository;
import com.ekar.test.app.service.ProducerConsumerService;
import com.ekar.test.app.threads.ThreadPool;
import com.ekar.test.app.threads.consumer.Consumer;
import com.ekar.test.app.threads.producer.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerConsumerServiceImpl implements ProducerConsumerService {

    private final ApplicationConfig config;

    public static final AtomicInteger counter = new AtomicInteger(50);

    private ThreadPool threadPool;

    private final CounterDetailsRepository counterDetailsRepository;

    private final RequestResponseLogRepository requestResponseLogRepository;

    @Override
    public void counterIncrementAndDecrement(ThreadsCountRequestDto countsRequest) {
        saveRequestResponseInDB(countsRequest);
        //Throw Error if counter is already exhausted
        if (validateCounter()){
            throw new RuntimeException("Counter value maximum");
        }
        //Creating fixed size thread pool from the request
        threadPool = ThreadPool.builder()
                .producers(Executors.newFixedThreadPool(countsRequest.getProducerCount()))
                .consumers(Executors.newFixedThreadPool(countsRequest.getConsumerCount()))
                .build();

        while (true) {
                //Infinite loop for worker threads
                if (validateCounter()){
                    //Terminate the worker threads if current count reaches 0 or 100
                    threadPool.terminateProducerAndConsumers();
                    saveCounterUpdatedTimeStampInDb();
                    break;
                }
                //Trigger producer and consumer
                threadPool.getProducers().execute(new Producer(config));
                threadPool.getConsumers().execute(new Consumer(config));
            }
    }

    private void saveRequestResponseInDB(ThreadsCountRequestDto countsRequest) {
        requestResponseLogRepository.save(RequestResponseLog
                .builder()
                .consumerThreadCount(countsRequest.getConsumerCount())
                .producerThreadCount(countsRequest.getProducerCount())
                .dateTime(LocalDateTime.now()).build());
    }

    private boolean validateCounter() {
        int counterCurrentValue = counter.get();
        return counterCurrentValue<= config.getCounterMin() || counterCurrentValue>= config.getCounterMax();
    }

    private void saveCounterUpdatedTimeStampInDb() {
        counterDetailsRepository.save(CounterDetails.builder()
                .updatedOn(LocalDateTime.now())
                .build());
    }

    @Override
    public void updateCounter(UpdateCounterRequestDto updateCounterRequestDto) {
        counter.set(updateCounterRequestDto.getCounter());
        log.info("Counter updated with value {}",counter.get());
    }
}
