package com.ibarhatov.springapp;

import com.ibarhatov.springapp.config.Config;
import com.ibarhatov.springapp.dao.BusinessProcessDao;
import com.ibarhatov.springapp.model.BusinessProcess;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by ibarkhatov on 15.03.2017.
 */
//запуск приложения из jar
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        BusinessProcessDao businessProcessDao = ctx.getBean(BusinessProcessDao.class);
        List<BusinessProcess> businessProcesses = businessProcessDao.getAll("RISK_LEVEL");
        System.out.println("- Print businessProcesses names;");
        System.out.println(businessProcesses.get(0).getName());
        System.out.println("- Print operationTypes names");
        businessProcesses.get(0).getOperationTypes().forEach(operationType -> System.out.println(operationType.getName()));
    }
}
