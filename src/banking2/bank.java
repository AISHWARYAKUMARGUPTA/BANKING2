package banking2;

import java.util.Scanner;

public class bank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner (System.in);
		BankAccount ob=new BankAccount("xyz","101");
		ob.showMenu();
	}

}
