package com.app.dto;


public class AccountBalanceException extends Exception{
	
// Checked exception which we need to handle
	
	public AccountBalanceException(String msg) {
		super(msg);
	}
}
