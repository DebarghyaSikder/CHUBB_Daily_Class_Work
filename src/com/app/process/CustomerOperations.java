package com.app.process;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import com.app.dto.Customer;

public class CustomerOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CustomerOperations obj=new CustomerOperations();
		obj.processSetforCustomerObjects();
		
	}
	public void processSetforCustomerObjects() {
//		String name = "";
		Customer custObj=null;
		
		
		// Passing the comparator object
		
		
		HashSet<Customer> custList = new HashSet<Customer>();

		Customer c1 = new Customer("James", "deb@gmail.com", "438292937", 4382,65);
		Customer c5 = new Customer("James", "deb@gmail.com", "438292937", 4382,65);
		
		Customer c2 = new Customer("Robin", "robin@gmail.com", "477262263", 463622,24);
		Customer c3 = new Customer("Joy", "joy@gmail.com", "47322737392", 382322,45);
		Customer c4 = new Customer("Trump", "trump@gmail.com", "3728449", 50000,20);

		
		if(c1.hashCode()!=c5.hashCode()) {   // if the hashcodes of two objects are diff. then they are not same
			System.out.println("c1 and c5 are not same");
		}
		custList.add(c2);
		custList.add(c1);
		custList.add(c3);
		custList.add(c4);
		custList.add(c5);
		
		System.out.println("size is "+custList.size());    // here the answer will b 4. Even though c1 and c5 are different objects but since we have written overridden functions add() and hashCode() so it will be not be considered different and not be added to the list. It will not create any new entry and will override the same
		Iterator<Customer> itr = custList.iterator();

//		while (itr.hasNext()) {
//			name = itr.next().getName();
//			System.out.println(name);
//		}
		
		while (itr.hasNext()) {
			custObj=itr.next();
			String name = custObj.getName();
			System.out.println(name);
			System.out.println("customer balance "+custObj.getAmountbalance());
			System.out.println("customer age "+custObj.getAge());
		}

		custList.forEach(namea -> System.out.println(namea.getName())); // namea is every customer object
		custList.forEach(System.out::println); // cleaner expression that prints everything on new line
	}

}
