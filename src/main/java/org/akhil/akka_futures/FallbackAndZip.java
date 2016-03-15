package org.akhil.akka_futures;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.Tuple2;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;

public class FallbackAndZip {
	
	static ExecutorService es = Executors.newFixedThreadPool(2);
	static ExecutionContext ec = ExecutionContexts.fromExecutorService(es);
	
	public static void main(String[] args) throws InterruptedException {
		Future<String> f1 = Futures.failed(new NullPointerException("Nullllll"));
		Future<String> f2 = Futures.failed(new NullPointerException("nulllll"));
		Future<String> f3 = Futures.successful("Ariana");
		Future<String> f4 = Futures.successful("Grande");
		
		Future<String> f5 = f1.fallbackTo(f2).fallbackTo(f3).fallbackTo(f4);
		
		f5.onSuccess(new PrintResult<String>(), ec);
		
		Future<String> f6 = f3.zip(f4).map(new Mapper<scala.Tuple2<String, String>, String>() {

			@Override
			public String apply(Tuple2<String, String> zipped) {
				return zipped._1 + "__" + zipped._2;
			}
			
		}, ec);
		
		f6.onSuccess(new PrintResult<String>(), ec);
		
		Thread.sleep(2000);
		es.shutdown();
	}

}
