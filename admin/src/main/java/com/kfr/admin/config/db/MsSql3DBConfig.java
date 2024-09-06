package com.kfr.admin.config.db;


import jakarta.persistence.EntityManagerFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = {"com.kfr.admin.infrastructure.refresh", "com.kfr.admin.infrastructure.user.jpa"},// RefreshRepository 위치
        //basePackages = {"com.kfr.admin.infrastructure.refresh"},// RefreshRepository 위치
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1JpaTransactionManager"
)
@MapperScan(
        sqlSessionTemplateRef = "sqlSessionTemplate",
        basePackages = {"com.kfr.admin.infrastructure.user.mybatis"}
)
public class MsSql3DBConfig {
    @Bean
    @Primary
    public EntityManagerFactory db1EntityManagerFactory(@Qualifier("msSql3DataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.kfr.admin.domain.user", "com.kfr.admin.infrastructure.refresh");
        //factory.setPackagesToScan("com.kfr.admin.infrastructure.refresh");
        factory.setPersistenceUnitName("db1EntityManager");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    @Primary
    public PlatformTransactionManager db1JpaTransactionManager(@Qualifier("db1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }


    @Primary
    @Bean
    public SqlSessionFactory sessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Primary
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);

    }
}
