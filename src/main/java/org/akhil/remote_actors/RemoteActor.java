package org.akhil.remote_actors;

import akka.actor.UntypedActor;

public class RemoteActor extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("Recived : " + message.toString());
		if(message instanceof String) {
			getSender().tell(message + " got something.", getSelf());
		}
		
	}
	
	

}
