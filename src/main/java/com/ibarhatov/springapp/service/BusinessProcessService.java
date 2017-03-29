package com.ibarhatov.springapp.service;

import com.ibarhatov.springapp.model.BusinessProcess;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ibarkhatov on 27.03.2017.
 */
public interface BusinessProcessService {
    @Transactional(readOnly = true)
    List<BusinessProcess> findAll();
    @Transactional(readOnly = true)
    BusinessProcess findOne(String code);
}
