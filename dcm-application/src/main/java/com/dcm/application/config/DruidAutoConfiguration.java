package com.dcm.application.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidAutoConfiguration {

    public DruidAutoConfiguration() {
    }

    @Bean("druidServlet")
    @ConditionalOnProperty(
        name = {"druid.datasource.enable-monitor"},
        havingValue = "true"
    )
    @ConditionalOnMissingBean(
        name = {"druidServlet"}
    )
    public ServletRegistrationBean registerDruidStatServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), new String[]{"/druid/*"});
    }
}
