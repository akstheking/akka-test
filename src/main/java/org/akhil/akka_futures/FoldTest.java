package org.akhil.akka_futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.japi.Function2;
import static akka.dispatch.Futures.fold;
import static akka.dispatch.Futures.reduce;

public class FoldTest {
	
	static ExecutorService es = Executors.newFixedThreadPool(2);
	static ExecutionContext ec = ExecutionContexts.fromExecutorService(es);

	public static void main(String[] args) throws InterruptedException {
		Future<String> f1 = Futures.successful("Akhil");
		Future<String> f2 = Futures.successful("Pratap");
		Future<String> f3 = Futures.successful("Singh");
		List<Future<String>> futureList = new ArrayList<>();
		futureList.add(f1);
		futureList.add(f2);
		futureList.add(f3);
		
		Future<String> fs = fold("Name : ", futureList, new Function2<String, String, String>() {

			@Override
			public String apply(String arg1, String arg2) throws Exception {
				return arg1 + arg2;
			}
			
		}, ec);
		
		fs.onSuccess(new PrintResult<String>(), ec);
		
		Future<String> fsReduce = reduce(futureList, new Function2<String, String, String>() {

			@Override
			public String apply(String arg1, String arg2) throws Exception {
				return arg1 + arg2;
			}
			
		}, ec);
		
		fsReduce.onSuccess(new PrintResult<String>(), ec);
		
		Thread.sleep(2000);
		es.shutdown();
	}

}
