package com.ekar.test.app.exception;

import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CounterExhaustedException.class)
    public final ResponseEntity<Object> handleAllExceptions(CounterExhaustedException ex) {
        CounterExhaustedException exceptionResponse =
                new CounterExhaustedException(
                        ex.getMessage(), ex.getErrorCode(), ex.getHttpStatus());
        return new ResponseEntity(exceptionResponse, ex.getHttpStatus());
    }
}
