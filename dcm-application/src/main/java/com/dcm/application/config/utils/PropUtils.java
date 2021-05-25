package com.dcm.application.config.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.boot.context.properties.bind.handler.IgnoreTopLevelConverterNotFoundBindHandler;
import org.springframework.boot.context.properties.bind.validation.ValidationBindHandler;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;
import org.springframework.validation.Validator;

public class PropUtils {
    private ApplicationContext applicationContext;
    private volatile Binder binder;
    private PropertySources propertySources;

    public PropUtils(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.propertySources = this.getPropertySources();
    }

    public List<Validator> getValidators(Bindable<?> target) {
        List<Validator> validators = new ArrayList(1);
        if (target.getValue() != null && target.getValue().get() instanceof Validator) {
            validators.add((Validator)target.getValue().get());
        }

        return validators;
    }

    public BindHandler getBindHandler(List<Validator> validators) {
        BindHandler handler = new IgnoreTopLevelConverterNotFoundBindHandler();
        if (!validators.isEmpty()) {
            handler = new ValidationBindHandler((BindHandler)handler, (Validator[])validators.toArray(new Validator[0]));
        }

        return (BindHandler)handler;
    }

    public Binder getBinder() {
        if (this.binder == null) {
            this.binder = new Binder(this.getConfigurationPropertySources(), this.getPropertySourcesPlaceholdersResolver(), this.getConversionService(), this.getPropertyEditorInitializer());
        }

        return this.binder;
    }

    private Iterable<ConfigurationPropertySource> getConfigurationPropertySources() {
        return ConfigurationPropertySources.from(this.propertySources);
    }

    private PropertySourcesPlaceholdersResolver getPropertySourcesPlaceholdersResolver() {
        return new PropertySourcesPlaceholdersResolver(this.propertySources);
    }

    private Consumer<PropertyEditorRegistry> getPropertyEditorInitializer() {
        if (this.applicationContext instanceof ConfigurableApplicationContext) {
            ConfigurableListableBeanFactory var10000 = ((ConfigurableApplicationContext)this.applicationContext).getBeanFactory();
            return var10000::copyRegisteredEditorsTo;
        } else {
            return null;
        }
    }

    private PropertySources getPropertySources() {
        PropertySourcesPlaceholderConfigurer configurer = this.getSinglePropertySourcesPlaceholderConfigurer();
        if (configurer != null) {
            return configurer.getAppliedPropertySources();
        } else {
            MutablePropertySources sources = this.extractEnvironmentPropertySources();
            if (sources != null) {
                return sources;
            } else {
                throw new IllegalStateException("Unable to obtain PropertySources from PropertySourcesPlaceholderConfigurer or Environment");
            }
        }
    }

    private PropertySourcesPlaceholderConfigurer getSinglePropertySourcesPlaceholderConfigurer() {
        Map<String, PropertySourcesPlaceholderConfigurer> beans = this.applicationContext.getBeansOfType(PropertySourcesPlaceholderConfigurer.class, false, false);
        return beans.size() == 1 ? (PropertySourcesPlaceholderConfigurer)beans.values().iterator().next() : null;
    }

    private MutablePropertySources extractEnvironmentPropertySources() {
        Environment environment = this.applicationContext.getEnvironment();
        return environment instanceof ConfigurableEnvironment ? ((ConfigurableEnvironment)environment).getPropertySources() : null;
    }

    private ConversionService getConversionService() {
        try {
            return (ConversionService)this.applicationContext.getBean("conversionService", ConversionService.class);
        } catch (NoSuchBeanDefinitionException var2) {
            return ((PropUtils.Factory)this.applicationContext.getAutowireCapableBeanFactory().createBean(PropUtils.Factory.class)).create();
        }
    }

    private static class Factory {
        private List<Converter<?, ?>> converters = Collections.emptyList();
        private List<GenericConverter> genericConverters = Collections.emptyList();

        private Factory() {
        }

        @Autowired(
            required = false
        )
        @ConfigurationPropertiesBinding
        public void setConverters(List<Converter<?, ?>> converters) {
            this.converters = converters;
        }

        @Autowired(
            required = false
        )
        @ConfigurationPropertiesBinding
        public void setGenericConverters(List<GenericConverter> converters) {
            this.genericConverters = converters;
        }

        public ConversionService create() {
            if (this.converters.isEmpty() && this.genericConverters.isEmpty()) {
                return ApplicationConversionService.getSharedInstance();
            } else {
                ApplicationConversionService conversionService = new ApplicationConversionService();
                Iterator iterator = this.converters.iterator();

                while(iterator.hasNext()) {
                    Converter<?, ?> converter = (Converter)iterator.next();
                    conversionService.addConverter(converter);
                }

                iterator = this.genericConverters.iterator();

                while(iterator.hasNext()) {
                    GenericConverter genericConverter = (GenericConverter)iterator.next();
                    conversionService.addConverter(genericConverter);
                }

                return conversionService;
            }
        }
    }
}
