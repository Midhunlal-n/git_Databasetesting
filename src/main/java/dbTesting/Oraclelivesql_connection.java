package dbTesting;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class Oraclelivesql_connection {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@db.freesql.com:1521/23ai_34ui2";
		String user = "MIDHUNLALN_SCHEMA_F95E4";
		String password = "T4AZT8UZ189GD2URvTJK8FE0#KP3L3";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established successfully!");

			// Prepare statement with parameters
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HR.EMPLOYEES WHERE FIRST_NAME = ?");
			// Set parameter values
			stmt.setString(1, "Steven");
			ResultSet rs = stmt.executeQuery();

			// Process the result set
			while (rs.next()) {
				int empNo = rs.getInt("EMPLOYEE_ID");
				String firstNm = rs.getString("FIRST_NAME");
				String lastnm = rs.getString("LAST_NAME");

				System.out.println(empNo + ": " + firstNm + " " + lastnm);
			}

			CallableStatement procstmt = conn.prepareCall("{CALL PR_SAL_PLUS_COMM(?,?)}");
			procstmt.setInt(1,7521);
			procstmt.registerOutParameter(2, Types.NUMERIC);
			procstmt.execute();
			System.out.println("Procedure executed successfully.");
			System.out.println("Salary plus Commission: " + procstmt.getDouble(2));
			
			procstmt.close();
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
