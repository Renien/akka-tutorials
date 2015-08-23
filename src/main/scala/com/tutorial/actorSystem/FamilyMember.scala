package com.tutorial.actorSystem

import akka.actor.{Props, ActorRef, Actor}
import scala.collection.JavaConverters._

/**
 * Created by renienj on 8/23/15.
 */

//Family messages
object FamilyMemberMessage {
  case class Greeting(message: String)
}

/**
 * Family Member consist of Parents and Children
 *
 * All the family members are created created under Family member actor
 */
class FamilyMember extends Actor {
  import com.tutorial.actorSystem.FamilyMemberMessage._

  var motherName : Option[String] = None
  var fatherName : Option[String] = None
  var fatherActor : Option[ActorRef] = None

  override def preStart = {
    /**
     * Create the family members
     * Mother
     * Father
     * Sons and Daughters
     * Some basic configurations handled in release.conf file
     */
    motherName = Option(context.system.settings.config.getString(
      "akka.apartment.familyMembers.motherName"))
    context.actorOf(Props[Mother], motherName.get)

    fatherName = Option(context.system.settings.config.getString(
      "akka.apartment.familyMembers.fatherName"))
    fatherActor = Option(context.actorOf(Props[Father], fatherName.get))

    val numberOfSons: Int = 2
    val sonsNames = context.system.settings.config.getStringList(
      "akka.apartment.familyMembers.sonsNames").asScala
    sonsNames take numberOfSons foreach { sName =>
      context.actorOf(Props[Son], sName)
    }

    val numberOfDaughters: Int = 2
    val daughtersNames = context.system.settings.config.getStringList(
      "akka.apartment.familyMembers.daughtersNames").asScala
    daughtersNames take numberOfDaughters foreach { dName =>
      context.actorOf(Props[Daughter], dName)
    }
  }

  def receive = {
    case Greeting(message) => {
      message match
      {
        case motherName => context.actorSelection(message) ! Greeting("Good Morning!") //Using actorSelection
        case fatherName => fatherActor.get ! Greeting("Good Morning!") //keep the actorRef object
      }

    }
  }
}
