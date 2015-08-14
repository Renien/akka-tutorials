package com.tutorial.actor

import akka.actor.Actor

/**
 * Created by renienj on 8/14/15.
 */
class Son extends Actor{
  override def receive: Receive = {
    case "Woke up" => {
      println("Son : Hi Mom")
      context.actorSelection("/user/mother") ! "Hi Mom"
    }
  }
}
