package dbTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class blobandClobtesting {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "testuser";
		String password = "123456";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");

			// Update BLOB and CLOB fields
			PreparedStatement stmt = conn.prepareStatement(
					"UPDATE employees SET resume_blob = ?, resume_text = ? WHERE employeeNumber = 1002");
			// PDF file
			File pdfFile = new File("C:\\Users\\midhunn\\Documents\\RESUME_BLOB_TESTING.pdf");
			FileInputStream pdfInputStream = new FileInputStream(pdfFile);
			stmt.setBinaryStream(1, pdfInputStream);
			// text file
			File textFile = new File("C:\\Users\\midhunn\\Documents\\RESUME_CLOB_TESTING.txt");
			FileReader textInputStream = new FileReader(textFile);
			stmt.setCharacterStream(2, textInputStream);

			int rowsUpdated = stmt.executeUpdate();
			System.out.println("Rows updated: " + rowsUpdated);
			pdfInputStream.close();
			textInputStream.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
			return;
		}

	}
}
