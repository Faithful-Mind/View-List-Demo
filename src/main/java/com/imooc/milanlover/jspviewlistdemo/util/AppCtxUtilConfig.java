package com.imooc.milanlover.jspviewlistdemo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Faithful-Mind on 2018/3/25.
 */
@Configuration
public class AppCtxUtilConfig {

    @Bean(name = "appCtxUtil")
    public AppCtxUtil appCtxUtil() {
        return new AppCtxUtil();
    }
}
