package com.imooc.milanlover.jspviewlistdemo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Faithful-Mind on 2018/3/25.
 */
public class AppCtxUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppCtxUtil.applicationContext = applicationContext;
//        System.out.println("11111111 " + applicationContext + " IS SET!");
    }
    public static Object getBean(String name) {
//        System.out.println("22222222 " + applicationContext + " IS INVOKING .getBean(String name)!");
        return applicationContext.getBean(name);
    }
}
