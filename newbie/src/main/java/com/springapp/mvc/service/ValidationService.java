package com.springapp.mvc.service;

import com.springapp.mvc.exceptions.CustomException;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kmchu on 16/5/4.
 */
@Service
@Validated
public class ValidationService {



    //not working. Maybe need aop proxy to check input args.
    public String printList(@Valid @NotEmpty List<String> itemNames){
        return itemNames.toString();
    }


    public void raiseException() throws CustomException{
        throw new CustomException("lalalalala in validation");
    }
}
