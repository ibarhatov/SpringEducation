package com.ibarhatov.springapp;

import com.ibarhatov.springapp.model.BusinessProcess;
import com.ibarhatov.springapp.model.OperationType;
import com.ibarhatov.springapp.service.BusinessProcessService;
import com.ibarhatov.springapp.Trigger.DynamicTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibarkhatov on 27.03.2017.
 */
//    Класс для демонстрации работы приложения с использованием Spring Scheduler
@Service
public class Demo implements SchedulingConfigurer{

//    выбираем имя бина, которым реализован интерфейс BusinessProcessService.
//    private final static String serviceBean = "businessProcessDao"; //  Hibernate
    private final static String serviceBean = "businessProcessServiceImpl"; //  SpringData

    @Autowired
    @Qualifier(serviceBean)
    BusinessProcessService businessProcessService;

    /*
    * метод, вызываемый Scheduler'ом
    */
    private void scheduleAction(ScheduledTaskRegistrar taskRegistrar){
        BusinessProcess businessProcess;
        businessProcess= businessProcessService.findOne("INDIVIDUAL_TARIFF");
//        businessProcess = businessProcessService.findAll().get(0);
        System.out.println("- Print businessProcesses names;");
        System.out.println(businessProcess.getName());
        System.out.println("- Print operationTypes names");
        businessProcess.getOperationTypes().forEach(operationType -> System.out.println(operationType.getName()));
        File file = new File("SpringApp.txt");
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        выводим селект в файл
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))){
            writer.write(businessProcess.toString());
            for (OperationType operationType : businessProcess.getOperationTypes()) {
                writer.write(operationType.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        обновляем значение периодичности
        ((DynamicTrigger) taskRegistrar.getTriggerTaskList().get(0).getTrigger()).updatePeriod();;
    }

//    конфигурируем triggerTask, устанавливая необходимый метод для выполнения и собственный trigger
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        List<TriggerTask> triggerTasks = new ArrayList<>();
        Trigger myTrigger = new DynamicTrigger();
        triggerTasks.add(new TriggerTask(() -> scheduleAction(taskRegistrar),
                myTrigger));
        taskRegistrar.setTriggerTasksList(triggerTasks);
    }
}