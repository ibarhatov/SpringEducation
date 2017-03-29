package com.ibarhatov.springapp.dao;

import com.ibarhatov.springapp.model.BusinessProcess;
import com.ibarhatov.springapp.service.BusinessProcessService;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ibarkhatov on 17.03.2017.
 */
//  DAO на основе Hibernate
//@Service
@Qualifier("businessProcessDao")
public class BusinessProcessDao extends HibernateDaoSupport implements BusinessProcessService {

//    @Qualifier("sessionFactory")
    public BusinessProcessDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    //    селектим все записи о бизнес процессах из базы
    @Override
    public List<BusinessProcess> findAll() {
        List<BusinessProcess> result = getHibernateTemplate().loadAll(BusinessProcess.class);
//        обращаемся к operationTypes для загрузки их из БД
        result.get(0).getOperationTypes().size();
        return result;
    }

//    селектим бизнес процесс из базы по идентификатору cod
    @Override
    public BusinessProcess findOne(String code) {
        DetachedCriteria criteria = DetachedCriteria.forClass(BusinessProcess.class);
        criteria.add(Restrictions.eq("code", code));
        BusinessProcess result = (BusinessProcess) getHibernateTemplate().findByCriteria(criteria).get(0);
//        обращаемся к operationTypes для загрузки их из БД
        result.getOperationTypes().size();
        return result;
    }
}