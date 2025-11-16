package com.app.process;
import com.app.dto.Customer;

public abstract class ProcessPayment implements EmailProcessing{
public int processcount;
public static void processFund(Customer initiator, Customer bene, double amount) throws Exception {  // this exception for parent class should be broader than child class
	System.out.println("First progrram");
	
	if(initiator!=null && bene!=null) {
		if(initiator.getAmountbalance()>amount) {
			double balanceamount=initiator.getAmountbalance()-amount;
			initiator.setAmount(balanceamount);
			bene.setAmount(bene.getAmountbalance()+amount);
		}
		else {
			System.out.println("Not enough balance");
		}
	}
}


public abstract boolean validateCustomer(Customer c1);

public boolean validateEmail(Customer c1) {
	if(c1.getEmail()!=null && c1.getEmail().contains("@"))
			{
		return true;
	}
	return false;
}

public void initializeEmailServer() {
	System.out.println("initialize server with azy=ure email sdervice");
}
}
