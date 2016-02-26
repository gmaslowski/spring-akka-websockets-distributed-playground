package com.gmaslowski.sawdp.app.config;

import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfiguration {

    @Bean
    ActorSystem actorSystem() {
        return ActorSystem.create("sawdp-processing");
    }

}
