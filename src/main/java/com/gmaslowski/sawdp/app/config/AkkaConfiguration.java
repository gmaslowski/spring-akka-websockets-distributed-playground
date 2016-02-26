package com.gmaslowski.sawdp.app.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.gmaslowski.sawdp.processing.NotifyingActor;
import com.gmaslowski.sawdp.processing.ProcessingActor;
import com.gmaslowski.sawdp.processing.SchedulingActor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class AkkaConfiguration {

    @Bean
    ActorSystem actorSystem() {
        return ActorSystem.create("sawdp-processing");
    }

    @Bean
    ActorRef processingActor(@Value("${serverId}") String serverId, ActorSystem actorSystem) {
        return actorSystem.actorOf(ProcessingActor.props(serverId));
    }

    @Bean
    ActorRef notifyingActor(ActorSystem actorSystem, SimpMessagingTemplate simpMessagingTemplate) {
        return actorSystem.actorOf(NotifyingActor.props(simpMessagingTemplate));
    }

    @Bean
    ActorRef schedulingActor(ActorSystem actorSystem, ActorRef processingActor, ActorRef notifyingActor) {
        return actorSystem.actorOf(SchedulingActor.props(processingActor, notifyingActor));
    }
}
