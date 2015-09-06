package com.tutorial.stateful

import akka.actor.{Props, Actor}

/**
 * Created by renienj on 8/30/15.
 */
//Family messages
object FamilyMemberMessage {
  case class Conversation(message: String)
  val message = Map(
      "Mom1" -> "Roy come lets have our dinner",
      "Mom2" -> "What did you eat ?", //Son's state changes
      "Mom3" -> "What's wrong with you! :@ How many times I have told you not to eat junk food",
      "Mom4" -> "I have told you so many times. Now you're grounded for two weeks (EVIL SMILE)",
      "Son1" -> "Mom, I just now came home",
      "Son2" -> "I'm full, I had my dinner", //Mother's state will change after this message
      "Son3" -> "Sorry mom, I had junk food :(",
      "Son4" -> "I'm very sorry I won't eat again")
}

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
