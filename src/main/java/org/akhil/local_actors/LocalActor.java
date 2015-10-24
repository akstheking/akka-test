package org.akhil.local_actors;


import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;



public class LocalActor extends UntypedActor {
	
	ActorRef remoteActor;
	ActorSelection remoteSelection;
	Timeout timeout = new Timeout((FiniteDuration) Duration.create("5 seconds"));
	
	public void preStart() {
		remoteSelection = getContext().actorSelection("akka.tcp://RemoteSys@127.0.0.1:2552/user/RemoteActor");
		//remoteSelection = getContext().actorSelection("akka.tcp://RemoteSys@127.0.1.1:2552");
		//remoteActor = getContext().actorFor("akka.tcp://RemoteSys@127.0.0.1:2552/user/RemoteActor");
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		Future<Object> future = Patterns.ask(remoteSelection, arg0.toString(), timeout);
		String result = (String) Await.result(future, timeout.duration());
		System.out.println("Message received from server -> " + result);
	}

}
