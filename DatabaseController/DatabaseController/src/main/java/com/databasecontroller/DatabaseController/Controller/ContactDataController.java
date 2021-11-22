package com.databasecontroller.DatabaseController.Controller;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.databasecontroller.DatabaseController.Model.Contact;
import com.databasecontroller.DatabaseController.Model.ContactDatabase;

public class ContactDataController {
	
	/* Controller which takes care of API's and Databases connection between each other.
	 * API controller calls this class to submit input to database. This class opens databases connection,
	 * adds input and takes care connection is closed properly after.  */
	
	private static ContactDataController controller;
	private ContactDatabase database;
	
	/* Constructor */
	private ContactDataController() {
		database = new ContactDatabase();
	}
	
	/* Class is singleton, so it returns of instance of itself. */
	public static ContactDataController getInstance() {
		if(controller == null) controller = new ContactDataController();
		return controller;
	}
	
	/* API controller calls this method to save input to database.
	 * Method checks that input is not null before calling database class.
	 * Method checks also if contact already exists in the database, to prevent adding duplicates.
	 * Method return true or false, depending on result from database class. 
	 * Method checks given contact if the given informations match expected. */
	public boolean save(Contact contact) {
		boolean result = false;
		
		if(contact != null && validateContact(contact)) {
			database.connect();
			Boolean ifNull = database.checkIfExisting(contact);
			if(ifNull) result = database.insert(contact);
			database.disconnect();
		}
		
		return result;
	}
	
	/* Checks trough every form information if the value is in correct format. */
	private boolean validateContact(Contact c) {
		System.out.println("FirstName: " + validateString(c.getFirstName(), false));
		System.out.println("Email: " + validateEmail(c.getEmail()));
		System.out.println("Nro: " + validateInt(c.getPhoneNro()));
		
		if(validateString(c.getFirstName(), false) && validateString(c.getLastName(), false) &&
				validateString(c.getCity(), true) && validateInt(c.getPhoneNro()) &&
				validateEmail(c.getEmail())) {
			return true;
		}
		
		System.out.println("Validate didn't succeed.");
		return false;
	}
	
	/* Checks whether string includes anything besides letters between a-ö.
	 * Checks also if string is allowed to be null.
	 * Returns true or false depending on result. */
	private boolean validateString(String s, boolean canNull) {
		if(canNull && s.equals("null")) return true;
		
		s = s.toLowerCase();
		char[] chars = s.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			if(!(chars[i] >= 'a' && chars[i] <= 'ö')) return false;
		}
		
		return true;
	}
	
	/* Checks whether string includes anything besides numbers and +.
	 * Makes sure string is at least 7 characters long and 15 characters at most.
	 * Checks also if string is null.
	 * Returns true or false depending on result. */
	private boolean validateInt(String s) {
		if(s.equals("null")) return true;
		
		s = s.replaceAll(" ", "");
		return s.matches("^[+0-9|0-9]{7,15}");
	}
	
	/* Checks whether string is in valid email form and InternetAddress' validate()-method checks whether
	 * email address is valid address.
	 * Returns true or false depending on result. */
	private boolean validateEmail(String s) {
		try {
			InternetAddress emailAddr = new InternetAddress(s);
			emailAddr.validate();
			
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
