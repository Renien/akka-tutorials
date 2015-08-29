package com.tutorial.supervisor

import akka.actor.{Kill, ActorSystem, Props}

/**
 * Created by renienj on 8/28/15.
 */
object TestSupervisor extends App {

  val system = ActorSystem("family")
  val familyMembers = system.actorOf(Props[FamilyMember], "familyMembers")

  //lets use system.actorSelect to access directly the son and daughter
  val sally  = system.actorSelection("akka://family/user/familyMembers/John/Sally") //Daughter: Sally
  val roy = system.actorSelection("akka://family/user/familyMembers/Mary/Roy") //Son: Roy
  roy ! Kill                              //Kill the son
  sally ! InjuredMessage.Injured("Crash") //Crash the daughter
  Thread.sleep(500) //bad code
  system.shutdown()

}
