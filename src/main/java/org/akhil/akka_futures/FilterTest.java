package org.akhil.akka_futures;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.akhil.akka_futures.AkkaFutureTest.PrintFailure;
import org.akhil.akka_futures.AkkaFutureTest.PrintResult;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Filter;
import akka.dispatch.Futures;
import akka.japi.Function;

public class FilterTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		final ExecutionContext ec = ExecutionContexts
				.fromExecutorService(executorService);
		
		Future<Integer> f1 = Futures.successful(4);
		
		Future<Integer> f2 = f1.filter(Filter.filterOf(new Function<Integer, Boolean>() {

			public Boolean apply(Integer t) {
				return t%2 == 0;
			}
			
		}), ec);
		
		Future<Integer> f3 = f1.filter(Filter.filterOf(new Function<Integer, Boolean>() {

			public Boolean apply(Integer t) {
				return t%2 != 0;
			}
			
		}), ec);
		
		System.out.println("f2 success");
		f2.onSuccess(new PrintResult<Integer>(), ec);
		Thread.sleep(2000);
		System.out.println("f2 failure");
		f2.onFailure(new PrintFailure(), ec);
		Thread.sleep(2000);
		System.out.println("f3 success");
		f3.onSuccess(new PrintResult<Integer>(), ec);
		Thread.sleep(2000);
		System.out.println("f3 failure");
		f3.onFailure(new PrintFailure(), ec);
		
		executorService.shutdown();
	}

}
