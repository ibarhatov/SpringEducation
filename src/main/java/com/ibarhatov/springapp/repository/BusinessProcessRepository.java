package com.ibarhatov.springapp.repository;

import com.ibarhatov.springapp.model.BusinessProcess;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ibarkhatov on 27.03.2017.
 */
//    DAO на основе Spring Data
public interface BusinessProcessRepository extends JpaRepository<BusinessProcess, String> {}
