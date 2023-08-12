package com.kevin.sample.config;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> handleConflict(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlowException.class)
    public ResponseEntity<String> handleFlowException(RuntimeException ex) {
        return new ResponseEntity<>("Too many requests, wait for moment to try again.", HttpStatus.TOO_MANY_REQUESTS);
    }
}
