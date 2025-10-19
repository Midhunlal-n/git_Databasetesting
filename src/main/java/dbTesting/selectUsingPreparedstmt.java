package dbTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class selectUsingPreparedstmt {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "testuser";
		String password = "123456";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");

			// Prepare statement with parameters
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM employees WHERE employeeNumber > ? AND reportsTo = ?");
			// Set parameter values
			stmt.setInt(1, 1100);
			stmt.setInt(2, 1056);
			ResultSet rs = stmt.executeQuery();

			// Process the result set
			while (rs.next()) {
				int empNo = rs.getInt("employeeNumber");
				String firstNm = rs.getString("firstName");

				System.out.println(empNo + ": " + firstNm);
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
