package org.akhil.akka_futures;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;

public class AkkaFutureTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		ExecutionContext ec =
				ExecutionContexts.fromExecutorService(executorService);
		Promise<String> promise = Futures.promise();
		Future<String> f = promise.future();
		//Thread.sleep(5000);
		//promise.success("hello");
		promise.failure(new IllegalArgumentException());
		f.onSuccess(new PrintResult<String>(), ec);
		f.onFailure(new PrintFailure(), ec);
		executorService.shutdown();
	}
	
	public final static class PrintFailure extends OnFailure {
		boolean error = false;

		@Override
		public void onFailure(Throwable arg0) throws Throwable {
			System.out.println("Failure ecnountered");
			arg0.printStackTrace();
			
		}
		
		
	}

	public final static class PrintResult<T> extends OnSuccess<T> {
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

}
