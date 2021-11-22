package com.databasecontroller.DatabaseController.Controller;

import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.databasecontroller.DatabaseController.Model.Contact;

@RestController
public class APIController {
	
	/* API set up. */

	private final ContactDataController controller;
	
	/* Constructor. Fetches instance of data controller. */
	public APIController() {
		controller = ContactDataController.getInstance();
	}
	
	/* API POST call by '/addContacts', which delivers body information in Map object.
	 * Method returns true or false, depending on result.
	 * Method can take single json or a list of jsons. Map is converted to JSONObject.
	 * JSONObject is run to ContactDataController-class to save as Contact objects. */
	@PostMapping(path = "/addContacts", consumes = "application/json")
	public @ResponseBody ResponseEntity<Boolean> addContact(@RequestBody Map input) {
		boolean result = true;
		JSONObject ob = new JSONObject(input);
		try {
			JSONArray obAr = ob.getJSONArray("Contacts");
			for(int i = 0; i < obAr.length(); i++) {
				JSONObject temp = obAr.getJSONObject(i);
				boolean b = controller.save(new Contact(temp.getString("FirstName"), temp.getString("LastName"), temp.getString("Email"), temp.getString("PhoneNumber"), temp.getString("City")));
				if(b == false) result = false;
			}
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@GetMapping("/order")
	  public @ResponseBody ResponseEntity<Contact> getOrder() {

	    Contact contact = new Contact("Nimi", "Nimi", "Email", "0000", "Kaupunki");

	    return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	  }
}
