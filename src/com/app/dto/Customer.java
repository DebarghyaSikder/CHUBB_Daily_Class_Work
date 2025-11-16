package com.app.dto;

//public class Customer implements Comparable
public class Customer
{
	
	public Customer(){
		System.out.println("Inside deafult constructor");
	}
	public Customer(String custname, String custemail, String accountdetails, double balance, int age){
		this.accountno=accountdetails;
		this.amountbalance=balance;
		this.email=custemail;
		this.name=custname;
		this.age=age;
	}

	private int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	private String name;
	private double amountbalance;
	private String email;
	private String accountno;
	
	public void setAmount(double amount) {
		this.amountbalance=amount;
	}
	
	public String getName() {
		return name;
	}
	
	public double getAmountbalance() {
		return amountbalance;
	}

	public String getEmail() {
		return email;
	}

	public String getAccountno() {
		return accountno;
	}
//	@Override
//	public int compareTo(Object o) {   // Comparing object to object
//		// TODO Auto-generated method stub
//		Customer c=(Customer)o;  // Downcasting to Customer type 
//		if(this.amountbalance<c.amountbalance) {
//			return 1;
//		}
//		else if(this.amountbalance>c.amountbalance) {
//			return -1;
//		}
//		return 0;
//	}
	
	@Override
	public int hashCode() {
		return this.age;
	}
	
	@Override
	public boolean equals(Object o) {   // if name and accountno.and age are equal then both the objects are same
		Customer c=(Customer) o;
		if(this.age==c.getAge() && this.name.equalsIgnoreCase(c.getName()) && this.accountno.equals(c.getAccountno())) {
			return true;
		}
		return false;
	}

}
