package api_service;

import java.util.*;

public class PasswordGenerator {
	Random rand = new Random();
	SQLapi sql = new SQLapi();
	String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789!@#$&*?";
	StringBuilder sb = new StringBuilder(8);
	private String password = null;
	
	public void generator(String entity) {
		
		for(int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		if(!sql.verifyDuplicatePass(entity, sb.toString())) {
			password = sb.toString();
		}
	}
	
	public String GetPassword() {
		return password;
	}
}
