package com.app.work;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.app.process.FileReadOperations;

public class JobThread extends Thread{
	
//	FileReadOperations obj=new FileReadOperations();
	
	FileReadOperations obj;
	
	JobThread(FileReadOperations obj1){
		this.obj=obj1;
		
	}
	
	public JobThread(String name) {
		super(name);
	}
	public void run() {
		System.out.print("Inside run"); 
		try {
			for(int i=0;i<50;i++)
			{
			obj.readFile("C:/Users/KIIT/Desktop/Coding/CHUBB/Day7_30_10_25/FileProcessingTest.txt");    // Telling the thread that you need to read the file
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(isAlive());
	}
}


// Both the threads of Reading and Write are trying to acquire lock on the object of FileReadOperations
// both the threads are working into two different methods but they are using a shared file