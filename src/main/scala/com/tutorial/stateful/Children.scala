package com.tutorial.stateful

import akka.actor.Actor
import com.tutorial.stateful.FamilyMemberMessage.Conversation

/**
 * Created by renienj on 8/30/15.
 */
//Son actor
class Son extends Actor{

  val Mom1 = FamilyMemberMessage.message("Mom1")
  val Mom2 = FamilyMemberMessage.message("Mom2")
  val Mom3 = FamilyMemberMessage.message("Mom3")
  val Mom4 = FamilyMemberMessage.message("Mom4")
  val Son1 = FamilyMemberMessage.message("Son1")

  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    //context.become(receive)
  }

  override def receive: Receive = {
    case Conversation(message) => {
      message match {
        case Son1 => {
          context.parent ! Conversation(message)
        }
        case Mom1 => {
          println(s"${sender().path.name} : " + message)
          sender() ! Conversation(FamilyMemberMessage.message("Son2"))
        }
        case Mom2 => {
          println(s"${sender().path.name} : " + message)
          context.become(sadConversation)
          sender() ! Conversation(FamilyMemberMessage.message("Son3"))
        }
      }
    }
  }

  def sadConversation: Receive = {
    case Conversation(message) => {
      message match
      {
        case Mom3 => {
          println(s"${sender().path.name} : " + message)
          sender() ! Conversation(FamilyMemberMessage.message("Son4"))
        }
        case Mom4 => {
          println(s"${sender().path.name} : " + message)
          println("Conversation Ends")
        }
      }

    }
  }


}

//Daughter Actor
class Daughter extends Actor{

  override def receive: Receive = {
    case _ =>
  }
}