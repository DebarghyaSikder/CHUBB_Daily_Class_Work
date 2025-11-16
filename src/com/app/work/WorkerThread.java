package com.app.work;

import com.app.process.FileReadOperations;

public class WorkerThread implements Runnable {
	
//	FileReadOperations obj=new FileReadOperations(); // This does work regarding read and write file
	
	FileReadOperations obj;
	
	WorkerThread(FileReadOperations obj1){  // Now we have shared this object here
		this.obj=obj1;
	}
	
	@Override
	public void run() {
		
		for(int i=0;i<50;i++) {
			
		
		
		obj.writeFilewithAppend("C:/Users/KIIT/Desktop/Coding/CHUBB/Day7_30_10_25/FileProcessingTest.txt");  // instead of overriding each time it will keep on appending);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		// TODO Auto-generated method stub
		System.out.println("Hi this inside a run");
	}

}
