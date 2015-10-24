package org.akhil.supervision_strategy;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

public class Monitor extends UntypedActor{
	
	List<ActorRef> watchedActors = new ArrayList<>();
	
	public Monitor() {
		
	}
	
	public Monitor(ActorRef actor) {
		watchedActors.add(actor);
		getContext().watch(actor);
	}
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		
		if(arg0 instanceof Terminated) {
			Terminated t = (Terminated) arg0;
			ActorRef terminated = t.actor();
			System.out.println("Actor Terminated : "+terminated.path());
		} else if(arg0 instanceof ActorRef) {
			ActorRef ac = (ActorRef) arg0;
			getContext().watch(ac);
			watchedActors.add(ac);
			System.out.println("REgistered Actors : " + watchedActors);
		} else {
			System.out.println("Unknown Message");
		}
		
	}
	
	

}
