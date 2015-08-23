package com.tutorial.actorSystem

import akka.actor.Actor

/**
 * Created by renienj on 8/17/15.
 */
class Mother extends Actor{

  import FamilyMemberMessage._

  override def receive: Receive = {
    case Greeting(messsage) => {
      println(s"Mother got the following message : $messsage")
    }
  }
}