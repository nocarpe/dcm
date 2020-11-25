package com.dcm.application.config.utils;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.dcm.application.config.ConfigMapConfigurationProperties;
import com.dcm.application.config.PropertiesConfigurationProperties;
import com.dcm.application.config.prop.DruidDataSourceProperties;
import com.dcm.application.config.prop.DruidFilterProperties;
import java.util.Iterator;
import java.util.List;
import org.apache.ibatis.datasource.DataSourceException;
import org.apache.shardingsphere.core.yaml.config.masterslave.YamlMasterSlaveRuleConfiguration;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingRuleConfiguration;
import org.apache.shardingsphere.spi.database.type.DatabaseType;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;

public class DataSourceBeanUtils {
    private static final Logger log = LoggerFactory.getLogger(DataSourceBeanUtils.class);
    private static final String DATASOURCE_PREFIX = ".sharding.jdbc.datasource.";
    private static final String SHARDING_RULE_PREFIX = ".sharding.jdbc.config.sharding";
    private static final String MASTER_SLAVE_RULE_PREFIX = ".sharding.jdbc.config.masterslave";
    private static final String CONFIG_MAP_PREFIX = ".sharding.jdbc.config";
    private static final String PROPERTIES_PREFIX = ".sharding.jdbc.config";
    public static final String SHARDING_DS_PREFIX = "sharding.jdbc.ds.prefix";
    public static final String RW_DS_PREFIX = "rw.jdbc.ds.prefix";
    public static final String RW_WRITE_DRUID_DS_PREFIX = ".druid.datasource";
    public static final String RW_READ_DRUID_DS_PREFIX = ".read.druid.datasource";
    public static final String SHARDING_PRIMARY_DATASOURCE = ".sharding.jdbc.datasource.primary";
    public static final String RW_PRIMARY_DATASOURCE = ".rw.jdbc.datasource.primary";
    public static final String MYBATIS_PLUS = ".mybatis-plus";
    public static final String DRUID_DATASOURCE = "DruidDataSource";
    public static final String READ_DRUID_DATASOURCE = "ReadDruidDataSource";
    public static final String SQL_SESSION_FACTORY = "SqlSessionFactory";
    public static final String READ_SQL_SESSION_FACTORY = "ReadSqlSessionFactory";
    public static final String TRANSACTION_MANAGER = "TransactionManager";
    public static final String READ_TRANSACTION_MANAGER = "ReadTransactionManager";
    public static final String TRANSACTION_TEMPLATE = "TransactionTemplate";
    public static final String READ_TRANSACTION_TEMPLATE = "ReadTransactionTemplate";
    private PropUtils propUtils;

    public DataSourceBeanUtils(ApplicationContext applicationContext) {
        this.propUtils = new PropUtils(applicationContext);
    }

    public YamlShardingRuleConfiguration buildShardingRuleConfigurationProperties(final String dsName) {
        String prefix = dsName + ".sharding.jdbc.config.sharding";
        return (YamlShardingRuleConfiguration)this.doBuild(prefix, YamlShardingRuleConfiguration.class);
    }

    public YamlMasterSlaveRuleConfiguration buildMasterSlaveRuleConfigurationProperties(final String dsName) {
        String prefix = dsName + ".sharding.jdbc.config.masterslave";
        return (YamlMasterSlaveRuleConfiguration)this.doBuild(prefix, YamlMasterSlaveRuleConfiguration.class);
    }

    public ConfigMapConfigurationProperties buildConfigMapConfigurationProperties(final String dsName) {
        String prefix = dsName + ".sharding.jdbc.config";
        return (ConfigMapConfigurationProperties)this.doBuild(prefix, ConfigMapConfigurationProperties.class);
    }

    public PropertiesConfigurationProperties buildPropertiesConfigurationProperties(final String dsName) {
        String prefix = dsName + ".sharding.jdbc.config";
        return (PropertiesConfigurationProperties)this.doBuild(prefix, PropertiesConfigurationProperties.class);
    }



    public MybatisPlusProperties buildMybatisPlusProperties(String dbType) {
        String prefix = dbType + ".mybatis-plus";
        return (MybatisPlusProperties)this.doBuild(prefix, MybatisPlusProperties.class);
    }

    public DruidDataSourceProperties buildWriteDruidDataSourceProperties(final String dsName) {
        String prefix = dsName + ".druid.datasource";
        return (DruidDataSourceProperties)this.doBuild(prefix, DruidDataSourceProperties.class);
    }

    public DruidDataSourceProperties buildReadDruidDataSourceProperties(final String dsName) {
        String prefix = dsName + ".read.druid.datasource";
        return (DruidDataSourceProperties)this.doBuild(prefix, DruidDataSourceProperties.class);
    }


    public void registerBean(BeanFactory beanFactory, String dsName, String baseBeanName, BeanDefinitionBuilder beanDefinitionBuilder, boolean isPrimaryDataSource) {
//        beanDefinitionBuilder.setAutowireMode(1);
//        String dsPrefix = DataSourceBuildUtils.getDsPrefix(dsName);
//        String beanName = DataSourceBuildUtils.getRealDsName(dsName) + dsPrefix + baseBeanName;
//        if (isPrimaryDataSource) {
//            if ("DruidDataSource".equals(baseBeanName)) {
//                beanDefinitionBuilder.getBeanDefinition().setPrimary(true);
//            }
//
//            if ("SqlSessionFactory".equals(baseBeanName)) {
//                beanDefinitionBuilder.getBeanDefinition().setPrimary(true);
//            }
//
//            if ("TransactionManager".equals(baseBeanName)) {
//                beanDefinitionBuilder.getBeanDefinition().setPrimary(true);
//            }
//
//            if ("TransactionTemplate".equals(baseBeanName)) {
//                beanDefinitionBuilder.getBeanDefinition().setPrimary(true);
//            }
//        }
//
//        ((DefaultListableBeanFactory)beanFactory).registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    public BeanDefinitionBuilder beanDefinitionBuilder(Class clazz) {
        return BeanDefinitionBuilder.genericBeanDefinition(clazz);
    }

    public void mapperScan(List<String> packages, String sqlSessionFactoryBeanName, BeanFactory beanFactory, ResourceLoader resourceLoader) {
        ClassPathMapperScanner scanner = new ClassPathMapperScanner((DefaultListableBeanFactory)beanFactory);

        try {
            if (resourceLoader != null) {
                scanner.setResourceLoader(resourceLoader);
            }

            Iterator iterator = packages.iterator();

            while(iterator.hasNext()) {
                String pkg = (String)iterator.next();
                log.debug("Using auto-configuration base package '" + pkg + "'");
            }

            scanner.setSqlSessionFactoryBeanName(sqlSessionFactoryBeanName);
            scanner.registerFilters();
            scanner.doScan(StringUtils.toStringArray(packages));
        } catch (IllegalStateException var8) {
            log.error("Could not determine auto-configuration package, automatic mapper scanning disabled." + var8);
        }

    }

    private <T> T doBuild(final String prefix, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            ResolvableType type = ResolvableType.forClass(clazz);
            Bindable<?> target = Bindable.of(type).withExistingValue(instance);
            List<Validator> validators = this.propUtils.getValidators(target);
            BindHandler bindHandler = this.propUtils.getBindHandler(validators);
            this.propUtils.getBinder().bind(prefix, target, bindHandler);
            return instance;
        } catch (Exception var8) {
            throw new DataSourceException("构建" + clazz.getName() + "异常");
        }
    }
}