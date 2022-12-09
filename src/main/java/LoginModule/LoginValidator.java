package LoginModule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginValidator implements ILoginValidator {

	@Override
	public boolean checkExistingUser(String username) {
		
		if( username == null || username.isEmpty()) {
			return false;	
		}
		
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json")) {
           
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            
            // get user list from JSON object
            JSONArray userList = (JSONArray) jsonObject.get("users");
            
            // loop through user list to find a match with entered username
            for(Object user: ((JSONArray)userList)) {
            	
            	JSONObject usJsonObject = (JSONObject) user;
            	String usernameString = (String)usJsonObject.get("username");
            
            	if(username.equals(usernameString)) {
            		return true;		
            	}
            	
            }
 
        } catch (FileNotFoundException e) {
        	
            e.printStackTrace();
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        } catch (ParseException e) {
        	
            e.printStackTrace();
        }
		
		return false;
	}
	
	@Override
	public boolean authenticateUser(String username, String password) {
		
		if(username == null || password == null || username.isEmpty() || password.isEmpty()) {
			return false;
		}
	
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json")) {
           
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            
            // get user list from JSON object
            JSONArray userList = (JSONArray) jsonObject.get("users");
            
            // loop through user list to find a match with entered username
            for(Object user: ((JSONArray)userList)) {
            	
            	JSONObject usJsonObject = (JSONObject) user;
            	String usernameString = (String)usJsonObject.get("username");
            
            	// if username found, then get password
            	if(username.equals(usernameString)) {
            		String passwordString = (String)usJsonObject.get("password");
            		
            		// check if password matches
            		if(password.equals(passwordString)) {
            			// return true if username and password match found in file
            			return true;
            		}
            	}
            	
            }
 
        } catch (FileNotFoundException e) {
        	
            e.printStackTrace();
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        } catch (ParseException e) {
        	
            e.printStackTrace();
        }
		
		return false;
	}
	
}
