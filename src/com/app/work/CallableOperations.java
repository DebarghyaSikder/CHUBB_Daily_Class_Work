package com.app.work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableOperations {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		// Executors
		
		CallableCounter callobj=new CallableCounter();
		
		ExecutorService exc=Executors.newFixedThreadPool(10);  // maximum no. of threads that can be used is 10
		
		// we will reuse the thread and perform the task
//		exc.execute(null);
		Future<String> ftobj=exc.submit(callobj);
		
		System.out.println(ftobj.isDone());
		
		System.out.println(callobj.call()); // the string we are returning is the status
		System.out.println(ftobj.isDone());
		
		exc.shutdown();
	}

}
