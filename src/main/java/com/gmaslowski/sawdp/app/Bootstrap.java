package com.gmaslowski.sawdp.app;

import com.gmaslowski.sawdp.app.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
}
