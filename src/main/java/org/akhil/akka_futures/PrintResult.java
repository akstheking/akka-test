package org.akhil.akka_futures;

import akka.dispatch.OnSuccess;

public class PrintResult<T> extends OnSuccess<T> {
	boolean error = false;
	
	public PrintResult() {
	}
	
	public PrintResult(boolean error) {
		this.error = error;
	}
	
	@Override
	public final void onSuccess(T t) {
		System.out.println(t);
	}
}

