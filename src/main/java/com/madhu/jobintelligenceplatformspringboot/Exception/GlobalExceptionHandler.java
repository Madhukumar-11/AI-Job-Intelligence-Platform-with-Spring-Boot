package com.madhu.jobintelligenceplatformspringboot.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExists.class)
    public ResponseEntity<String> handleException(UserExists ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleException(InvalidCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<String> handleException(JobNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<String> handleException(MissingServletRequestPartException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload File");
    }
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handleException(MultipartException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload File");
    }

    @ExceptionHandler(InvalidResumeException.class)
    public ResponseEntity<String> handleException(InvalidResumeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
