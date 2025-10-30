package dbTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class updatesampleQuery {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "testuser";
		String password = "123456";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");
			conn.setAutoCommit(false); // Disable auto-commit for transaction management

			// Example query execution
			Statement stmt = conn.createStatement();
			// Insert a new record into the offices table
			// executeUpdate can be used for insert, update, delete
			int rowsAffected = stmt.executeUpdate("UPDATE offices SET city='Sevilla' WHERE officeCode=8");
			System.out.println("Rows affected: " + rowsAffected);

			boolean conditon = askUserConfirmation(); // Placeholder for user confirmation logic
			if (conditon == true) {
				conn.commit(); // Commit the transaction if condition is met
				System.out.println("Transaction committed.");
			} else {
				conn.rollback(); // Roll back the transaction if condition is not met
				System.out.println("Transaction rolled back.");
			}
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
			return;
		}

	}

	public static boolean askUserConfirmation() {
		System.out.println("Do you want to commit the changes? (yes/no)");
		Scanner reader = new Scanner(System.in);
		String response = reader.nextLine().trim().toLowerCase();

		boolean choice;
		if (response.equals("yes")) {
			choice = true;
		} else {
			choice = false;
		}
		reader.close();
		return choice;
	}
}
