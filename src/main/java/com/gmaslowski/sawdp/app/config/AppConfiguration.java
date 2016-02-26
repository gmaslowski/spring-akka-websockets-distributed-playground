package com.gmaslowski.sawdp.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.gmaslowski.sawdp")
@Import({
        AkkaConfiguration.class,
        StompConfiguration.class
})
public class AppConfiguration {
}
