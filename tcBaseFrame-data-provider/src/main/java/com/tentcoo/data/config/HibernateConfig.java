//package com.tentcoo.data.config;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///**
// * Created by rover on 2018/1/19.
// */
//@Configuration
//@EnableTransactionManagement
//@AutoConfigureAfter({DruidDataSourceConfiguration.class})
//public class HibernateConfig extends LocalSessionFactoryBean implements EnvironmentAware {
//
//    @Autowired
//    private DataSource dataSource;
//
//    private RelaxedPropertyResolver propertyResolver;
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        this.propertyResolver = new RelaxedPropertyResolver(environment,"hibernate.");
//    }
//
//    @Bean(name = "sqlSessionFactory4Hibernate")
//    public SessionFactory sqlSessionFactoryBean() {
//        try {
//            this.setDataSource(dataSource);
//            this.setPackagesToScan(propertyResolver.getProperty("packageScan"));
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Bean
//    public PlatformTransactionManager platformTransactionManager() {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//}
