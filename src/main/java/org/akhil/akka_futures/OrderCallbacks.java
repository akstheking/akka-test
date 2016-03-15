package org.akhil.akka_futures;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;

public class OrderCallbacks {

	static ExecutorService es = Executors.newFixedThreadPool(2);
	static ExecutionContext ec = ExecutionContexts.fromExecutorService(es);

	public static void main(String[] args) throws InterruptedException {

		Future<String> f1 = Futures.successful("Ariana Grande")
				.andThen(new OnComplete<String>() {

					@Override
					public void onComplete(Throwable arg0, String arg1)
							throws Throwable {
						System.out.println("On complete first");
					}
				}, ec).andThen(new OnComplete<String>() {

					@Override
					public void onComplete(Throwable arg0, String arg1)
							throws Throwable {
						System.out.println("On complete second");
					}
				}, ec);

		Thread.sleep(2000);
		es.shutdown();
	}

}
