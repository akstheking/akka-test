package org.akhil.akka_futures;

import java.util.concurrent.Callable;

public class LengthFuture implements Callable<Integer> {
	
	String s;
	
	public LengthFuture(String s) {
		this.s = s;
	}

	public Integer call() throws Exception {
		return s.length();
	}

}
