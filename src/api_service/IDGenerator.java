package api_service;
import java.util.*;
public class IDGenerator {
	Random rand = new Random();
	SQLapi sql = new SQLapi();
	String chars = "0123456789";
	StringBuilder sb = new StringBuilder(10);
	String bulk = "", idd = "";
	
	public String generator(String entity) {
		String temp ;
		for(int i = 0; i < 10; i++) {
			bulk = bulk + chars.charAt(rand.nextInt(chars.length()));
		}
		sb.append(bulk);
		if(entity.equals("book")) {
			idd = "bid".concat(sb.toString());
		}
		else if(entity.equals("member")) {
			idd = "UEP-".concat(sb.toString());
		}
		else if(entity.equals("booksissued")) {
			idd = "IID".concat(sb.toString());
		}
		if(!sql.verifyDuplicateID(entity, idd)) {
			temp = idd;
			bulk = "";
			idd = "";
			sb.delete(0, 10);
			return temp;
		}
		return null;
	}
}
