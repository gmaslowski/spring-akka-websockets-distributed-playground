package com.gmaslowski.sawdp.processing

import java.util.Date

import akka.actor.{Actor, ActorLogging, Props}
import com.gmaslowski.sawdp.processing.NotifyingActor.ProcessingStatus
import org.springframework.messaging.simp.SimpMessagingTemplate

object NotifyingActor {

  def props(simpMessagingTemplate: SimpMessagingTemplate) = Props(classOf[NotifyingActor], simpMessagingTemplate)

  case class ProcessingStatus(content: String)
}

class NotifyingActor(simpMessagingTemplate: SimpMessagingTemplate) extends Actor with ActorLogging {
  override def receive = {

    case ProcessingStatus(content) =>
      simpMessagingTemplate.convertAndSend("/topic/processing", new ProcessingInProgressMessage(content + new Date()))
  }
}
