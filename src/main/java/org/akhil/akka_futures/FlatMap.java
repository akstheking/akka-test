package org.akhil.akka_futures;

import static akka.dispatch.Futures.future;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Mapper;

public class FlatMap {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		final ExecutionContext ec = ExecutionContexts
				.fromExecutorService(executorService);

		Future<StringBuilder> f1 = future(new Callable<StringBuilder>() {

			public StringBuilder call() throws Exception {
				System.out.println("f1 thread : "
						+ Thread.currentThread().getName());
				StringBuilder sb = new StringBuilder();
				FileReader fr = new FileReader("/home/akhil/tmp/test.txt");
				BufferedReader ber = new BufferedReader(fr);
				String line;
				while ((line = ber.readLine()) != null) {
					sb.append(line);
				}
				return sb;
			}

		}, ec);

		Thread.sleep(2000);
		Future<Integer> f2 = f1.flatMap(
				new Mapper<StringBuilder, Future<Integer>>() {

					@Override
					public Future<Integer> apply(final StringBuilder parameter) {
						System.out.println("f2 thread : "
								+ Thread.currentThread().getName());

						return future(new Callable<Integer>() {
							public Integer call() {
								return parameter.length();
							}
						}, ec);
					}

				}, ec);

		f2.onSuccess(new PrintResult<Integer>(), ec);
		executorService.shutdown();
	}
}
