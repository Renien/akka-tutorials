package com.tutorial.supervisor

import akka.actor.SupervisorStrategy.{Escalate, Restart, Stop}
import akka.actor._
import com.tutorial.supervisor.InjuredMessage.Cured
import scala.collection.JavaConverters._



/**
 * Created by renienj on 8/28/15.
 */
class Mother extends Actor{

  override final val supervisorStrategy = OneForOneStrategy(){
    case _: ActorInitializationException  => Stop
    case _: ActorKilledException => Stop
    case _: Exception => Restart
    case _ => Escalate
  }

  @scala.throws[Exception](classOf[Exception])
  override def preStart() = {
    //Sons are under Mother
    val numberOfSons: Int = 2
    val sonsNames = context.system.settings.config.getStringList(
      "akka.apartment.familyMembers.sonsNames").asScala
    sonsNames take numberOfSons foreach { sName =>
      context.actorOf(Props[Son], sName)
    }
  }

  override def receive: Receive = {
    case Cured(message) => println(message)
  }
}