package dbTesting;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class procedureCallsql {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "testuser";
		String password = "123456";
		String productCode = "S10_1678";
		String productline = "classic cars";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");

			// Procedure with input parameter
			CallableStatement mystmt = conn.prepareCall("{CALL IncreaseMSRPby10(?)}");
			mystmt.setString(1, productCode);
			mystmt.execute();
			System.out.println("Stored procedure executed successfully.");
			System.out.println("MSRP increased by 10 for product " + productCode);
			mystmt.close();

			// procedure with in out parameter
			System.out.println("\nCalling procedure with INOUT parameter...");
			CallableStatement mystmt2 = conn.prepareCall("{CALL GetproductDesc(? , ?)}");
			mystmt2.setString(1, productline);
			mystmt2.registerOutParameter(2, Types.VARCHAR);
			mystmt2.execute();
			String productDesc = mystmt2.getString(2);
			System.out.println("Product Description: " + productDesc);
			mystmt2.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
			return;
		}

	}

}
