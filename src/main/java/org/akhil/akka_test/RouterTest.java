package org.akhil.akka_test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class RouterTest {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Akka System starts\n");
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef act = system.actorOf(Props.create(TestActor.class).withRouter(new MyRouter(5, 3)));
		act.tell(new Message("If you smellll...."), null);
		Thread.sleep(3000);
	}

}
