package com.tutorial.actor

import akka.actor.Actor

/**
 * Created by renienj on 8/14/15.
 */
class Mother extends Actor{
  override def receive: Receive = {
    case "Hi Mom" => {
      println("Mother : Hi Son, Good Morning!..")
    }
  }
}
