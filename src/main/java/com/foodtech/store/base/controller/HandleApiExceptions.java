package com.foodtech.store.base.controller;

import com.foodtech.store.auth.exceptions.AuthException;
import com.foodtech.store.auth.exceptions.NotAccessException;
import com.foodtech.store.base.api.response.ErrorResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleApiExceptions  extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }


    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Object> notFoundException(ChangeSetPersister.NotFoundException ex, WebRequest request){
        return buildResponseEntity(ErrorResponse.of("NotFoundException", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> authException(AuthException ex, WebRequest request){
        return buildResponseEntity(ErrorResponse.of("AuthException", HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(NotAccessException.class)
    public ResponseEntity<Object> notAccessException(NotAccessException ex, WebRequest request){
        return buildResponseEntity(ErrorResponse.of("NotAccessException", HttpStatus.FORBIDDEN));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex, WebRequest request){
        return buildResponseEntity(ErrorResponse.of("Exception", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
