package com.dcm.application.config.utils;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.dcm.application.config.prop.DruidDataSourceProperties;
import com.dcm.application.config.prop.DruidFilterProperties;
import com.dcm.application.config.sorter.PhoenixExceptionSorter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

public class DataSourceUtils {
    private static final Logger log = LoggerFactory.getLogger(DataSourceUtils.class);

    public DataSourceUtils() {
    }

    public static DruidDataSource buildDruidDataSource(DruidDataSourceProperties druidDataSourceProperties) throws Exception {
        DruidDataSource result = new DruidDataSource();
        result.setName(druidDataSourceProperties.getName());
        result.setUrl(druidDataSourceProperties.getUrl());
        result.setUsername(druidDataSourceProperties.getUsername());
        result.setPassword(druidDataSourceProperties.getPassword());
        result.setConnectionProperties("config.decrypt=true;config.decrypt.key=" + druidDataSourceProperties.getPwdPublicKey());
        result.setFilters("config");
        result.setMaxActive(druidDataSourceProperties.getMaxActive());
        result.setInitialSize(druidDataSourceProperties.getInitialSize());
        result.setMaxWait((long)druidDataSourceProperties.getMaxWait());
        result.setMinIdle(druidDataSourceProperties.getMinIdle());
        result.setTimeBetweenEvictionRunsMillis((long)druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        result.setMinEvictableIdleTimeMillis((long)druidDataSourceProperties.getMinEvictableIdleTimeMillis());
        result.setMaxEvictableIdleTimeMillis((long)druidDataSourceProperties.getMaxEvictableIdleTimeMillis());
        result.setValidationQuery(druidDataSourceProperties.getValidationQuery());
        result.setValidationQueryTimeout(druidDataSourceProperties.getValidationQueryTimeout());
        result.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
        result.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
        result.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
        result.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
        result.setMaxOpenPreparedStatements(druidDataSourceProperties.getMaxOpenPreparedStatements());
        result.setDbType(druidDataSourceProperties.getDbType());
        if (druidDataSourceProperties.isEnableMonitor()) {
            StatFilter filter = buildStatFilter(druidDataSourceProperties);
            List<Filter> list = new ArrayList();
            list.add(filter);
            result.setProxyFilters(list);
        }

        return result;
    }

    public static DruidDataSource buildDruidDataSource(DruidDataSourceProperties druidDataSourceProperties, DruidFilterProperties druidFilterProperties) throws Exception {
        DruidDataSource result = buildDruidDataSource(druidDataSourceProperties);
        if (null == druidFilterProperties) {
            return result;
        } else {
            List<Filter> filterList = result.getProxyFilters();
            if (druidFilterProperties.getEnableWallFilter() && druidDataSourceProperties.isEnableWallFilter()) {
                WallFilter wallFilter = buildWallFilter(druidFilterProperties.getWall(), druidDataSourceProperties.isWallLogViolation(), druidDataSourceProperties.isWallThrowException());
                filterList.add(wallFilter);
            }

            return result;
        }
    }

    public static DruidDataSource buildPhoenixDruidDataSource(DruidDataSourceProperties druidDataSourceProperties) throws Exception {
        DruidDataSource result = new DruidDataSource();
        result.setName(druidDataSourceProperties.getName());
        result.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        result.setUrl(druidDataSourceProperties.getUrl());
        result.setMaxActive(druidDataSourceProperties.getMaxActive());
        result.setInitialSize(druidDataSourceProperties.getInitialSize());
        result.setMaxWait((long)druidDataSourceProperties.getMaxWait());
        result.setMinIdle(druidDataSourceProperties.getMinIdle());
        result.setTimeBetweenEvictionRunsMillis((long)druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        result.setMinEvictableIdleTimeMillis((long)druidDataSourceProperties.getMinEvictableIdleTimeMillis());
        result.setValidationQuery(druidDataSourceProperties.getValidationQuery());
        result.setValidationQueryTimeout(druidDataSourceProperties.getValidationQueryTimeout());
        result.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
        result.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
        result.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
        result.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
        result.setMaxOpenPreparedStatements(druidDataSourceProperties.getMaxOpenPreparedStatements());
        result.setDbType(druidDataSourceProperties.getDbType());
        result.setConnectionProperties("phoenix.schema.isNamespaceMappingEnabled=true");
        result.setRemoveAbandoned(true);
        result.setRemoveAbandonedTimeout(86400);
        result.setExceptionSorter(new PhoenixExceptionSorter());
        return result;
    }

    public static void buildDataSourceBeanDefinitionBuilder(BeanDefinitionBuilder dataSourceBeanDefinitionBuilder, DruidDataSourceProperties druidDataSourceProperties, DruidFilterProperties druidFilterProperties) {
        dataSourceBeanDefinitionBuilder.addPropertyValue("name", druidDataSourceProperties.getName());
        dataSourceBeanDefinitionBuilder.addPropertyValue("url", druidDataSourceProperties.getUrl());
        dataSourceBeanDefinitionBuilder.addPropertyValue("username", druidDataSourceProperties.getUsername());
        dataSourceBeanDefinitionBuilder.addPropertyValue("password", druidDataSourceProperties.getPassword());
        dataSourceBeanDefinitionBuilder.addPropertyValue("connectionProperties", "config.decrypt=true;config.decrypt.key=" + druidDataSourceProperties.getPwdPublicKey());
        dataSourceBeanDefinitionBuilder.addPropertyValue("filters", "config");
        dataSourceBeanDefinitionBuilder.addPropertyValue("maxActive", druidDataSourceProperties.getMaxActive());
        dataSourceBeanDefinitionBuilder.addPropertyValue("initialSize", druidDataSourceProperties.getInitialSize());
        dataSourceBeanDefinitionBuilder.addPropertyValue("maxWait", druidDataSourceProperties.getMaxWait());
        dataSourceBeanDefinitionBuilder.addPropertyValue("minIdle", druidDataSourceProperties.getMinIdle());
        dataSourceBeanDefinitionBuilder.addPropertyValue("timeBetweenEvictionRunsMillis", druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        dataSourceBeanDefinitionBuilder.addPropertyValue("minEvictableIdleTimeMillis", druidDataSourceProperties.getMinEvictableIdleTimeMillis());
        dataSourceBeanDefinitionBuilder.addPropertyValue("validationQuery", druidDataSourceProperties.getValidationQuery());
        dataSourceBeanDefinitionBuilder.addPropertyValue("validationQueryTimeout", druidDataSourceProperties.getValidationQueryTimeout());
        dataSourceBeanDefinitionBuilder.addPropertyValue("testWhileIdle", druidDataSourceProperties.isTestWhileIdle());
        dataSourceBeanDefinitionBuilder.addPropertyValue("testOnBorrow", druidDataSourceProperties.isTestOnBorrow());
        dataSourceBeanDefinitionBuilder.addPropertyValue("testOnReturn", druidDataSourceProperties.isTestOnReturn());
        dataSourceBeanDefinitionBuilder.addPropertyValue("poolPreparedStatements", druidDataSourceProperties.isPoolPreparedStatements());
        dataSourceBeanDefinitionBuilder.addPropertyValue("maxOpenPreparedStatements", druidDataSourceProperties.getMaxOpenPreparedStatements());
        dataSourceBeanDefinitionBuilder.addPropertyValue("dbType", druidDataSourceProperties.getDbType());
        List<Filter> filterList = new ArrayList();
        if (druidDataSourceProperties.isEnableMonitor()) {
            StatFilter statFilter = buildStatFilter(druidDataSourceProperties);
            filterList.add(statFilter);
        }

        if (druidFilterProperties.getEnableWallFilter() && druidDataSourceProperties.isEnableWallFilter()) {
            WallFilter wallFilter = buildWallFilter(druidFilterProperties.getWall(), druidDataSourceProperties.isWallLogViolation(), druidDataSourceProperties.isWallThrowException());
            filterList.add(wallFilter);
        }

        dataSourceBeanDefinitionBuilder.addPropertyValue("proxyFilters", filterList);
    }


    public static SqlSessionFactory buildMybatisSqlSessionFactoryBean(DruidDataSource dataSource, List<String> mapperLocationList, MybatisPlusProperties properties, ApplicationContext applicationContext) throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        return buildMybatisSqlSession(factory, mapperLocationList, properties, applicationContext);
    }

    public static SqlSessionFactory buildMybatisSqlSessionFactoryBean(DataSource dataSource,List<String> mapperLocationList, MybatisPlusProperties properties, ApplicationContext applicationContext) throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        return buildMybatisSqlSession(factory, mapperLocationList, properties, applicationContext);
    }

  /*  public static void addPageInterceptor(SqlSessionFactory sqlSessionFactory, PageHelperProperties pageHelperProperties) {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.putAll(pageHelperProperties.getProperties());
        interceptor.setProperties(properties);
        sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
    }*/

    private static SqlSessionFactory buildMybatisSqlSession(MybatisSqlSessionFactoryBean factory, List<String> mapperLocationList, MybatisPlusProperties properties, ApplicationContext applicationContext) throws Exception {
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(properties.getConfigLocation())) {
            factory.setConfigLocation((new PathMatchingResourcePatternResolver()).getResource(properties.getConfigLocation()));
        }

        MybatisConfiguration configuration = buildMybatisConfiguration(properties.getConfiguration());
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        factory.setConfiguration(configuration);
        if (properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(properties.getConfigurationProperties());
        }

        if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        }

