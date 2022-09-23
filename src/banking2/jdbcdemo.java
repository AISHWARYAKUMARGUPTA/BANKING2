package banking2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class jdbcdemo {
	
		public static void main(String[]args) {
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
				
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from BankingSystem");
				System.out.println("customerId	customerName   		balance		previousTransaction");
				while(rs.next()) {
					System.out.println(rs.getString(1)+"		"+rs.getString(2)+"			"+rs.getString(3)+"			"+rs.getString(4));
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}

	}