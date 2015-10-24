package org.akhil.akka_test;

public class Message {
	
	private String who;

	public void setWho(String who) {
		this.who = who;
	}

	public Message(String who) {
		this.who = who;
	}

	public String getWho() {
		return who;
	}

	@Override
	public String toString() {
		return "Message [who=" + who + "]";
	}
	

}
