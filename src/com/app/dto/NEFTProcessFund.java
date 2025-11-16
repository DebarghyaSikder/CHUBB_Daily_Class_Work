package com.app.dto;

import com.app.process.ProcessPayment;
import com.app.process.SMSProcessing;

public class NEFTProcessFund extends ProcessPayment implements SMSProcessing{
	public static void processFund(Customer initiator, Customer bene, double amount) throws AccountBalanceException {
		System.out.println("First progrram in NEFTProcessFund");
		
		if(initiator!=null && bene!=null) {
			if(initiator.getAmountbalance()>amount && amount>200000) {
				double balanceamount=initiator.getAmountbalance()-amount;
				initiator.setAmount(balanceamount);
				bene.setAmount(bene.getAmountbalance()+amount);
				System.out.println("process fund immediately");
			}
			else {
				throw new AccountBalanceException("Not enough balance or not a NEFT payment");
			}
		}
	}
	
	public boolean validateCustomer(Customer c1) {
		if(c1.getName()!=null && c1.getName().equals("Bin Laden")) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean validateEmail() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean sendSMS(Customer c1) {
		System.out.println("send SMS to customer "+c1.getName());
		return true;
	}
	
	

}

// SUBCLASS ACCESS SPECFIER HAS TO BE GREATER