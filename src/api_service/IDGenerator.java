package api_service;
import java.util.*;
public class IDGenerator {
	Random rand = new Random();
	SQLapi sql = new SQLapi();
	String chars = "0123456789";
	StringBuilder sb = new StringBuilder(10);
	private String id = "UEP-";
	
	public void generator(String entity) {
		
		for(int i = 0; i < 10; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		if(!sql.verifyDuplicateID(entity, sb.toString())) {
			id += sb.toString();
		}
	}
	public String GetID() {
		return id;
	}
}
