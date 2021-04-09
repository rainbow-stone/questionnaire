package com.baoshine.questionnaire.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:application-db.properties"})
@EnableTransactionManagement
@EnableJpaAuditing
@Slf4j
@EnableJpaRepositories(
        basePackages = "com.baoshine.questionnaire.repository",
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "transactionManager")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "com.baoshine.datasource.questionnaire")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.baoshine.questionnaire.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.dialect.storage_engine", "innodb");
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("com.baoshine.datasource.jpa.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.enable_lazy_load_no_trans",
                env.getProperty("com.baoshine.datasource.jpa.enable_lazy_load_no_trans"));
        em.setJpaPropertyMap(properties);

        return em;
    }


    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(Objects.requireNonNull(entityManager().getObject()));
    }
}