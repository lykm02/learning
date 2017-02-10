package com.springapp.mvc;

import com.springapp.mvc.aspects.ConvertThrowableAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.interceptor.DebugInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * Created by kmchu on 16/5/4.
 */
@Configuration
@EnableWebMvc
@PropertySources(
        {@PropertySource(value = "classpath:message.properties")}
)
public class AppConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        return postProcessor;
    }

    @Bean
    public Advisor logAdvisor(){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.springapp.mvc..*.*(..))");
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new DebugInterceptor());
        return advisor;
    }

    @Bean
    public Advisor throwsAdvisor(){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.springapp.mvc.service.ValidationService.*(..))");
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new ThrowsAdviceInterceptor(new ConvertThrowableAdvice()));
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("classpath:message");
        return messageSource;
    }

}
