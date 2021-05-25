package com.dcm.application.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : yaoximing
 * @date : 2020-11-24
 * @description :
 **/
public class DataSourceConfig implements BeanPostProcessor, BeanFactoryAware, ResourceLoaderAware, EnvironmentAware,
    ApplicationContextAware, InitializingBean, DisposableBean, ApplicationListener<ContextRefreshedEvent>,
    PriorityOrdered {



    private void register() {

    }




    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.register();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