        if (StringUtils.hasLength(properties.getTypeEnumsPackage())) {
            factory.setTypeEnumsPackage(properties.getTypeEnumsPackage());
        }

        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(properties.getTypeHandlersPackage());
        }

        factory.setMapperLocations(resolveMapperLocations(mapperLocationList));
        /*GlobalConfiguration globalConfig;
        if (!ObjectUtils.isEmpty(properties.getGlobalConfig())) {
            globalConfig = properties.getGlobalConfig().convertGlobalConfiguration();
        } else {
            globalConfig = new GlobalConfiguration();
        }

        if (applicationContext.getBeanNamesForType(MetaObjectHandler.class, false, false).length > 0) {
            MetaObjectHandler metaObjectHandler = (MetaObjectHandler)applicationContext.getBean(MetaObjectHandler.class);
            globalConfig.setMetaObjectHandler(metaObjectHandler);
        }

        if (applicationContext.getBeanNamesForType(IKeyGenerator.class, false, false).length > 0) {
            IKeyGenerator keyGenerator = (IKeyGenerator)applicationContext.getBean(IKeyGenerator.class);
            globalConfig.setKeyGenerator(keyGenerator);
        }

        if (applicationContext.getBeanNamesForType(ISqlInjector.class, false, false).length > 0) {
            ISqlInjector iSqlInjector = (ISqlInjector)applicationContext.getBean(ISqlInjector.class);
            globalConfig.setSqlInjector(iSqlInjector);
        }

        factory.setGlobalConfig(globalConfig);*/
        //factory.setPlugins(new Interceptor[]{paginationInterceptor()});
        return factory.getObject();
    }

    private static Resource[] resolveMapperLocations(List<String> mapperLocations) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList();
        if (mapperLocations != null) {
            Iterator iterator = mapperLocations.iterator();

            while(iterator.hasNext()) {
                String mapperLocation = (String)iterator.next();

                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException ex) {
                    log.warn(ex.getMessage());
                }
            }
        }

        return (Resource[])resources.toArray(new Resource[resources.size()]);
    }

  /*  private static PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }*/

    private static MybatisConfiguration buildMybatisConfiguration(MybatisConfiguration old) {
        MybatisConfiguration configuration = new MybatisConfiguration();
        if (null == old) {
            return configuration;
        } else {
            configuration.setLogPrefix(old.getLogPrefix());
            configuration.setLogImpl(old.getLogImpl());
            configuration.setVfsImpl(old.getVfsImpl());
            configuration.setCallSettersOnNulls(old.isCallSettersOnNulls());
            configuration.setUseActualParamName(old.isUseActualParamName());
            configuration.setReturnInstanceForEmptyRow(old.isReturnInstanceForEmptyRow());
            configuration.setDatabaseId(old.getDatabaseId());
            configuration.setConfigurationFactory(old.getConfigurationFactory());
            configuration.setSafeResultHandlerEnabled(old.isSafeResultHandlerEnabled());
            configuration.setSafeRowBoundsEnabled(old.isSafeRowBoundsEnabled());
            configuration.setMapUnderscoreToCamelCase(old.isMapUnderscoreToCamelCase());
            configuration.setEnvironment(old.getEnvironment());
            configuration.setAutoMappingBehavior(old.getAutoMappingBehavior());
            configuration.setAutoMappingUnknownColumnBehavior(old.getAutoMappingUnknownColumnBehavior());
            configuration.setLazyLoadingEnabled(old.isLazyLoadingEnabled());
            configuration.setProxyFactory(old.getProxyFactory());
            configuration.setAggressiveLazyLoading(old.isAggressiveLazyLoading());
            configuration.setMultipleResultSetsEnabled(old.isMultipleResultSetsEnabled());
            configuration.setLazyLoadTriggerMethods(old.getLazyLoadTriggerMethods());
            configuration.setUseGeneratedKeys(old.isUseGeneratedKeys());
            configuration.setDefaultExecutorType(old.getDefaultExecutorType());
            configuration.setCacheEnabled(old.isCacheEnabled());
            configuration.setDefaultStatementTimeout(old.getDefaultStatementTimeout());
            configuration.setDefaultFetchSize(old.getDefaultFetchSize());
            configuration.setUseColumnLabel(old.isUseColumnLabel());
            configuration.setLocalCacheScope(old.getLocalCacheScope());
            configuration.setJdbcTypeForNull(old.getJdbcTypeForNull());
            configuration.setVariables(old.getVariables());
            configuration.setReflectorFactory(old.getReflectorFactory());
            configuration.setObjectFactory(old.getObjectFactory());
            configuration.setObjectWrapperFactory(old.getObjectWrapperFactory());
            return configuration;
        }
    }

    public static WallFilter buildWallFilter(WallConfig wallConfig, boolean wallLogViolation, boolean wallThrowException) {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setLogViolation(wallLogViolation);
        wallFilter.setThrowException(wallThrowException);
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }

    public static StatFilter buildStatFilter(DruidDataSourceProperties druidDataSourceProperties) {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(druidDataSourceProperties.isLogSlowSql());
        statFilter.setMergeSql(druidDataSourceProperties.isMergeSql());
        statFilter.setSlowSqlMillis((long)druidDataSourceProperties.getSlowSqlMillis());
        return statFilter;
    }
}