package com.ekar.test.app.controller;

import com.ekar.test.app.dto.ResponseDto;
import com.ekar.test.app.dto.ThreadsCountRequestDto;
import com.ekar.test.app.dto.UpdateCounterRequestDto;
import com.ekar.test.app.service.ProducerConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api("API for configuring the threads count")
@RequiredArgsConstructor
@Validated
public class ProducerConsumerController {

    private final ProducerConsumerService producerConsumerService;

    @PostMapping("/threads-count")
    @ApiOperation(value = "Configure producer and consumer threads", response = ResponseDto.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid request")})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto configureThreadsCount(@Valid @RequestBody ThreadsCountRequestDto requestDto){
        producerConsumerService.counterIncrementAndDecrement(requestDto);
        return ResponseDto.builder().status("Success").build();
    }


    @PutMapping("/set-counter")
    @ApiOperation(value = "Update the counter value", response = ResponseDto.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid request")})
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto updateCounter(@Valid @RequestBody final UpdateCounterRequestDto requestDto) {
        producerConsumerService.updateCounter(requestDto);
        return ResponseDto.builder().status("Success").build();
    }
}
