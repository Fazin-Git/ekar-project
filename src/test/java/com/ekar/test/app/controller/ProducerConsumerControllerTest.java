package com.ekar.test.app.controller;

import com.ekar.test.app.dto.ResponseDto;
import com.ekar.test.app.dto.ThreadsCountRequestDto;
import com.ekar.test.app.dto.UpdateCounterRequestDto;
import com.ekar.test.app.service.ProducerConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProducerConsumerControllerTest {

    @InjectMocks
    ProducerConsumerController producerConsumerController;

    @Mock
    ProducerConsumerService service;

    @Test
    public void configureThreadsCountTest(){
        doNothing().when(service).counterIncrementAndDecrement(any(ThreadsCountRequestDto.class));
        ResponseDto responseDto = producerConsumerController.configureThreadsCount(any());
        assertEquals(responseDto.getStatus(),"Success");
    }

    @Test
    public void updateCounter(){
        doNothing().when(service).updateCounter(any(UpdateCounterRequestDto.class));
        ResponseDto responseDto = producerConsumerController.updateCounter(any(UpdateCounterRequestDto.class));
        assertEquals(responseDto.getStatus(),"Success");
    }
}
