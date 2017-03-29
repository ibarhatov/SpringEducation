package com.ibarhatov.springapp;

import com.ibarhatov.springapp.config.Config;
import com.ibarhatov.springapp.config.SpringDataConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by ibarkhatov on 15.03.2017.
 */
//запуск приложения из jar
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.ibarhatov.springapp");
    }
}
