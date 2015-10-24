package org.akhil.supervision_strategy;


import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.stop;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;

public class Supervisor extends UntypedActor {
	
	private ActorRef worker1;
	private ActorRef worker2;
	
	public Supervisor() {
		worker1 = getContext().actorOf(Props.create(Worker.class), "worker1");
		worker2 = getContext().actorOf(Props.create(Worker.class), "worker2");
		
	}

	private static SupervisorStrategy strategy = new OneForOneStrategy(5,
			Duration.create("5 second"), new Function<Throwable, Directive>() {

				public Directive apply(Throwable t) {
					if(t instanceof NullPointerException) {
						return restart();
					} else if(t instanceof IllegalArgumentException) {
						return stop();
					} else if (t instanceof ArithmeticException) {
						return resume();
					} else {
						return escalate();
					}
				}

			});
	
	

	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}



	@Override
	public void onReceive(Object o) throws Exception {
		if(o instanceof ActorRef) {
			ActorRef monitor = (ActorRef) o;
			monitor.tell(worker1, getSelf());
		} else {
			getContext().stop(worker1);
			//worker1.tell(o, getSelf());
			//worker2.tell(o, getSelf());
		}
		/*if(o instanceof String) {
			worker1.tell(o, getSelf());
			worker2.tell(o, getSelf());
		} else {
			worker1.tell(o, getSelf());
			worker2.tell(o, getSelf());
		}*/

	}

}
