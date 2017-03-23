package com.ibarhatov.springapp.dao;

import com.ibarhatov.springapp.model.BusinessProcess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Created by ibarkhatov on 17.03.2017.
 */
@Service
public class BusinessProcessDao {

    @Autowired
    private SessionFactory sessionFactory;

//    селектим все записи о бизнес процессах из базы
    public List<BusinessProcess> getAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from BusinessProcess").list();
    }

//    делаем селект и выводим его в консоль + файл
    public void runSelectToFile(){
        StringBuilder builder = new StringBuilder();
        for (BusinessProcess businessProcess : getAll()) {
            businessProcess.getOperationTypes().forEach(operationType ->
                    operationType.getOperationStages().forEach(operationStage -> {
//                        получаем новую строку, выводим в консоль и добавляем к остальным
                        System.out.print(builder.append(businessProcess.getName() + " -> " +
                                operationType.getName() + " -> " + operationStage.getName()).toString());
                        builder.append("\n");
                    }));
        }
        File file = new File("SpringApp.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        выводим селект в файл
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))){
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
