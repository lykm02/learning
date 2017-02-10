package com.springapp.mvc.form;

import java.lang.annotation.*;

/**
 * Created by kmchu on 16/5/17.
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormData {
    String value() default "name";
}
