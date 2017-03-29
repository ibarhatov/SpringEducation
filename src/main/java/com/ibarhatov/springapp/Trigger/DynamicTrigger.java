package com.ibarhatov.springapp.Trigger;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by ibarkhatov on 29.03.2017.
 */
public class DynamicTrigger implements Trigger{

//    значение периодичности выполнения задачи
    private long period;

    public DynamicTrigger() {
        loadPeriodFromSource();
    }

    public DynamicTrigger(long period) {
        this.period = period;
    }

//    возвращает время, в которое должна выполниться запланированная задача
    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
        Date nextExecutionTime = lastActualExecutionTime != null ? lastActualExecutionTime : new Date();
        nextExecutionTime.setTime(nextExecutionTime.getTime() + period);
        return nextExecutionTime;
    }

    /**
     * обновить {@param period}
     */
    public void updatePeriod() {
        loadPeriodFromSource();
    }

    /**
     * загрузить из ресурса {@param period}
     */
    private void loadPeriodFromSource() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/schedule.txt"))){
            this.period = scanner.nextLong();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
