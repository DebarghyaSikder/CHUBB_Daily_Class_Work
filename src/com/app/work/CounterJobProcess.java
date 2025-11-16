package com.app.work;

public class CounterJobProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Constructor injection- using the object of a separate class inside this constructor
		ProcessCounter countobj=new ProcessCounter();
		CountingIncrementor inobj=new CountingIncrementor(countobj);

		// We link the same object to multiple threads
		
		// CountingIncrementt is a thrread that is trying to increment and CountingWorker is a thread trying to decrement it
		
		CountingWorker dobj=new CountingWorker(countobj);
		
		inobj.start();
		dobj.start();
		
		System.out.println("value of counteer "+countobj.getValue());
		
		System.out.println("value of counter atomic "+countobj.getatomiccounterValue());
	}

}
