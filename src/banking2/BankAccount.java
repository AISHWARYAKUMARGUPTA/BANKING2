package banking2;

import java.util.Scanner;

public class BankAccount {
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	
	BankAccount(String customerName,String customerId){
		this.customerName=customerName;
		this.customerId=customerId;
	}
	
	void deposit(int amount) {
		if (amount>0) {
			balance=balance+amount;
			previousTransaction=amount;
		}	
	}
	
	void withdraw(int amount) {
		if(amount>0) {
			balance=balance-amount;
			previousTransaction=-amount;
		}
	}
	
	void getPreviousTransaction() {
		if(previousTransaction>0) 
			System.out.println("DEPOSITED:"+previousTransaction);
		else if(previousTransaction<0) 
			System.out.println("WITHDRAWN:"+Math.abs(previousTransaction));
		else
			System.out.println("NO TRANSACTION OCCURED");
			
	}
	
	 void showMenu() {
		 char option ='\0';
		 Scanner sc=new Scanner( System.in);
		 
		 System.out.println("WELCOME"+customerName);
		 System.out.println("YOUR ID IS"+customerId);
		 System.out.println("\n");
		 System.out.println("A. check balance");
		 System.out.println("B. deposit");
		 System.out.println("C. withdraw");
		 System.out.println("D. previous transaction");
		 System.out.println("E. exit");
		 
		 do {
			 System.out.println("==============================================================");
			 System.out.println("enter an option");
			 System.out.println("==============================================================");
			 option=sc.next().charAt(0);
			 System.out.println("\n");
			 
			 switch(option) {
			 case'A':
				 System.out.println("-------------------------------");
				 System.out.println("balance="+balance);
				 System.out.println("-------------------------------");
				 System.out.println("\n");
				 break;
			 case'B':
				 System.out.println("-------------------------------");
				 System.out.println("ENTER AMOUNT TO DEPOSIT");
				 System.out.println("-------------------------------");
				 int amount=sc.nextInt();
				 deposit(amount);
				 System.out.println("\n");
				 break;
			 case'C':
				 System.out.println("-------------------------------");
				 System.out.println("enter an award to withdraw");
				 System.out.println("-------------------------------");
				 int amount2=sc.nextInt();
				 withdraw(amount2);
				 System.out.println("\n");
				 break;
			 case'D':
				 System.out.println("-------------------------------");
				 getPreviousTransaction();
				 System.out.println("\n");
				 break;
			 case 'E':
				 System.out.println("*****************************");
				 break;
			 default:
				 System.out.println("invalid option");
				 break;
			 }
		 }while (option!='E');
		
		 System.out.println("THANKYOU"); 
	 }
}
