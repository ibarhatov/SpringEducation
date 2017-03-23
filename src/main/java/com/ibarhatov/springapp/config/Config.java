package com.ibarhatov.springapp.config;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by ibarkhatov on 23.03.2017.
 */
@Configuration
@ComponentScan("com.ibarhatov.springapp")
public class Config {

    @Bean
    public DataSource dataSource() throws SQLException, IOException {
        OracleDataSource dataSource = new OracleDataSource();
        Properties properties = new Properties();
//        читаем из файла параметры для подключения к БД для Jar
        FileInputStream stream = new FileInputStream("src/main/resources/jdbc.properties");

//        читаем из файла параметры для подключения к БД для War
//        FileInputStream stream = new FileInputStream("webapps/springApp-0.1/WEB-INF/classes/jdbc.properties");
        properties.load(stream);
        stream.close();

        dataSource.setURL(properties.getProperty("url"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("pass"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

//        указываем директорию классов объектов
        sessionFactory.setPackagesToScan("com.ibarhatov.springapp.model");
        Properties properties = new Properties();

//        читаем из файла параметры конфигурации hibernate для Jar
        FileInputStream stream = new FileInputStream("src/main/resources/hibernate.properties");

//        читаем из файла параметры конфигурации hibernate для War
//        FileInputStream stream = new FileInputStream("webapps/springApp-0.1/WEB-INF/classes/hibernate.properties");
        properties.load(stream);
        stream.close();
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }
}
