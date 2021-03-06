package com.ibarhatov.springapp;

import com.ibarhatov.springapp.config.Config;
import com.ibarhatov.springapp.config.SpringDataConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;

/**
 * Created by ibarkhatov on 23.03.2017.
 */
//инициализатор приложения на Tomcat
public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringDataConfig.class);
        ctx.getBean(Demo.class);
    }
}