package com.springapp.mvc.api;

import com.springapp.mvc.exceptions.CustomException;
import com.springapp.mvc.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kmchu on 16/5/7.
 */
@Controller
@RequestMapping(value = "/validate",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ValidatorController {

    @Autowired
    Validator validator;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String validate(@Valid @Min(value=1l,message="should greater than 1") int val, String name){
        return val + name;
    }

    @RequestMapping(value="/info",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String info(){
        return validator.toString();
    }

    @Autowired
    ValidationService validationService;

    @RequestMapping(value="/name/{type}")
    public String getNames(@PathVariable int type){
        if(type == 1){
            return validationService.printList(Collections.<String>emptyList());
        }
        List<String> names = new LinkedList<>();
        names.add("hahha");
        return validationService.printList(names);
    }

    @RequestMapping(value="/throw")
    public void testThrows() throws CustomException {
        validationService.raiseException();
    }

}
