package com.gmaslowski.sawdp.processing

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.gmaslowski.sawdp.processing.NotifyingActor.ProcessingStatus
import com.gmaslowski.sawdp.processing.ProcessingActor.{ProcessMessage, RescheduledMessage}

object ProcessingActor {

  def props(serverId: String) = Props(classOf[ProcessingActor], serverId)

  case class ProcessMessage(responseTo: ActorRef, content: String)
  case class RescheduledMessage(responseTo: ActorRef, content: String)
}

class ProcessingActor(serverId: String) extends Actor with ActorLogging {

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
      actorToNotify ! ProcessingStatus(s"from $serverId, $content")
  }
}
