package api_service;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

import screens.*;

public class SQLapi {
	private Connection con = null;
    private Statement st = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    EmployeeList empl = new EmployeeList();
    
    public boolean SQLAccount(String tbl, String id, String pass) {
    	String sql;
    	boolean val = false;
    	try {
	    	if(tbl.equals("employees")) {
	    		sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `id` LIKE '%").concat(id).concat("%' AND `password` LIKE '%").concat(pass).concat("%';");
				
	    	} else {
	    		sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `id` LIKE '%").concat(id).concat("%' AND `password` LIKE '%").concat(pass).concat("%';");
				
	    	}
	    	con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            val = rs.next();
            if(val) {
            	return true;
            }
    	} catch(SQLException e) {}
    	return val;
    }
    
    public void SQLCreate(String tbl, String id, String _2nd, String _3rd, String _4th, String _5th, String _6th, String _7th, String _8th, DefaultTableModel dtm) {
    	String sql;
    	try {
    		if (tbl.equals("book")) {
    			sql = "INSERT INTO `tbl_".concat(tbl).concat("` (`book_id`, `book_type`, `title`, `author`, `published_date`, `shelf_num`, `status`) VALUES(?, ?, ?, ?, ?, ?, ?)");
            
    		} else if (tbl.equals("booksissued")) {
    			sql = "INSERT INTO `tbl_".concat(tbl).concat("` (`issue_id`, `book_id`, `book_title`, `member_name`, `issue_date`, `return_date`, `fine_fee`, `status`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
    		
    		} else {
    			sql = "INSERT INTO `tbl_".concat(tbl).concat("` (`id`, `name`, `type`, `age`, `address`, `mobile_no`, `e-mail`, `password`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        		
    		}
    		
    		con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            
            ps.setString(1, id);
            ps.setString(2, _2nd);
            ps.setString(3, _3rd);
            ps.setString(4, _4th);
            ps.setString(5, _5th);
            ps.setString(6, _6th);
            ps.setString(7, _7th);
            if(_8th != null) 
            	ps.setString(8, _8th);
            
            ps.execute();
            GetData(tbl, dtm, "", "");
            JOptionPane.showMessageDialog(null, "Successfully Inserted");
    	}catch(SQLException e) {
    		System.out.println(e);
    	}
    }
    
    public String[] SQLRead(String tbl, String id) {
    	String sql, rs1 = null, rs2 = null, rs3 = null, rs4 = null,rs5 = null, rs6 = null,rs7 = null,rs8 = null;
    	String[] arr;
    	try {
    		if(tbl.equals("book")) {
    			sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `book_id` LIKE '%").concat(id).concat("%' OR `title` LIKE '%").concat(id).concat("%';");
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while(rs.next()) {
					 rs1 = rs.getString(1);
					 rs2 = rs.getString(2);
					 rs3 = rs.getString(3);
					 rs4 = rs.getString(4);
					 rs5 = rs.getString(5);
					 rs6 = rs.getString(6);
					 rs7 = rs.getString(7);
	            }
	            arr = new String[]{rs1, rs2, rs3, rs4, rs5,rs6, rs7};
	            return arr;
    		}else if(tbl.equals("booksissued")) {
    			sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `issue_id` LIKE '%").concat(id).concat("%' OR `book_id` LIKE '%").concat(id).concat("%' OR `book_title` LIKE '%").concat(id).concat("%' OR `member_name` LIKE '%").concat(id).concat("%';");
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while(rs.next()) {
					 rs1 = rs.getString(1);
					 rs2 = rs.getString(2);
					 rs3 = rs.getString(3);
					 rs4 = rs.getString(4);
					 rs5 = rs.getString(5);
					 rs6 = rs.getString(6);
					 rs7 = rs.getString(7);
					 rs8 = rs.getString(8);
	            }
	            arr = new String[]{rs1, rs2, rs3, rs4, rs5,rs6, rs7, rs8};
	            return arr;
    		}else {
    			sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `id` LIKE '%").concat(id).concat("%' OR `name` LIKE '%").concat(id).concat("%';");
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
	            while(rs.next()) {
					 rs1 = rs.getString(1);
					 rs2 = rs.getString(2);
					 rs3 = rs.getString(3);
					 rs4 = rs.getString(4);
					 rs5 = rs.getString(5);
					 rs6 = rs.getString(6);
					 rs7 = rs.getString(7);
					 rs8 = rs.getString(8);
	            }
	            arr = new String[]{rs1, rs2, rs3, rs4, rs5,rs6, rs7, rs8};
	            return arr;
    		}
    		
    	} catch(SQLException e) {}
    	return null;
    	
    }
    
    public void SQLUpdate(String tbl, String id, String[] arr, DefaultTableModel dtm){
    	String query;
    	try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            st = con.createStatement();
            if(tbl.equals("book")) {
            	query = "UPDATE `tbl_".concat(tbl).concat("` SET `book_type`='").concat(arr[0]).concat("',`title`='").concat(arr[1]).concat("',`author`='").concat(arr[2]).concat("',`published_date`='").concat(arr[3]).concat("',`shelf_num`='").concat(arr[4]).concat("',`status`='").concat(arr[5]).concat("' WHERE `book_id` = '").concat(id).concat("'");
            
            }else if(tbl.equals("booksissued")) {
            	query = "UPDATE `tbl_".concat(tbl).concat("` SET `book_id`='").concat(arr[0]).concat("',`book_title`='").concat(arr[1]).concat("',`member_name`='").concat(arr[2]).concat("',`issue_date`='").concat(arr[3]).concat("',`return_date`='").concat(arr[4]).concat("',`fine_fee`='").concat(arr[5]).concat("',`status`='").concat(arr[6]).concat("' WHERE `issue_id` = '").concat(id).concat("';");;
                
            }else {
            	query = "UPDATE `tbl_".concat(tbl).concat("` SET `name`='").concat(arr[0]).concat("',`type`='").concat(arr[1]).concat("',`age`='").concat(arr[2]).concat("',`address`='").concat(arr[3]).concat("',`mobile_no`='").concat(arr[4]).concat("',`e-mail`='").concat(arr[5]).concat("',`password`='").concat(arr[6]).concat("' WHERE `id` LIKE '%").concat(id).concat("%';");
                
            }
            if((st.executeUpdate(query)) == 1){
                JOptionPane.showMessageDialog(null, "Record Updated Successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Record Update Unsuccessful");
            }
            
        }catch(SQLException e){
        	System.out.println(e);
        }
    	GetData(tbl, dtm, "", "");
    }
    
    public void SQLDelete(String tbl, String _id, DefaultTableModel dtm) {
    	String sql;
    	try{
    		if(tbl.equals("book")) {
    			sql = "DELETE FROM `tbl_".concat(tbl).concat("` WHERE `book_id` = '").concat(_id).concat("';");
    		} else {
    			sql = "DELETE FROM `tbl_".concat(tbl).concat("` WHERE `id` = '").concat(_id).concat("';");
    		}
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);

            int res = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Select an Option...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            
            if(res == JOptionPane.YES_OPTION) {
            	ps.executeUpdate();
            	JOptionPane.showMessageDialog(null, "Successfully Deleted From Records!", "Prompt!", JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(null, "Record Deletion Cancelled!", "Prompt!", JOptionPane.INFORMATION_MESSAGE);
            }
            
            GetData(tbl, dtm, "", "");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean verifyMember(String memberName) {
    	String sql = "SELECT EXISTS(SELECT * FROM `tbl_member` WHERE `name` LIKE '%".concat(memberName).concat("%');");
    	try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root",""); 
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1) != 0;
				
		} catch (SQLException e) {}
       return false;
    }
    
    public void GetData(String tbl, DefaultTableModel dtm, String name, String pan) {
    	String sql;
    	try{
            sql = "SELECT * FROM tbl_".concat(tbl);
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            for(int i = dtm.getRowCount() - 1; i >= 0; i--){
                dtm.removeRow(i);
            }
            if(tbl.equals("book")) {
            	while(rs.next()){
            		String _1 = rs.getString(1);
                    String _2 = rs.getString(2);
                    String _3 = rs.getString(3);
                    String _4 = rs.getString(4);
                    String _5 = rs.getString(5);
                    String _7 = rs.getString(7);
                	dtm.addRow(new Object[]{_1, _2, _3, _4, _5, _7});
                }
            	
            } else if(tbl.equals("booksissued")){
            	String _1 = null, _2 = null, _3 = null, _4 = null, _5 = null, _6 = null, _7 = null, _8 = null;
                    if("modelInfo".equals(pan)) {
                    	sql = "SELECT * FROM tbl_".concat(tbl).concat(" WHERE `member_name` LIKE '").concat(name).concat("';");
                        con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                    	while(rs.next()){
        					 _3 = rs.getString(3);
        					 _5 = rs.getString(5);
        					 _6 = rs.getString(6);
        					 _8 = rs.getString(8);
        					 dtm.addRow(new Object[]{_3, _5, _6, _8});
                    	}
                    	
                    } else if("modelUp".equals(pan)){
                    	sql = "SELECT * FROM tbl_".concat(tbl).concat(" WHERE `member_name` LIKE '%").concat(name).concat("%';");
                        con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                    	while(rs.next()){
        					 _2 = rs.getString(2);
        					 _3 = rs.getString(3);
        					 _5 = rs.getString(5);
        					 _6 = rs.getString(6);
        					 _8 = rs.getString(8);
        					 dtm.addRow(new Object[]{_2, _3, _5, _6, _8});
                    	}
                    	
                    }else {
                    	sql = "SELECT * FROM `tbl_".concat(tbl).concat("`;");
                        con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                    	while(rs.next()){
        					 _1 = rs.getString(1);
        					 _3 = rs.getString(3);
        					 _4 = rs.getString(4);
        					 _5 = rs.getString(5);
        					 _6 = rs.getString(6);
        					 _8 = rs.getString(8);
        					 dtm.addRow(new Object[]{_1, _3, _4, _5, _6, _8});
                    	}
                    	
                    }
            }else {
            	while(rs.next()){
            		String _1 = rs.getString(1);
                    String _2 = rs.getString(2);
                    String _3 = rs.getString(3);
                    String _5 = rs.getString(5);
                    String _6 = rs.getString(6);
                    String _7 = rs.getString(7);
                	dtm.addRow(new Object[]{_1, _3, _2, _5, _6, _7});
                }
            	
            }
           
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL Error: No Results Found! " + e);
        }
    }
    
    public void setBookStatus(String tbl, String stat, String id, DefaultTableModel dtm) {
    	String query;
    	try{
    		switch(tbl) {
    		case "book":
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                query = "UPDATE `tbl_book` SET `status`= ? WHERE `book_id` = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, stat);
                ps.setString(2, id);
                ps.executeUpdate();
                GetData("book", dtm, "", "");
    			break;
    		case "booksissued":
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                query = "UPDATE `tbl_booksissued` SET `status`= ? WHERE `issue_id` = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, stat);
                ps.setString(2, id);
                ps.executeUpdate();
                GetData("booksissued", dtm, "", "");
    			break;
    		}
            
           
        }catch(SQLException e){}
    }
    
    public boolean verifyDuplicatePass(String tbl, String pass) {
    	String sql;
    	boolean val;
    	try {
			sql = "SELECT `password` FROM `tbl_" + tbl + "` WHERE `password` LIKE '" + pass + "';";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		
    		val = rs.next();
    		if(!val) {
            	return false;
            }
    	} catch(SQLException e) {}
    	return true;
    }
    
    public boolean verifyDuplicateID(String tbl, String pass) {
    	String sql;
    	boolean val;
    	String id = "";
    	if(tbl.equals("book"))
    		id = "book_id";
    	else if (tbl.equals("booksissued"))
    		id = "issue_id";
    	else
    		id = "id";
    	try {
    		sql = "SELECT `" + id + "` FROM `tbl_" + tbl + "` WHERE `" + id + "` LIKE '" + pass + "';";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		
    		val = rs.next();
    		if(!val) {
            	return false;
            }
    	} catch(SQLException e) {}
    	return true;
    }
    
    public int getTotal() {
    	try {
			String sql = "SELECT COUNT(*) FROM `tbl_book`;";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		rs.next();
    		return rs.getInt(1);
    	} catch(SQLException e) {}
    	return 0;
    }
    
    public int getTotalIssued() {
    	try {
			String sql = "SELECT COUNT(*) FROM `tbl_booksissued`;";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		rs.next();
    		return rs.getInt(1);
    	} catch(SQLException e) {}
    	return 0;
    }
    
    public int getTotalAvailable() {
    	try {
			String sql = "SELECT COUNT(*) FROM `tbl_book` WHERE status LIKE 'available';";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		rs.next();
    		return rs.getInt(1);
    	} catch(SQLException e) {}
    	return 0;
    }
    
    public int getLateBooks() {
    	try {
    		String sql = "SELECT COUNT(*) FROM `tbl_booksissued` WHERE `status` LIKE '%not returned%' AND `return_date` < NOW();";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		rs.next();
    		return rs.getInt(1);
    	} catch(SQLException e) {}
    	return 0;
    }
    
    public int getTotalStudent() {
    	try {
			String sql = "SELECT COUNT(*) FROM `tbl_member` WHERE type LIKE 'Student';";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		rs.next();
    		return rs.getInt(1);
    	} catch(SQLException e) {}
    	return 0;
    }
    
    public int getTotalTeachers() {
    	try {
			String sql = "SELECT COUNT(*) FROM `tbl_member` WHERE type LIKE 'Teacher';";
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		rs.next();
    		return rs.getInt(1);
    	} catch(SQLException e) {}
    	return 0;
    }

}
