package api_service;

public class DashResult {
	SQLapi sql = new SQLapi();
	
	public int GetTotalBooks() {
		return sql.getTotal();
	}
	
	public int GetTotalIssued() {
		return sql.getTotalIssued();
	}
	
	public int GetTotalAvailable() {
		return sql.getTotalAvailable();
	}
	
	public int GetLateBooks() {
		return sql.getLateBooks();
	}
	
	public int GetTotalStudent() {
		return sql.getTotalStudent();
	}
	
	public int GetTotalTeachers() {
		return sql.getTotalTeachers();
	}
}
