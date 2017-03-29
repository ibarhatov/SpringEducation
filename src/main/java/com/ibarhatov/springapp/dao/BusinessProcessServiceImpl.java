package com.ibarhatov.springapp.dao;

import com.ibarhatov.springapp.model.BusinessProcess;
import com.ibarhatov.springapp.repository.BusinessProcessRepository;
import com.ibarhatov.springapp.service.BusinessProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ibarkhatov on 27.03.2017.
 */
//    DAO на основе SpringData
@Service
@Qualifier ("businessProcessServiceImpl")
public class BusinessProcessServiceImpl implements BusinessProcessService
{
    @Autowired
    BusinessProcessRepository businessProcessRepository;

    @Override
    public BusinessProcess findOne(String code) {
        BusinessProcess result = businessProcessRepository.findOne(code);
//        обращаемся к operationTypes для загрузки их из БД
        result.getOperationTypes().size();
        return result;
    }

    @Override
    public List<BusinessProcess> findAll(){
        List<BusinessProcess> result = (List<BusinessProcess>) businessProcessRepository.findAll();
//        обращаемся к operationTypes для загрузки их из БД
        result.get(0).getOperationTypes().size();
        return result;
    }
}
