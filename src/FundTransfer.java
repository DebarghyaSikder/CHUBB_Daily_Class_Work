import com.app.dto.AccountBalanceException;
import com.app.dto.Customer;
import com.app.dto.NEFTProcessFund;
import com.app.process.ProcessPayment;
import java.io.*;
public class FundTransfer {

	public int count;
	public static void main(String[] args) throws FileNotFoundException, IOException{
		// TODO Auto-generated method stub
		System.out.println("This is test");
		
		
		
//		ProcessPayment obj=new ProcessPayment();           abstract cannot be instantiated
//		ProcessPayment obj1=new ProcessPayment();
		
//		try {
//			int i=5/0;
//			int arr[]= {};
//			//System.out.println(arr[2]);
//			File f=new File("");  // this file does not exist so exception
//			FileInputStream ftr=new FileInputStream(f);
//			ftr.read();
//			FileReader fr=new FileReader(f);
//			fr.close();
//		}
//		catch(ArithmeticException ex) {
			System.out.println("Inside a catch");
//		}
		
//		catch(Exception ex) {
//			System.out.println("Inside a catch");
//		}
//		
		
//		catch(Exception ex) {  // catching general exception not ARrayINdexoutofbound
//			System.out.println("IO Exception "+ ex.getMessage() );
//		}
		
		
		FundTransfer fobj=new FundTransfer();
		
		Customer c1=new Customer("James","deb@gmail.com","438292937",4382,40);
		Customer c2=new Customer("Robin","robin@gmail.com","477262263",463622,20);
		
//		obj.processFund(c1,c2,4000);
		
//		NEFTProcessFund neftobj=new NEFTProcessFund();
		NEFTProcessFund neftobj=new NEFTProcessFund();  // calls neft method since object of child class
		
		
		
		
		System.out.println("customer balance initiator"+ c1.getAmountbalance());
		System.out.println("customer balance bene "+c2.getAmountbalance());
		
		boolean isValidCustomer=neftobj.validateCustomer(c2);
		if(isValidCustomer) {
			try {
				neftobj.processFund(c1,c2,30000);
			} catch (AccountBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Not valid customer");
		}
		
		
//		neftobj.validateCustomer(c2);
//		neftobj.processFund(c1, c2, 30000);
	}

}
