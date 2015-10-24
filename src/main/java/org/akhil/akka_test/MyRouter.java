package org.akhil.akka_test;

import akka.actor.ActorSystem;
import akka.routing.CustomRouterConfig;
import akka.routing.Router;

public class MyRouter extends CustomRouterConfig{
	
	int numberOfInstance = 0;
	int messageBurst = 0;
	
	public MyRouter(int numberOfInstance, int messageBurst) {
		this.numberOfInstance = numberOfInstance;
		this.messageBurst = messageBurst;
	}

	public Router createRouter(ActorSystem arg0) {
		return null;
		
	}

}
