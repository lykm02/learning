package com.springapp.mvc;

import com.springapp.mvc.form.FormData;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

/**
 * Created by kmchu on 16/5/17.
 */

public class FormRequestArgumentResolvers extends ServletModelAttributeMethodProcessor {

    /**
     * @param annotationNotRequired if "true", non-simple method arguments and
     *                              return values are considered model attributes with or without a
     *                              {@code @ModelAttribute} annotation.
     */
    public FormRequestArgumentResolvers(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if(parameter.hasParameterAnnotation(FormData.class)){
            return true;
        }
        return false;
    }
}
