package com.springapp.mvc.service;

import com.springapp.mvc.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * Created by kmchu on 16/5/15.
 */
@ControllerAdvice
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e){
        return new ResponseEntity<Object>("catch custom exception which missing illegal arguments", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ValidationException.class, ConstraintViolationException.class})
    public @ResponseBody
    ResponseEntity<String>  getValidationException(Exception e){
        ResponseEntity entity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        return entity;
    }

}
