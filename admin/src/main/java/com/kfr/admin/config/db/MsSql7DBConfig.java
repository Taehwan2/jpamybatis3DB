package com.kfr.admin.config.db;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Profile("!test")
@EnableJpaRepositories(
        basePackages = "com.kfr.admin.domain.mssql7", // JangoRepository 위치
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2JpaTransactionManager"
)
public class MsSql7DBConfig {
    @Bean
    public EntityManagerFactory db2EntityManagerFactory(@Qualifier("msSql7DataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.kfr.admin.infrastructure.jango"); //
        factory.setPersistenceUnitName("db2EntityManager");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager db2JpaTransactionManager(@Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}
