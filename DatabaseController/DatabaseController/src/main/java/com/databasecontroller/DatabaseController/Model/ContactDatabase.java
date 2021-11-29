package com.databasecontroller.DatabaseController.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* Creates connection to database and provides methods to open and shut connection.
 * Provides method to add information to database. */

public class ContactDatabase {
	private String url, username, password;
	private Connection connection;
	
	
	/* Constructor. The database url, username and password is initialised. */
	public ContactDatabase() {
		url = "jdbc:mysql://localhost:3306/tietopiiri_contacts";
		username = "username";
		password = "password";
	}
	
	/* Creating a connection to MariaDB database. */
	public boolean connect() {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			connection=DriverManager.getConnection(  
			url, username, password);
			
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}    
	}
	
	/* Adding Contact object to database. Return true or false, depending on result. */
	public boolean insert(Contact c) {
		try {
			Statement stm = connection.createStatement();
			
			String query = "insert into contacts(FirstName, LastName, Email, PhoneNumber, City) values ('" + c.getFirstName() + "','" + c.getLastName() + "','" + c.getEmail() + "','" + c.getPhoneNro() + "','" + c.getCity() + "');";
			boolean result = stm.execute(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/* Checks if contact already exists in the database. */
	public boolean checkIfExisting(Contact c) {
		try {
			Statement check = connection.createStatement();
			
			String checking = "select * from contacts where Email = '" + c.getEmail() + "' && FirstName = '" + c.getFirstName() +  "' && LastName = '" + c.getLastName() + "' limit 1;";
			ResultSet rs=check.executeQuery(checking);
			
			while(rs.next()) {
				return false;
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	
	/* Shutting the connection with database. */
	public boolean disconnect() {
		try {
			connection.close();
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

}
