package org.akhil.akka_futures;

import akka.dispatch.OnFailure;

public class PrintFailure extends OnFailure {
	boolean error = false;

	@Override
	public void onFailure(Throwable arg0) throws Throwable {
		System.out.println("Failure ecnountered");
		arg0.printStackTrace();
		
	}
	
	
}
