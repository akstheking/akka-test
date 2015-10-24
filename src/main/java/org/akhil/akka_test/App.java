package org.akhil.akka_test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Akka System starts\n");
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef act = system.actorOf(Props.create(TestActor.class), "testtt");
		act.tell(new Message("If you smellll...."), null);
		Thread.sleep(3000);
		act.tell(new Message("become-something"), null);	
		Thread.sleep(3000);
		act.tell(new Message("see what you have become "), null);
		Thread.sleep(3000);
		act.tell(new Message("come back"), null);
		Thread.sleep(3000);
		act.tell(new Message("see what you have become "), null);
	}
}
