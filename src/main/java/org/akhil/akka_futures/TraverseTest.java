package org.akhil.akka_futures;

import static akka.dispatch.Futures.traverse;
import static akka.dispatch.Futures.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Mapper;
import akka.japi.Function;

public class TraverseTest {

	static ExecutorService es = Executors.newFixedThreadPool(2);
	static ExecutionContext ec = ExecutionContexts.fromExecutorService(es);

	public static void main(String[] args) throws InterruptedException {
		List<String> list = Arrays.asList(new String[] { "Akhil", "Pratap",
				"String" });
		Future<Iterable<String>> uppFuture = traverse(list,
				new Function<String, Future<String>>() {

					@Override
					public Future<String> apply(final String param)
							throws Exception {
						return future(new Callable<String>() {

							@Override
							public String call() throws Exception {
								return param.toUpperCase();
							}

						}, ec);
					}

				}, ec);
		
		uppFuture.onSuccess(new PrintResult<Iterable<String>>(), ec);

		Future<String> concatedString = uppFuture.map(
				new Mapper<Iterable<String>, String>() {

					@Override
					public String apply(Iterable<String> parameter) {
						String s = "";
						for (String p : parameter) {
							s += p;
						}
						return s;
					}
				}, ec);

		concatedString.onSuccess(new PrintResult<String>(), ec);
		Thread.sleep(2000);
		es.shutdown();

	}

}
