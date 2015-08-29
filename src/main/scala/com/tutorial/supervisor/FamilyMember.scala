package com.tutorial.supervisor

import akka.actor.{Actor, Props}

/**
 * Created by renienj on 8/28/15.
 */
class FamilyMember extends Actor {


  @scala.throws[Exception](classOf[Exception])
  override def preStart() = {
    /**
     * Create the family members
     * Mother
     * Father
     * Some basic configurations handled in release.conf file
     */
    context.actorOf(Props[Mother], context.system.settings.config.getString(
      "akka.apartment.familyMembers.motherName"))

    context.actorOf(Props[Father], context.system.settings.config.getString(
      "akka.apartment.familyMembers.fatherName"))
  }

  override def receive: Receive = {
    case _ =>
  }
}

//injury messages
object InjuredMessage {
  case class Injured(cause: String)
  case class Cured(cause: String)
}