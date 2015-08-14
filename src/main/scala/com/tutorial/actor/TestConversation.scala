package com.tutorial.actor

import akka.actor.{Props, ActorSystem}

/**
 * Created by renienj on 8/14/15.
 */
object TestConversation extends App{

  val system = ActorSystem("family")
  val mother = system.actorOf(Props[Mother], "mother")
  val son = system.actorOf(Props[Son], "son")
  son ! "Woke up"
  Thread.sleep(500)
  system.shutdown()

}
