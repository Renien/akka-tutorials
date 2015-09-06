package com.tutorial.stateful

import akka.actor.{ActorSystem, Props}
import com.tutorial.stateful.FamilyMemberMessage.Conversation

/**
 * Created by renienj on 8/30/15.
 */
object TestState extends App {
  val system = ActorSystem("family")
  val familyMembers = system.actorOf(Props[FamilyMember], "familyMembers")

  //lets use system.actorSelect to access directly the son and daughter
  val roy = system.actorSelection("akka://family/user/familyMembers/Mary/Roy") //Son: Roy
  roy ! Conversation("Mom, I just now came home")
  //roy ! Conversation(FamilyMemberMessage.message("Son1"))
  Thread.sleep(500) //bad code
  system.shutdown()
}
