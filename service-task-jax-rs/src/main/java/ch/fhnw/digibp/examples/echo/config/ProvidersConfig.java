/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.echo.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProvidersConfig {

	@Bean
	@ConditionalOnMissingBean
	public JacksonJsonProvider jsonProvider() {
		return new JacksonJsonProvider();
	}
}