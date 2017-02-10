package com.springapp.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by kmchu on 16/5/9.
 */
@Configuration
public class MyMvcConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(jacksonMessageConverter());
    }

    private HttpMessageConverter<?> jacksonMessageConverter(){
        ObjectMapper objMapper = new ObjectMapper().setPropertyNamingStrategy(
                PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        HttpMessageConverter<?> converter = new MappingJackson2HttpMessageConverter(objMapper);
        return converter;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation is empty.
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }
}
