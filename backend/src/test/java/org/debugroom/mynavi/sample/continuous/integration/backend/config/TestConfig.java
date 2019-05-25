package org.debugroom.mynavi.sample.continuous.integration.backend.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;

public class TestConfig {

    @Configuration
    public static class UnitTestConfig{
        @Bean
        public MessageSource messageSource() {
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource(){
                @Override
                public void setResourceLoader(ResourceLoader resourceLoader) {
                }
            };
            messageSource.setBasename("messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }
    }

    @SpringBootApplication
    @EnableConfigurationProperties
    public static class ServiceTestConfig{
    }

    @SpringBootApplication
    public static class ControllerTestConfig{
    }

}
