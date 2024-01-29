package main.controller;

import io.jsonwebtoken.JwtException;
import main.exp.AppBadException;
import main.exp.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(AppBadException.class)
    private ResponseEntity<?> handle (AppBadException e ){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler
    private ResponseEntity<?> handle(ForbiddenException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(JwtException.class)
    private ResponseEntity<?> handle(JwtException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> handle (RuntimeException e ){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
