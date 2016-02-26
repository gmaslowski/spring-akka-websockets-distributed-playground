package com.gmaslowski.sawdp.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AkkaConfiguration.class})
public class AppConfiguration {
}
