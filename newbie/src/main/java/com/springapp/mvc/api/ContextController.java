package com.springapp.mvc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kmchu on 16/5/9.
 */
@RestController
public class ContextController implements ApplicationContextAware{

    @Autowired
    private ApplicationContext context;

    public void setApplicationContext(ApplicationContext context){
        this.context = context;
    }

    @RequestMapping(value="/context")
    public List<String> beanNameList(String type){
        if(type == null) {
            return Arrays.asList(context.getBeanDefinitionNames());
        }
        return Collections.emptyList();
    }

    @RequestMapping(value="/context/{name}")
    public List<Object>  beanOfName(@PathVariable("name") String name){
        if(context.containsBean(name)){
            Object bean = context.getBean(name);
            List<Object> list = new ArrayList<>();
            list.add(bean);
            list.add(bean.getClass().getCanonicalName());
            return list;
        }
        return Collections.emptyList();
    }
}
