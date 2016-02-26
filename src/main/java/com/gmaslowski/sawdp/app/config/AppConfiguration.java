package com.gmaslowski.sawdp.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.gmaslowski.sawdp")
@Import({
        AkkaConfiguration.class,
        StompConfiguration.class
})
@PropertySource("classpath:app.properties")
public class AppConfiguration {
}
