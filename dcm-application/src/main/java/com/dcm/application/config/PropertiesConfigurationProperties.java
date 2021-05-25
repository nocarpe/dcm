package com.dcm.application.config;
import java.util.Properties;
public class PropertiesConfigurationProperties {
    private Properties props = new Properties();

    public PropertiesConfigurationProperties() {
    }

    public Properties getProps() {
        return this.props;
    }

    public void setProps(final Properties props) {
        this.props = props;
    }
}
