package banking2;
import java.sql.Connection;
import java.util.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class jdbcdemo {
	
		public static void main(String[]args) {
			Scanner sc=new Scanner (System.in);
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url = "jdbc:oracle:thin:@//localhost:1521/orcl.iiht.tech";
				
				String userName = "scott";
				
				String password = "tiger";
				
				Connection con = DriverManager.getConnection(url,userName,password);
				
				if(con!=null)
					System.out.println("connected");
				else
					System.out.println("no connection");
				
				System.out.println ("Hello SELECT A OPTION");
				char option ='\0';
				
				do {
					System.out.println("--------------------------------------------------------------");
					System.out.println("--------------------------------------------------------------");
					System.out.println("O. ENTIRE DATA base");
					System.out.println("N. NEW ACCOUNT");
					System.out.println("A. check balance");
					System.out.println("B. deposit");
					System.out.println("C. withdraw");
					System.out.println("D. previous transaction");
					System.out.println("E. exit");
					System.out.println("==============================================================");
					System.out.println("enter an option");
					System.out.println("==============================================================");
					option=sc.next().charAt(0);
					Statement stmt=con.createStatement();
					switch (option) {
					 case 'O':
						 		
							ResultSet rs = stmt.executeQuery("select * from BankingSystem");
							System.out.println("customerId	customerName   		balance		previousTransaction");
							while(rs.next()) {
								System.out.println(rs.getString(1)+"		"+rs.getString(2)+"			"+rs.getString(3)+"			"+rs.getString(4));
							}
							break;
					 case 'N':
						 System.out.println("enter id");
						 int id1=sc.nextInt();
						 System.out.println("enter name");
						 String name1=sc.next();
						 String q="insert into BankingSystem (customerId,customerName) values (?,?)";
						 PreparedStatement pstmt=con.prepareStatement(q);
						 pstmt.setInt(1, id1);
						 pstmt.setString(2, name1);
						 pstmt.executeUpdate();
						 System.out.println("account created"); 
						 break;
				 case'A':
					 System.out.println("enter id");
					 int id2=sc.nextInt();
					 System.out.println("customerId	customerName   		balance		previousTransaction");
					 PreparedStatement pstmt2=con.prepareStatement("select * from BankingSystem where customerId=?");
					 pstmt2.setInt(1, id2);
					 ResultSet r=pstmt2.executeQuery();
					 while (r.next()) {
						 System.out.println(r.getString(1)+"		"+r.getString(2)+"			"+r.getString(3)+"			"+r.getString(4));
					 }
					 	break;
				 case'B':
					 System.out.println("enter id");
					 int id3=sc.nextInt();
					 System.out.println("ENTER AMOUNT TO DEPOSIT");
					 int amount=sc.nextInt(); 
					 PreparedStatement pstmt3=con.prepareStatement("update BankingSystem set balance=balance+? where customerId=?");
					 pstmt3.setInt(1, amount);
					 pstmt3.setInt(2, id3);
					 pstmt3.executeUpdate();
					 PreparedStatement pstmt4=con.prepareStatement("update BankingSystem set previousTransaction=? where customerId=?");
					 pstmt4.setInt(1, amount);
					 pstmt4.setInt(2, id3);
					 pstmt4.executeUpdate();
					 System.out.println("done");
					 break;
				 case'C':
					 System.out.println("enter id");
					 int id4=sc.nextInt();
					 System.out.println("enter an AMOUNT to withdraw");
					 int amount2=sc.nextInt();
					 PreparedStatement pstmt01=con.prepareStatement("select * from BankingSystem where customerId=?");
					 pstmt01.setInt(1,id4);
					 ResultSet r1=pstmt01.executeQuery();
					 int balance;
					 while(r1.next()) {
						 balance=r1.getInt(3);
					 
					 if (balance>=amount2) {
						 PreparedStatement pstmt5=con.prepareStatement("update BankingSystem set balance=balance-? where customerId=?");
						 pstmt5.setInt(1, amount2);
						 pstmt5.setInt(2, id4);
						 pstmt5.executeUpdate();
						 PreparedStatement pstmt6=con.prepareStatement("update BankingSystem set previousTransaction=-? where customerId=?");
						 pstmt6.setInt(1, amount2);
						 pstmt6.setInt(2, id4);
						 pstmt6.executeUpdate();
						 System.out.println("done");
					 }
					 else
						 System.out.println("insufficient balance");
					 }
					 break;
				 case'D':
					 System.out.println("enter id");
					 int id5=sc.nextInt();
					 PreparedStatement pstmt7=con.prepareStatement("select * from BankingSystem where customerId=?");
					 pstmt7.setInt(1, id5);
					 ResultSet res=pstmt7.executeQuery();
					 while (res.next()) {
						 System.out.println("previousTransaction=" + res.getString(4));
					 }
					 break;
				 case 'E':
					 System.out.println("*********************THANKYOU*********************************");
					 break;
				 default:
					 System.out.println("invalid option");
					 break;
				 }
			 }while (option!='E');
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}

	}