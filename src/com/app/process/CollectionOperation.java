package com.app.process;

import java.util.*;

import com.app.dto.Customer;

public class CollectionOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CollectionOperation obj = new CollectionOperation();
//		obj.processList();
//		obj.processSetforCustomerObjects();
//		obj.sortedSetProcess();
//		obj.procesMap();
//		obj.processMapTree();

//		obj.processCompanyEmployees();
//
//		obj.processQueue();
//		obj.processList();
		
		obj.processSetforCustomerObjects();
		
		obj.procesSortedMap();
		
		obj.processSortedList();
		
		obj.addCustomers();
	}
	
	
	public void addCustomers() {
		
		List<Customer> custList = new ArrayList<Customer>();
		for(int i=0;;i++) {
			Customer c6 = new Customer("Trump", "trump@gmail.com", "3728449", 50000,20);
			custList.add(c6);
			
			try {
				Thread.sleep(40);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void processSortedList() {
//		String name = "";
		Customer custObj=null;
		
		
		// Passing the comparator object
		CustomerAgeComparator comp=new CustomerAgeComparator();
		
		
		List<Customer> custList = new ArrayList<Customer>();

		Customer c1 = new Customer("James", "deb@gmail.com", "438292937", 4382,65);
		Customer c2 = new Customer("Robin", "robin@gmail.com", "477262263", 463622,24);
		Customer c3 = new Customer("Joy", "joy@gmail.com", "47322737392", 382322,45);
		Customer c4 = new Customer("Trump", "trump@gmail.com", "3728449", 50000,20);

				
		custList.add(c2);
		custList.add(c1);
		custList.add(c3);
		custList.add(c4);
		
		
		Collections.sort(custList,comp);  // passing the list and comparator

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


	public void processList() {
		String name = "";
		ArrayList<String> names = new ArrayList<String>();
		names.add("James"); // index 0
		names.add("Rohan"); // 1
		names.add("Roy");
		names.add("Ajay");
		names.add("Camlin");

		names.remove(0);
		names.add("Rohit");
		
		Collections.sort(names);  // modifies the list

		boolean isnamethere = names.contains("Rohit");
		int index = names.indexOf("Rohit");

		System.out.println(isnamethere + " " + index);

		Iterator<String> itr = names.iterator();

		while (itr.hasNext()) {
			name = itr.next();
			System.out.println(name);
		}

		names.forEach(namea -> System.out.println(namea)); // lambda expression- internally uses the iterator
		names.forEach(System.out::println); // cleaner expression that prints everything on new line
	}

	public void processSetforCustomerObjects() {
//		String name = "";
		Customer custObj=null;
		
		
		// Passing the comparator object
		CustomerAgeComparator comp=new CustomerAgeComparator();
		
		
		TreeSet<Customer> custList = new TreeSet<Customer>(comp);

		Customer c1 = new Customer("James", "deb@gmail.com", "438292937", 4382,65);
		Customer c2 = new Customer("Robin", "robin@gmail.com", "477262263", 463622,24);
		Customer c3 = new Customer("Joy", "joy@gmail.com", "47322737392", 382322,45);
		Customer c4 = new Customer("Trump", "trump@gmail.com", "3728449", 50000,20);

		custList.add(c2);
		custList.add(c1);
		custList.add(c3);
		custList.add(c4);
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

	public void sortedSetProcess() {
		String name = "";
		TreeSet<String> names = new TreeSet<String>();
		names.add("Anish");
		names.add("Rohan");
		names.add("yatin");
		names.add("james");

		Iterator<String> itr = names.iterator();

		while (itr.hasNext()) {
			name = itr.next();
			System.out.println(name);
		}

		names.forEach(namea -> System.out.println(namea)); // namea is every customer object
		names.forEach(System.out::println);
	}

	public void procesMap() {

		String key = "";
		String value = "";
		HashMap<String, String> studentmap = new HashMap<String, String>(32,.90f);  // hashmap has a default initial capacity and LOAD FACTOR.. Hashmap will be resized when it has gone to full 90 percent and initial capacity is 92

		studentmap.put("james", "IT");
		studentmap.put("Roy", "CS");
		studentmap.put("Rohit", "AI");
		studentmap.put("Yatin", "Devops");

		System.out.println(studentmap.get("Rohit"));
		Set<String> keyset = studentmap.keySet();

		Iterator<String> itr = keyset.iterator();

		while (itr.hasNext()) {
			key = itr.next();
			value = studentmap.get(key);
			System.out.println(key);
		}
	}
	
	
	
	public void procesSortedMap() {

		String key = "";
		String value = "";
		
		// comparator
		CustomerAgeComparator comp=new CustomerAgeComparator();
		
		TreeMap<Customer, String> custommap = new TreeMap<Customer, String>(comp);  // passing the comparator so that the TreeMap can internally understand that what it should compare and what basis should it sort
		TreeMap<String, String> studentmap = new TreeMap<String, String>();
		
		studentmap.put("james", "IT");
		studentmap.put("Roy", "CS");
		studentmap.put("Rohit", "AI");
		studentmap.put("Yatin", "Devops");

		System.out.println(studentmap.get("Rohit"));
		Set<String> keyset = studentmap.keySet();

		Iterator<String> itr = keyset.iterator();

		while (itr.hasNext()) {
			key = itr.next();
			value = studentmap.get(key);
			System.out.println(key);
		}
	}
	
	

	public void processMapTree() {
		String key = "";
		String value = "";
		TreeMap<String, String> studentmap = new TreeMap<String, String>(); // TreeMap sorts based on name

		studentmap.put("james", "IT");
		studentmap.put("roy", "CS");
		studentmap.put("Rohit", "AI");
		studentmap.put("yatin", "Devops");

		System.out.println(studentmap.get("Rohit"));
		Set<String> keyset = studentmap.keySet();

		Iterator<String> itr = keyset.iterator();

		while (itr.hasNext()) {
			key = itr.next();
			value = studentmap.get(key);
			System.out.println(key);
		}
	}

	public void processQueue() {
		String name = "";
		Queue<String> names = new PriorityQueue<String>();
		names.add("James"); // index 0
		names.add("Rohan"); // 1
		names.add("Roy");

		names.remove(0);

		names.add("Rohit");

		boolean isnamethere = names.contains("Rohit111");

		System.out.println(" is name there " + isnamethere);

		Iterator<String> itr = names.iterator();

		while (itr.hasNext()) {
			name = itr.next();
			System.out.println(name);
		}

		names.forEach(namea -> System.out.println(namea)); // lambda expression- internally uses the iterator
		names.forEach(System.out::println);

	}

	// Assignment
	public void processCompanyEmployees() {
		// Map where key= company name, value= list of employee names

		Map<String, List<String>> companymap = new HashMap<>();

		companymap.put("Google", Arrays.asList("Amit", "Sonia", "Karan"));
		companymap.put("Microsoft", Arrays.asList("Ravi", "Tina", "Arjun"));
		companymap.put("TCS", Arrays.asList("Rohit", "Priya", "Sneha"));
		companymap.put("Infosys", Arrays.asList("Ankit", "Vikram", "Ramesh"));
		companymap.put("Wipro", Arrays.asList("Suresh", "Meena", "Rajat"));

		// Display the map
		System.out.println("\n--- Company and their Employees ---");
		companymap.forEach((company, employees) -> {
			System.out.println(company + " -> " + employees);
		});

		// Checking if Cognizant exists
		String targetCompany = "Cognizant";
		String targetemployee = "Ram";

		if (!companymap.containsKey(targetCompany)) {
			System.out.println("\nCompany '" + targetCompany + "' not found. Adding new entry...");
			companymap.put(targetCompany, new ArrayList<>(Arrays.asList("Ram", "Sita", "Gopal")));

		}

		// To check if Ram works in Cognizant
		List<String> emplist = companymap.get(targetCompany);

		if (emplist.contains(targetemployee)) {
			System.out.println("\nYes, " + targetemployee + " works in " + targetCompany);
		} else {
			System.out.println("\nEmployee '" + targetemployee + "' not found in " + targetCompany);
		}

		// Printing updated map
		System.out.println("Updated comapny map");
		companymap.forEach((company, employees) -> {
			System.out.println(company + "->" + employees);
		});

	}
}
