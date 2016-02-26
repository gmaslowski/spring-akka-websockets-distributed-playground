package com.gmaslowski.sawdp.processing

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.gmaslowski.sawdp.processing.ProcessingActor.ProcessMessage
import com.gmaslowski.sawdp.processing.SchedulingActor.ScheduleProcessing

object SchedulingActor {

  def props(processingActor: ActorRef, notifyingActor: ActorRef) = Props(classOf[SchedulingActor], processingActor, notifyingActor)

  case class ScheduleProcessing(content: String)
}

class SchedulingActor(processingActor: ActorRef, notifyingActor: ActorRef) extends Actor with ActorLogging {

  override def receive = {

    case ScheduleProcessing(content) =>
      processingActor ! new ProcessMessage(notifyingActor, content)
  }
}