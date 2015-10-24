package org.akhil.akka_test;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Procedure;

public class TestActor extends UntypedActor {

	private String name;

	public TestActor(String name) {
		this.name = name;
	}

	public TestActor() {
	}

	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof Message) {
			System.out.println("I am normal and I received : " + message + "\n");
			ActorRef receiver = getContext()
					.actorOf(Props.create(Actor2.class));
			System.out.println(" I Sent it to : " + receiver + "\n");
			receiver.tell(message, getSelf());
			if (((Message) message).getWho().equals("become-something")) {
				System.out.println("I am changing myself\n");
				getContext().become(new Procedure<Object>() {

					public void apply(Object message) throws Exception {
						if (message instanceof Message) {
							System.out
									.println("I have become someone else. I have become something else.\n");
							if(((Message) message).getWho().equals("come back")) {
								System.out.println("Trying to be normal guy\n");
								getContext().unbecome();
							}
						}
					}

				});
			}
		} else if (message instanceof String) {
			System.out.println("received " + message + " from " + getSender()
					+ "\n");
		}
	}
}
