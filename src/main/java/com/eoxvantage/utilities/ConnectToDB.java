package com.eoxvantage.utilities;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {
	
	public ConnectToDB() {
		
	}
	
	// Connection object
	static Connection con = null;
	
	// Statement object
	private static Statement stmt;
	
	// Constant for Database URL
	public static String DB_URL = ApplicationFileReader.getCreds("dbURL");

	// Database Username
	public static String DB_USER = ApplicationFileReader.getCreds("dbUserName");
	
	// Database Password
	public static String DB_PASSWORD = ApplicationFileReader.getCreds("dbPassword");

	/**
	 * @param email
	 * @return resetCode
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 */
	public String getData(String email)
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		// Database connection
		String dbClass = "com.mysql.cj.jdbc.Driver";
		Class.forName(dbClass);
		// Get connection to DB
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		// Statement object to send the SQL statement to the Database
		stmt = con.createStatement();

		String resetCode = null;
		try {
			String query = "select password_reset_code from oxzionapi.ox_user where username ='".concat(email)
					.concat("'");
			// Get the contents of user info table from DB
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				resetCode = res.getString("password_reset_code");
				System.out.println(resetCode);
			} else {
				System.out.println("Result set is empty !!!, Please check");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConn();
		return resetCode;
	}

	private void closeConn() throws SQLException {
		if (con != null) {
			con.close();
		}
	}

}
