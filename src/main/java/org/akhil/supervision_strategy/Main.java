package org.akhil.supervision_strategy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Akka System starts\n");
		ActorSystem system = ActorSystem.create("StrategySystem");
		ActorRef supervisor = system.actorOf(Props.create(Supervisor.class), "Supervisor");
		/*supervisor.tell(Integer.valueOf(8), null);
		Thread.sleep(2000);
		System.out.println("\nSent : " + 8);
		supervisor.tell("", null);		
		supervisor.tell(Integer.valueOf(0), null);
		Thread.sleep(2000);
		System.out.println("\nSent : " + 0);
		//Integer result = (Integer) Await.result(Patterns.ask(supervisor, "", 5000), Duration.create(5000, TimeUnit.MILLISECONDS));
		supervisor.tell("", null);
		supervisor.tell(Integer.valueOf(4), null);
		Thread.sleep(2000);
		System.out.println("\nSent : " + 4);
		supervisor.tell("", null);
		Thread.sleep(2000);*/
		Thread.sleep(2000);
		System.out.println("Sent 2.0F");
		supervisor.tell(new Float(2.0f), null);
		Thread.sleep(2000);
//		System.out.println("\nSent : " + null);
		//supervisor.tell("", null);		
	}
}
