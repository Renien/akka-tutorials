package com.tutorial.actorSystem

import akka.actor.Actor


/**
 * Created by renienj on 8/23/15.
 */
class Father extends Actor{

  import FamilyMemberMessage._

  override def receive: Receive = {
    case Greeting(messsage) => {
      println(s"Father got the following message : $messsage")
    }
  }
}