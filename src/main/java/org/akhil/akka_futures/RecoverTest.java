package org.akhil.akka_futures;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.Recover;

public class RecoverTest {

	static ExecutorService es = Executors.newFixedThreadPool(2);
	static ExecutionContext ec = ExecutionContexts.fromExecutorService(es);

	public static void main(String[] args) throws InterruptedException {

		Future<Integer> f1 = Futures.failed(
				new ArithmeticException("Oooo Math Error")).recover(
				new Recover<Integer>() {

					@Override
					public Integer recover(Throwable arg0) throws Throwable {
						if (arg0 instanceof ArithmeticException) {
							return -5;
						} else {
							throw arg0;
						}
					}

				}, ec);

		f1.onSuccess(new PrintResult<Integer>(), ec);
		f1.onFailure(new PrintFailure(), ec);
		Thread.sleep(2000);
		es.shutdown();

	}

}
