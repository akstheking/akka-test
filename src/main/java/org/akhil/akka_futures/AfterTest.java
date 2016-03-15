package org.akhil.akka_futures;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.pattern.Patterns;

public class AfterTest {

	static ExecutorService es = Executors.newFixedThreadPool(2);
	static ExecutionContext ec = ExecutionContexts.fromExecutorService(es);
	

	public static void main(String[] args) {
		Future<String> failExc = Futures.failed(new IllegalStateException(
				"OHNOES1"));
//		Future<String> delayed = Patterns.after(Duration.create(200, "millis"),
//				system().scheduler(), ec, failExc);
		Future<String> future = Futures.future(new Callable<String>() {
			public String call() throws InterruptedException {
				Thread.sleep(1000);
				return "foo";
			}
		}, ec);
//		Future<String> result = Futures.firstCompletedOf(
//				Arrays.<Future<String>> asList(future, delayed), ec);
	}

}
