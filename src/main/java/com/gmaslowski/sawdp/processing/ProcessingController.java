package com.gmaslowski.sawdp.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ProcessingController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessingController.class);

    @MessageMapping("/process")
    @SendTo("/topic/processing")
    public ProcessingInProgressMessage startProcess(StartProcessingMessage message) throws Exception {
        return new ProcessingInProgressMessage(UUID.randomUUID().toString());
    }
}
