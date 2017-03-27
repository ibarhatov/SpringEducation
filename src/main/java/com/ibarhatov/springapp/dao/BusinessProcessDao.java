package com.ibarhatov.springapp.dao;

import com.ibarhatov.springapp.model.BusinessProcess;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ibarkhatov on 17.03.2017.
 */
@Service
public class BusinessProcessDao extends HibernateDaoSupport{

    public BusinessProcessDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    //    селектим все записи о бизнес процессах из базы по полю code(identify)
    @Transactional(readOnly = true)
    public List<BusinessProcess> getAll(String code) {
        DetachedCriteria criteria = DetachedCriteria.forClass(BusinessProcess.class);
        criteria.add(Restrictions.eq("code", code));
        List<BusinessProcess> result = null;
        result = (List<BusinessProcess>) getHibernateTemplate().findByCriteria(criteria);
//        обращаемся к operationTypes для загрузки их из БД
        result.get(0).getOperationTypes().size();
        return result;
    }
}
