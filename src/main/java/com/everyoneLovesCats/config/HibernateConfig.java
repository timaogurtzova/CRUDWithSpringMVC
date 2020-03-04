package com.everyoneLovesCats.config;

import com.everyoneLovesCats.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.everyoneLovesCats.dao")
@PropertySource("classpath:db/db.properties")
public class HibernateConfig {
    private org.springframework.core.env.Environment environment;

    @Autowired
    public HibernateConfig(org.springframework.core.env.Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Properties dbProperties() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, environment.getProperty("jbdcDriverMySQL"));
        properties.setProperty(Environment.URL, environment.getProperty("connectionURL"));
        properties.setProperty(Environment.USER, environment.getProperty("userNameDb"));
        properties.setProperty(Environment.PASS, environment.getProperty("userPassword"));
        properties.setProperty(Environment.JDBC_TIME_ZONE, environment.getProperty("serverTimeZone"));
        properties.setProperty("useSSL", environment.getProperty("useSSL"));
        properties.setProperty(Environment.DIALECT, environment.getProperty("DIALECT"));
        properties.setProperty(Environment.SHOW_SQL, environment.getProperty("SHOW_SQL"));
        properties.setProperty(Environment.HBM2DDL_AUTO, environment.getProperty("HBM2DDL_AUTO"));
        return properties;
    }

    @Bean
    public org.hibernate.cfg.Configuration configurationHiberate(@Qualifier("dbProperties") Properties properties) {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }

    @Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
