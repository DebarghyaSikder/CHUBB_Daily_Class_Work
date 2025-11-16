package com.app.process;

public interface EmailProcessing  {
	
	// by default methods will be public and abstract
	boolean validateEmail();
	
	static boolean sendEmail() {   // static methods cannot be overridden but default ones can be
		System.out.println("sending emails");
		return true;
	}
	
	default void initializeEmailServer() {
		System.out.println("initialize server");
	}
}
