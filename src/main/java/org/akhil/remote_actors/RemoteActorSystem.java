package org.akhil.remote_actors;

import akka.actor.ActorSystem;

import com.typesafe.config.ConfigFactory;

public class RemoteActorSystem /*implements Bootable*/{
	
	final ActorSystem system = ActorSystem.create("RemoteSys",ConfigFactory.load().getConfig("RemoteSys"));
/*
	@Override
	public void shutdown() {
		System.out.println("Shutdown the remote actor systen");
		system.terminate();
		
	}

	@Override
	public void startup() {
		System.out.println("Starting the REmote aCtor system");
		system.actorOf(Props.create(RemoteActor.class), "RemoteActor");
	}
*/
		

}
