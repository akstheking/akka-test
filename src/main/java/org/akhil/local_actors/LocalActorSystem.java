package org.akhil.local_actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.typesafe.config.ConfigFactory;


public class LocalActorSystem {

	public static void main(String[] args) throws InterruptedException {
		ActorSystem system = ActorSystem.create("LocalActorStystem", ConfigFactory.load().getConfig("LocalSys"));
		ActorRef localActor = system.actorOf(Props.create(LocalActor.class));
		localActor.tell("This is remote testing", null);
		Thread.sleep(5000);
		system.terminate();
	}
}
