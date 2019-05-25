package org.debugroom.mynavi.sample.continuous.integration.backend.config;

import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.CommonExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("org.debugroom.mynavi.sample.continuous.integration.backend.app.web")
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    CommonExceptionHandler commonExceptionHandler(){
        return new CommonExceptionHandler();
    }

}
