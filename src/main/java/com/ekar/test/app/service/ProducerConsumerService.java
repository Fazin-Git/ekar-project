package com.ekar.test.app.service;

import com.ekar.test.app.dto.ThreadsCountRequestDto;
import com.ekar.test.app.dto.UpdateCounterRequestDto;

public interface ProducerConsumerService {
    void counterIncrementAndDecrement(ThreadsCountRequestDto threadsCountRequestDto);
    void updateCounter(UpdateCounterRequestDto updateCounterRequestDto);
}
