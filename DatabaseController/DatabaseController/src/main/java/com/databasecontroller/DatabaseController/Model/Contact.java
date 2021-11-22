package com.databasecontroller.DatabaseController.Model;

public class Contact {
	/* Contact class represents the objects saved into database. */
	
	private long id;
	private String firstName, lastName, email, phoneNro, city;
	
	/* Constuctor. */
	public Contact(String firstName, String lastName, String email, String phoneNro, String city) {
		this.id = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNro = phoneNro;
		this.city = city;
	}

	public long getId() { return id; }
	
	public String getFirstName() { return firstName; }
	
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	public String getPhoneNro() { return phoneNro; }
	
	public void setPhoneNro(String phoneNro) { this.phoneNro = phoneNro; }
	
	public String getCity() { return city; }
	
	public void setCity(String city) { this.city = city; }
}
