package com.tutorial.supervisor

import akka.actor.Actor
import com.tutorial.supervisor.InjuredMessage.{Cured, Injured}

/**
 * Created by renienj on 8/28/15.
 */
//Son actor
class Son extends Actor{

  @scala.throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    context.parent ! Cured("I'm Fine Mom!!!!!!")
  }

  override def receive: Receive = {
    case Injured(message) =>
  }
}

//Daughter Actor
class Daughter extends Actor{

  @scala.throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    context.parent ! Cured(self.path.name +": I'm Fine Dad!!!!!!")
  }

  override def receive: Receive = {
    case Injured(message) => throw new Exception
  }
}