package org.debugroom.mynavi.sample.continuous.integration.bff.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

import org.debugroom.mynavi.sample.continuous.integration.bff.domain.ServiceProperties;

@ComponentScan("org.debugroom.mynavi.sample.continuous.integration.bff.domain")
@Configuration
public class DomainConfig {

    @Autowired
	ServiceProperties properties;

	@Bean
	public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.rootUri(properties.getDns()).build();
	}

}
