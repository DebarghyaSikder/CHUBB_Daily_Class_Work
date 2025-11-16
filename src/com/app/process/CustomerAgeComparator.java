package com.app.process;

import java.util.Comparator;

import com.app.dto.Customer;

public class CustomerAgeComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		
		// Pass the two objects and compare to both
		Customer c1=(Customer)o1;
		Customer c2=(Customer)o2;
		
		 // Add one more field age in Customer.java
		
		 
		if(c1.getAge()>c2.getAge()) {
			return 1;
		}
		else if(c1.getAge()<c2.getAge()) {
			return -1;
		}
		return 0;
		
	}

}
