package org.example.config;

import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Config {
//    public static SessionFactory getSession(){
//        try{
//            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//        } catch (HibernateException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }

    public static SessionFactory getSession() {
        Configuration configuration = new Configuration();
        Properties prop = new Properties();
        prop.setProperty("connection.driver_class", "com.postgresql.Driver");
        prop.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/mydatabase");
        prop.setProperty("hibernate.connection.username", "postgres");
        prop.setProperty("hibernate.connection.password", "postgres");
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.setProperty("hibernate.show_sql", "true");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");
        prop.setProperty("hibernate.current_session_context_class", "thread");
        configuration.setProperties(prop);
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);

    }
}
