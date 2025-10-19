package dbTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class mySQLdbTestingdemo {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "testuser";
		String password = "123456";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");

			// Example query execution
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
			// Process the result set
			while (rs.next()) {
				int customerId = rs.getInt("customerNumber");
				String customerName = rs.getString("customerName");

				System.out.println(customerId + ": " + customerName);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
			return;
		}

	}

}
