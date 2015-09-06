package com.tutorial.stateful

import akka.actor.SupervisorStrategy.{Escalate, Restart, Stop}
import akka.actor._
import com.tutorial.stateful.FamilyMemberMessage.Conversation
import scala.collection.JavaConverters._

/**
 * Created by renienj on 8/30/15.
 */
class Mother extends Actor{

  override final val supervisorStrategy = OneForOneStrategy(){
    case _: ActorInitializationException  => Stop
    case _: ActorKilledException => Stop
    case _: Exception => Restart
    case _ => Escalate
  }

  val Son1 = FamilyMemberMessage.message("Son1").toString
  val Son2 = FamilyMemberMessage.message("Son2")
  val Son3 = FamilyMemberMessage.message("Son3")
  val Son4 = FamilyMemberMessage.message("Son4")

  @scala.throws[Exception](classOf[Exception])
  override def preStart() = {
    //Sons are under Mother
    val numberOfSons: Int = 2
    val sonsNames = context.system.settings.config.getStringList(
      "akka.apartment.familyMembers.sonsNames").asScala
    sonsNames take numberOfSons foreach { sName =>
      context.actorOf(Props[Son], sName)
    }
    context.become(receive)
  }

  override def receive: Receive = {
    case Conversation(message) => {
      message match
      {
        case Son1 => {
          println(s"${sender().path.name} : " + message)
          sender() ! Conversation(FamilyMemberMessage.message("Mom1"))
        }
        case Son2 => {
          println(s"${sender().path.name} : " + message)
          context.become(angryConversation)
          sender() ! Conversation(FamilyMemberMessage.message("Mom2")) }
      }

    }
  }

  def angryConversation: Receive = {
    case Conversation(message) => {
      message match
      {
        case Son3 => {
          println(s"${sender().path.name} : " + message)
          sender() ! Conversation(FamilyMemberMessage.message("Mom3"))
        }
        case Son4 => {
          println(s"${sender().path.name} : " + message)
          sender() ! Conversation(FamilyMemberMessage.message("Mom4"))
        }
      }

    }
  }
}