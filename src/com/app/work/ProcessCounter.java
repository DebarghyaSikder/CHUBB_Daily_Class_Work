package com.app.work;

import java.util.concurrent.atomic.AtomicInteger;

public class ProcessCounter {
	
	private int counter;
	
	// use AtomicInteger to make the variables safe. Means in one go it has to be done
	private AtomicInteger counter1=new AtomicInteger();
	
	synchronized void increment() throws InterruptedException {  // critical section safe
		counter1.getAndIncrement();
		counter++;
		wait(20);
		notify(); 
	}
// one thread keeps on incrementing and one particular thread keeps on decrementing
	synchronized void decrement() throws InterruptedException {
		counter1.getAndDecrement();
		counter--;   // make the objecst thread safe
		wait(20);
		notify(); // notify to other threads that you can come
	}
	
	public synchronized int getValue() {
		return counter;
	}
	
	public synchronized int getatomiccounterValue() {
		return counter1.get();  
	}
	
	// these are thread safe object methods
}


// If one thread increments and one decrements there will be inconsistency in the value of the counter
