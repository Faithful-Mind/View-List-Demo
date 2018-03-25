package com.imooc.milanlover.jspviewlistdemo.web.listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Faithful-Mind on 2018/3/25.
 */
@WebListener
public class ContainerStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClassPathXmlApplicationContext cCtx = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
//        System.out.println("_______________");
//        System.out.println(cCtx);
//        System.out.println("_______________");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
