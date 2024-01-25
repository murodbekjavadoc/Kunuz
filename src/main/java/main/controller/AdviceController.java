package main.controller;

import main.exp.AppBadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(AppBadException.class)
    private ResponseEntity<?> handle (AppBadException e ){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> handle (RuntimeException e ){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
