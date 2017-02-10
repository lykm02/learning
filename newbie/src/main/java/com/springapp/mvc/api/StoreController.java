package com.springapp.mvc.api;

import com.springapp.mvc.form.StoreForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by kmchu on 16/5/13.
 */
@RestController
public class StoreController {
    @InitBinder
    public void initBinder(WebDataBinder binder){
    }

    @RequestMapping(value="/store")
    public ResponseEntity<StoreForm> queryByName(@Valid @ModelAttribute StoreForm storeForm){
        return new ResponseEntity<StoreForm>(storeForm, HttpStatus.OK);
    }

    @RequestMapping(value="/store/buId")
    public ResponseEntity<String> getBuId(@Valid StoreForm storeForm){
        return new ResponseEntity<>(storeForm.getBuId(), HttpStatus.OK);
    }
}
