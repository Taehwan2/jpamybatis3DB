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
        basePackages = {"com.kfr.admin.infrastructure.mysql"},// RefreshRepository 위치
        entityManagerFactoryRef = "db3EntityManagerFactory",
        transactionManagerRef = "db3JpaTransactionManager"
)
public class MysqlDBConfig {
    @Bean
    public EntityManagerFactory db3EntityManagerFactory(@Qualifier("mySqlDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.kfr.admin.domain.mysql");
        factory.setPersistenceUnitName("db3EntityManager");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager db3JpaTransactionManager(@Qualifier("db3EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}
