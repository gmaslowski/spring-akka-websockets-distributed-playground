package com.gmaslowski.sawdp.processing

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.gmaslowski.sawdp.processing.ProcessingActor.{ProcessMessage, ProcessingStatus, RescheduledMessage}

object ProcessingActor {

  case class ProcessMessage(responseTo: ActorRef, content: String)
  case class RescheduledMessage(responseTo: ActorRef, content: String)
  case class ProcessingStatus(content: String)

}

class ProcessingActor extends Actor with ActorLogging {

  import scala.concurrent.duration._
  implicit val ec = context.dispatcher

  override def receive = {
    case ProcessMessage(actorToNofity, content) =>
      context.system.scheduler.schedule(
        1 seconds,
        2 seconds,
        self,
        new RescheduledMessage(actorToNofity, content))

    case RescheduledMessage(actorToNotify, content) =>
      actorToNotify ! ProcessingStatus(content)
  }
}
