package com.tutorial.supervisor

import akka.actor.SupervisorStrategy.{Escalate, Restart, Stop}
import akka.actor._
import com.tutorial.supervisor.InjuredMessage.Cured
import scala.collection.JavaConverters._

/**
 * Created by renienj on 8/28/15.
 */
class Father extends Actor{

  override final val supervisorStrategy = OneForOneStrategy(){
    case _: ActorInitializationException  => Stop
    case _: ActorKilledException => Stop
    case _: Exception => Restart
    case _ => Escalate
  }

  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    //Daughters are under father
    val numberOfDaughters: Int = 2
    val daughtersNames = context.system.settings.config.getStringList(
      "akka.apartment.familyMembers.daughtersNames").asScala
    daughtersNames take numberOfDaughters foreach { dName =>
      context.actorOf(Props[Daughter], dName)
    }
  }

  override def receive: Receive = {
    case Cured(message) => println(message)
  }
}