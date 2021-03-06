package com.ibarhatov.springapp.config;

import oracle.jdbc.pool.OracleDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by ibarkhatov on 23.03.2017.
 */
//    Config for Hibernate
//@Configuration
@EnableTransactionManagement
@ComponentScan("com.ibarhatov.springapp")
@PropertySource("classpath:jdbc.properties")
public class Config {

    @Bean
    public DataSource dataSource(Environment env) throws SQLException, IOException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Environment env) throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

//        указываем директорию классов объектов
        sessionFactory.setPackagesToScan("com.ibarhatov.springapp.model");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }


    @Bean
    public HibernateTransactionManager transactionManaget(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }
}
