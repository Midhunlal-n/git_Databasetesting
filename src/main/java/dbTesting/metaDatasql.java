package dbTesting;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class metaDatasql {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "testuser";
		String password = "123456";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");

			// Retrieve and display database metadata
			System.out.println("\nDatabase Metadata:");
			DatabaseMetaData dbMetaData = conn.getMetaData();
			System.out.println("Database Product Name: " + dbMetaData.getDatabaseProductName());
			System.out.println("Database Product Version: " + dbMetaData.getDatabaseProductVersion());
			System.out.println("Tables in the database:");
			ResultSet rs = dbMetaData.getTables(null, null, "%", new String[] { "TABLE" });
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				if (!tableName.startsWith("pma_")) {
					System.out.println(tableName);
				}
			}
			System.out.println("------------------------------");
			System.out.println("ResultSet Metadata:");
			Statement stmt = conn.createStatement();
			ResultSet rs2 = stmt.executeQuery("SELECT * FROM offices");
			// ResultSet metadata
			ResultSetMetaData rsMetaData = rs2.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			System.out.println("Columns in customers table:");
			for (int i = 1; i <= columnCount; i++) {
				System.out.println(rsMetaData.getColumnName(i) + " - " + rsMetaData.getColumnTypeName(i));
			}

			conn.close();

		} catch (Exception e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
			return;
		}

	}

}
