package dbTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class insertIntodemo {

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
			// Insert a new record into the offices table
			//executeUpdate can be used for insert, update, delete
			int rowsAffected = stmt.executeUpdate(
					"INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) "
							+ "VALUES ('8', 'Madrid', '+1 512 448 3820', '52 East Central Street', 'Suite 415', 'MA', 'Spain', '88622', 'EMEA')");
			System.out.println("Rows affected: " + rowsAffected);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
			return;
		}

	}

}
