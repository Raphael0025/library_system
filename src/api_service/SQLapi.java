package api_service;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class SQLapi {
	private Connection con = null;
    private Statement st = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public void SQLCreate(String tbl, String id, String _2nd, String _3rd, String _4th, String _5th, String _6th, String _7th, String _8th) {
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

            JOptionPane.showMessageDialog(null, "Successfully Inserted");
            //SQLGetData();
    	}catch(SQLException e) {}
    }
    
    public String[] SQLRead(String tbl, String id) {
    	String[] arr = {};
    	String sql;
    	try {
    		if(tbl.equals("book")) {
    			sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `book_id` LIKE '%").concat(id).concat("%' OR `title` LIKE '%").concat(id).concat("%';");
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
    		}else if(tbl.equals("booksissued")) {
    			sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `issue_id` LIKE '%").concat(id).concat("%' OR `book_id` LIKE '%").concat(id).concat("%' OR `member_name` LIKE '%").concat(id).concat("%';");
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
    		}else {
    			sql = "SELECT * FROM `tbl_".concat(tbl).concat("` WHERE `id` LIKE '%").concat(id).concat("%';");
    			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
    		}
    		int i = 0;
            while(rs.next()) {
            	arr[i] = rs.getString(i+1);
            	i += 1;
            }
    		
    		return arr;
    	} catch(SQLException e) {
    		JOptionPane.showMessageDialog(null, "No Result Found!", "Error 404", JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return arr;
    }
    
    public void SQLUpdate(String tbl, String id, String[] arr){
    	String query;
    	try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            st = con.createStatement();
            if(tbl.equals("book")) {
            	query = "UPDATE `tbl_".concat(tbl).concat("` SET `book_type`='").concat(arr[0]).concat("',`title`='").concat(arr[1]).concat("',`author`=").concat(arr[2]).concat("',`published_date`=").concat(arr[3]).concat("',`shelf_num`=").concat(arr[4]).concat("',`status`=").concat(arr[5]).concat(" WHERE `book_id` = ").concat(id);
            
            }else if(tbl.equals("booksissued")) {
            	query = "UPDATE `tbl_".concat(tbl).concat("` SET `book_id`='").concat(arr[0]).concat("',`book_title`='").concat(arr[1]).concat("',`member_name`=").concat(arr[2]).concat("',`issue_date`=").concat(arr[3]).concat("',`return_date`=").concat(arr[4]).concat("',`fine_fee`=").concat(arr[5]).concat("',`status`=").concat(arr[6]).concat(" WHERE `issue_id` = ").concat(id);
                
            }else {
            	query = "UPDATE `tbl_".concat(tbl).concat("` SET `name`='").concat(arr[0]).concat("',`type`='").concat(arr[1]).concat("',`age`=").concat(arr[2]).concat("',`address`=").concat(arr[3]).concat("',`mobile_no`=").concat(arr[4]).concat("',`email`=").concat(arr[5]).concat("',`password`=").concat(arr[6]).concat(" WHERE `id` = ").concat(id);
                
            }
            if((st.executeUpdate(query)) == 1)
            {
                JOptionPane.showMessageDialog(null, "Record Updated Successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Record Update Unsuccessful");
            }
            //SQLGetData();
        }catch(SQLException e){}
    }
    
    public void SQLDelete(String tbl, String _id) {
    	String sql;
    	try{
    		if(tbl.equals("book")) {
    			sql = "DELETE FROM `".concat(tbl).concat("` WHERE `book_id` = ?");
    		} else {
    			sql = "DELETE FROM `".concat(tbl).concat("` WHERE `id` = ?");
    		}
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);

            ps.setString(1, _id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully Deleted Records!");
            //SQLGetData();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void GetData(String tbl, DefaultTableModel dtm) {
    	String sql;
    	try{
            sql = "SELECT * FROM ".concat(tbl);
            con = DriverManager.getConnection("jdbc:mysql://localhost/laboratory_system","root","");
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
            	while(rs.next()){
            		String _1 = rs.getString(1);
                    String _2 = rs.getString(2);
                    String _3 = rs.getString(3);
                    String _4 = rs.getString(4);
                    String _5 = rs.getString(5);
                    String _6 = rs.getString(6);
                    String _8 = rs.getString(8);
                    if(dtm.toString().equals("modelInfo")) {
                    	dtm.addRow(new Object[]{_3, _5, _6, _8});
                    } else if(dtm.toString().equals("modelUP")){
                    	dtm.addRow(new Object[]{_2, _3, _5, _6, _8});
                    }else {
                    	dtm.addRow(new Object[]{_1, _2, _3, _4, _5, _6, _8});
                    }
                }
            }else {
            	while(rs.next()){
            		String _1 = rs.getString(1);
                    String _2 = rs.getString(2);
                    String _3 = rs.getString(3);
                    String _5 = rs.getString(5);
                    String _6 = rs.getString(6);
                	dtm.addRow(new Object[]{_1, _3, _2, _6, _5});
                }
            }
           
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL Error: No Results Found!");
        }
    }
    
    public boolean verifyDuplicate(String tbl, String pass) {
    	String sql;
    	boolean val;
    	try {
			sql = "SELECT `password` FROM `tbl_".concat(tbl).concat("` WHERE `password` LIKE '%").concat(pass).concat("%';");
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_system","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
    		
    		val = rs.next();
    		return val;
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
    		String sql = "SELECT COUNT(*) FROM `tbl_booksissued` WHERE status LIKE 'over due';";
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
