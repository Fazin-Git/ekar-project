package com.ekar.test.app.service;

import com.ekar.test.app.config.ApplicationConfig;
import com.ekar.test.app.dto.ThreadsCountRequestDto;
import com.ekar.test.app.dto.UpdateCounterRequestDto;
import com.ekar.test.app.entity.CounterDetails;
import com.ekar.test.app.exception.CounterExhaustedException;
import com.ekar.test.app.repository.CounterDetailsRepository;
import com.ekar.test.app.repository.RequestResponseLogRepository;
import com.ekar.test.app.service.impl.ProducerConsumerServiceImpl;
import com.ekar.test.app.threads.ThreadPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.ekar.test.app.service.impl.ProducerConsumerServiceImpl.counter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProducerConsumerServiceImplTest {

    @InjectMocks
    ProducerConsumerServiceImpl producerConsumerService;

    @Mock
    CounterDetailsRepository counterDetailsRepository;

    @Mock
    RequestResponseLogRepository requestResponseLogRepository;

    @Mock
    ThreadPool threadPool;

    @Mock
    ApplicationConfig config;

    @Test
    public void counterIncrementAndDecrementTest(){
        when(config.getCounterMax()).thenReturn(100);
        when(config.getCounterMin()).thenReturn(0);
        ThreadsCountRequestDto threadsCountRequestDto = ThreadsCountRequestDto.builder().consumerCount(5).producerCount(30).build();
        producerConsumerService.counterIncrementAndDecrement(threadsCountRequestDto);
        verify(counterDetailsRepository, times(1)).save(any(CounterDetails.class));
    }

    @Test
    public void counterIncrementAndDecrementExceptionTest(){
        when(config.getCounterMax()).thenReturn(100);
        when(config.getCounterMin()).thenReturn(0);
        counter.set(100);
        ThreadsCountRequestDto threadsCountRequestDto = ThreadsCountRequestDto.builder().consumerCount(5).producerCount(30).build();
        CounterExhaustedException thrown = assertThrows(CounterExhaustedException.class,
                ()->producerConsumerService.counterIncrementAndDecrement(threadsCountRequestDto),
                "expected doNothing to throw,but it didn't");
        assertEquals("ERR_CLIENT_001",thrown.getErrorCode());
    }

    @Test
    public void updateCounterTest(){
        UpdateCounterRequestDto updateCounterRequestDto = new UpdateCounterRequestDto();
        updateCounterRequestDto.setCounter(60);
        producerConsumerService.updateCounter(updateCounterRequestDto);
        assertEquals(counter.get(),updateCounterRequestDto.getCounter());
    }

}
