package org.akhil.supervision_strategy;

import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;

public class Worker extends UntypedActor{
	
	private int state = 0;

	@Override
	public void postStop() throws Exception {
		System.out.println("Stopping worker instance #" + getSelf());
	}

	@Override
	public void preStart() throws Exception {
		System.out.println("Starting worker instance #" + getSelf());
	}

	@Override
	public SupervisorStrategy supervisorStrategy() {
		// TODO Auto-generated method stub
		return super.supervisorStrategy();
	}

	@Override
	public void onReceive(Object o) throws Exception {
		System.out.println("received : " + o);
		if(o instanceof Float) {
			throw new NullPointerException("Null message passed");
		} else if(o instanceof Integer) {
			Integer value  = (Integer) o;
			if(value <= 0) {
				throw new ArithmeticException("Integer passed is less than or equal to zero");
			}
			state = value;			
		} else if(o instanceof String) {
			System.out.println(getSelf() + " state : " + state);
		} else {
			throw new IllegalArgumentException("Invalid message passed");
		}
		
	}

}
