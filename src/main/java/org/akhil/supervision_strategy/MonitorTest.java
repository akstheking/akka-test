package org.akhil.supervision_strategy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MonitorTest {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Akka System starts\n");
		ActorSystem system = ActorSystem.create("StrategySystem");
		ActorRef supervisor = system.actorOf(Props.create(Supervisor.class), "Supervisor");
		ActorRef monitor = system.actorOf(Props.create(Monitor.class), "Monitor");
		supervisor.tell(monitor, null);
		Thread.sleep(2000);
		supervisor.tell(5, null);
		supervisor.tell("", null);
		supervisor.tell(new Float(2.5F), null);
	}

}
