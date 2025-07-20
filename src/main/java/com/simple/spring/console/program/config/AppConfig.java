package com.simple.spring.console.program.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.simple.spring.console.program")
@PropertySource("classpath:app-properties.yml")
public class AppConfig {
}
