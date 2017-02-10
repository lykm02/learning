package com.springapp.mvc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.*;

/**
 * Created by kmchu on 16/5/12.
 */
//@ControllerAdvice(basePackages = "com.springapp.mvc")
public class ApiExceptionHandlerService {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ValidationException.class, ConstraintViolationException.class})
    public @ResponseBody
    ResponseEntity<String>  getValidationException(Exception e){
        ResponseEntity entity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        return entity;
    }


    @ExceptionHandler({BindException.class})
    public @ResponseBody
    ResponseEntity<String>  proccessSpringValidationException(BindException e){
        List<ObjectError> allErrors = e.getAllErrors();
        List<String> errors = new ArrayList<>();
        for(ObjectError error : allErrors){
            errors.add(error.getObjectName() + messageSource.getMessage(error, Locale.CHINA));
        }
        ResponseEntity entity = new ResponseEntity(errors,HttpStatus.OK);
        return entity;
    }
}
