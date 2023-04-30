package api_service;

import java.util.*;

public class PasswordGenerator {
	Random rand = new Random();
	SQLapi sql = new SQLapi();
	String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789!@#$&*?";
	StringBuilder sb = new StringBuilder(8);
	private String password = null;
	
	public void generator(String entity) {
		boolean flag = true;
		while(!flag) {
			for(int i = 0; i < 8; i++) {
				sb.append(chars.charAt(rand.nextInt(chars.length())));
			}
			password = sb.toString();
			if(!sql.verifyDuplicate(entity, password)) {
				flag = false;
			}
		}
		
	}
	
	public String GetPassword() {
		return password;
	}
}
