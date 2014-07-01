package com.github.justudin.TextBookM_MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author udin
 * 
 */

public class Mysql_test {

	public static void main(String[] args) throws ClassNotFoundException {
		// Calculate execute times in ms
		long startTime = System.currentTimeMillis();

		// execute mysql_ process
		mysql_insert_process();
		
		//mysql_read_process();

		long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime)
				+ "ms");
	}

	public static void mysql_read_process() throws ClassNotFoundException {
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/textbook"; // Change as your
																// database
																// information

		String user = "root"; // user mysql
		String pass = ""; // password mysql

		// Define statement and resultset
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);

			// SQL SELECT query.
			String query = "SELECT * FROM textbook limit 2000";

			// create the java statement
			stmt = conn.createStatement();

			// execute the query, and get a java resultset
			rs = stmt.executeQuery(query);
			int a = 0;
			// iterate through the java resultset
			while (rs.next()) {
				int ISBN = rs.getInt("ISBN");
				String textbookName = rs.getString("textbookName");
				String textbookEditor = rs.getString("textbookEditor");
				String textbookPub = rs.getString("textbookPub");

				// print the results
				System.out.format("%s, %s, %s, %s\n", ISBN, textbookName,
						textbookEditor, textbookPub);
				a++;
				
			}
			System.out.println(a);
			stmt.close();

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
	}

	public static void mysql_insert_process() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/textbook"; // Change as your
																// database
																// information

		String user = "root"; // user mysql
		String pass = ""; // password mysql

		// Define statement and resultset
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);

			// create the java statement
			stmt = conn.createStatement();
			int a = 0;
			System.out.println("Input data:");
			// Insert with looping
			for (int i = 0; i < 100000; i++) {
				// Insert the data
				stmt.executeUpdate("INSERT INTO textbook (ISBN, textbookName, textbookEditor, textbookPub) "
						+ "VALUES ('123456', 'Mysql database Tutorial', 'Andrew John', 'Tianjin University Press')");
				a++;
			}
			System.out.println(a);
			stmt.close();

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
	}

}
