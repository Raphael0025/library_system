package api_service;
import java.util.*;
public class IDGenerator {
	Random rand = new Random();
	SQLapi sql = new SQLapi();
	String chars = "0123456789";
	StringBuilder sb = new StringBuilder(10);
	private String id = "EUPLS";
	
	public void generator(String entity) {
		boolean flag = true;
		while(!flag) {
			for(int i = 0; i < sb.capacity(); i++) {
				sb.append(chars.charAt(rand.nextInt(chars.length())));
			}
			id += sb.toString();
			if(!sql.verifyDuplicate(entity, id)) {
				flag = false;
			}
		}
	}
	
	public String GetID() {
		return id;
	}
}
