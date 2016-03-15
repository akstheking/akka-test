package org.akhil.akka_futures;

import static akka.dispatch.Futures.future;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Mapper;
import static akka.dispatch.Futures.sequence;


public class SequenceTest {
	
	static ExecutorService es = Executors.newFixedThreadPool(2);
	static ExecutionContext ec =
			ExecutionContexts.fromExecutorService(es);

	public static void main(String[] args) {
		
		String[] files = {"/home/akhil/tmp/test.txt","/home/akhil/tmp/test2.txt","/home/akhil/tmp/test3.txt"};
		List<Future<Integer>> futures = new ArrayList<>();
		for(String file : files) {
			futures.add(getFileFuture(file));
		}
		
		Future<Iterable<Integer>> future = sequence(futures, ec);
		
		Future<Long> totalSize = future.map(new Mapper<Iterable<Integer>, Long>() {

			@Override
			public Long apply(Iterable<Integer> parameter) {
				Long l = 0L;
				for(Integer i : parameter) {
					l += i;
				}
				return l;
			}
		}, ec);	
		
		totalSize.onSuccess(new PrintResult<Long>(), ec);
		es.shutdown();
		
	}
	
	public static Future<Integer> getFileFuture(final String file) {
		Future<Integer> f1 = future(new Callable<Integer>() {

			public Integer call() throws Exception {
				System.out.println("f1 thread : " + Thread.currentThread().getName());
				StringBuilder sb = new StringBuilder();
				FileReader fr = new FileReader(file);
				BufferedReader ber = new BufferedReader(fr);
				String line;
				while((line = ber.readLine()) != null){
					sb.append(line);
				}
				System.out.println(file + " : " + sb.length());
				return sb.length();
			}
			
		}, ec);
		
		return f1;
	}
	
	public static List<Future<Integer>> generateLengthFutures() throws IOException {
		List<Future<Integer>> futures = new ArrayList<>();
		FileReader fr = new FileReader("/home/akhil/tmp/test.txt");
		BufferedReader ber = new BufferedReader(fr);
		String line;
		while((line = ber.readLine()) != null){
			//futures.add(es.submit(new LengthFuture(line)));
		}
		return futures;
	}

}
