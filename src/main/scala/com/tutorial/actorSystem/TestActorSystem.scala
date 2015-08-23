package com.tutorial.actorSystem

import akka.actor.{ActorSystem, Props}
import com.tutorial.actorSystem.FamilyMemberMessage.Greeting


/**
 * Created by renienj on 8/17/15.
 */

object TestActorSystem extends App{
  val system = ActorSystem("family")
  val familyMembers = system.actorOf(Props[FamilyMember], "familyMembers")
  familyMembers ! Greeting("Mary")
  familyMembers ! Greeting("John")
  Thread.sleep(500) //bad code
  system.shutdown()
}
