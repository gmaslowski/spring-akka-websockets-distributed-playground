package com.gmaslowski.sawdp.processing;

import akka.actor.ActorRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

import static akka.actor.ActorRef.noSender;

@Controller
public class ProcessingController {

    private final ActorRef schedulingActor;

    @Autowired
    public ProcessingController(ActorRef schedulingActor) {
        this.schedulingActor = schedulingActor;
    }

    @MessageMapping("/process")
    @SendTo("/topic/processing")
    public ProcessingInProgressMessage startProcess(StartProcessingMessage message) throws Exception {
        schedulingActor.tell(new SchedulingActor.ScheduleProcessing(message.getContent()), noSender());
        return new ProcessingInProgressMessage(UUID.randomUUID().toString());
    }
}
