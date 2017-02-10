package com.springapp.mvc.aspects;

import com.springapp.mvc.exceptions.CustomException;
import org.springframework.aop.ThrowsAdvice;

/**
 * Created by kmchu on 16/5/19.
 */
public class ConvertThrowableAdvice implements ThrowsAdvice {

    public void afterThrowing(CustomException e) throws Throwable{
        System.out.println("as you know, nothing happened.");
        throw new IllegalArgumentException(e.getMessage());
    }
}
