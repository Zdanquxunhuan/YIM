package com.yu.yimserver.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * In this way, we can easily get Bean instances registered in the Spring container in Java code
 * without having to manually create the object or manage the object lifecycle.
 *
 * Implementing this interface makes sense for example when an object requires access to a set of collaborating beans.
 * Note that configuration via bean references is preferable to implementing this interface
 * just for bean lookup purposes.
 */
@Component
public class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> tClass){
        return context.getBean(tClass);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return context.getBean(name,clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


}
