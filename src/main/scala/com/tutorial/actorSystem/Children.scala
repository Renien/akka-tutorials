package com.tutorial.actorSystem

import akka.actor.Actor
import FamilyMemberMessage._

/**
 * Created by renienj on 8/23/15.
 */

//Son actor
class Son extends Actor{

  override def receive: Receive = {
    case Greeting(message) =>
  }
}

//Daughter Actor
class Daughter extends Actor{

  override def receive: Receive = {
    case Greeting(message) =>
  }
}