package com.example.pmb.domain.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(BeanProvider.applicationContext == null) {
            BeanProvider.applicationContext = applicationContext;
        }
    }

    //  applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

/*    //  name   Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //  class  Bean.
    public static T getBean(Class clazz){
        return getApplicationContext().getBean(clazz);
    }

    //  name,  Clazz     Bean
    public static T getBean(String name,Class clazz){
        return getApplicationContext().getBean(name, clazz);
    }*/
}
