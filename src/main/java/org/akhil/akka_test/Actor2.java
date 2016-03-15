package org.akhil.akka_test;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;


public class Actor2 extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Exception {
		
		if(message instanceof Message) {
			ActorRef sender = getSender();
			System.out.println("Actor 2 receives : "+message + " from "+ sender.toString() + "\n");
			getSender().tell("Think if you know me", null);
		}
	}
	

}
