package MVC;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;
import java.awt.font.*;
import java.util.*;
import java.util.Date;

import javax.swing.*;
import custom.*; 
import api_service.*;
import screens.*;
import java.text.*;
import java.time.*;

@SuppressWarnings("serial")
public class Controller extends MyClass{
	mainApp mp;
	login lp;
	Dashboard dashB;
	topBar tb;
	sideBar sb;
	memberList m_list;
	issuedBook ib;
	bookShelf bs;
	promptFrame pf;
	M_RUD mr;
	NewMemberRecord nmr;
	NewStaff ns;
	NewBookRecord nbr;
	B_RUD brud;
	E_RUD erd;
	BookRent br;
	ViewIssuedBook vib;
	UserProfile up;
	MemberDashboard md;
	BookInfo bki;
	SQLapi sql = new SQLapi();
	DashResult dr = new DashResult();
	PasswordGenerator pg = new PasswordGenerator();
	IDGenerator ig = new IDGenerator();
	CustomTextField ctf = new CustomTextField();
	EmployeeList employee;
	public String[] arr;
	String globeID;
	
	public Controller(mainApp mp, MemberDashboard memberDash, UserProfile up, login lp, Dashboard dash, topBar tBar, sideBar sb, memberList mbList, bookShelf bs, issuedBook ib, EmployeeList employee){
		this.mp = mp;
		this.lp = lp;
		this.dashB = dash;
		this.tb = tBar;
		this.sb = sb;
		this.m_list = mbList;
		this.bs = bs;
		this.md = memberDash;
		this.ib = ib;
		this.up = up;
		this.employee = employee;
		
		lp.loginBtn.addActionListener(this);
		sb.dash.addActionListener(this);
		sb.member.addActionListener(this);
		sb.books.addActionListener(this);
		sb.empl.addActionListener(this);
		sb.issued.addActionListener(this);
		lp.member.addActionListener(this);
		lp.staff.addActionListener(this);
		mp.out.addMouseListener(this);
		tb.arrow.addMouseListener(this);
		m_list.query.addActionListener(this);
		m_list.addMember.addActionListener(this);
		bs.addB.addActionListener(this);
		bs.query.addActionListener(this);
		employee.addStaff.addActionListener(this);
		employee.query.addActionListener(this);
		ib.query.addActionListener(this);
		tb.name.addMouseListener(this);
		tb.home.addMouseListener(this);
		md.query.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
        String btn = pressed.getText();
		switch(btn) {
			case "LOGIN" :
				String user = lp.tf.getText();
				@SuppressWarnings("deprecation") 
				String pass = lp.pf.getText();
				this.globeID = user;
				
				if(lp.admin) {
					if(sql.SQLAccount("employees", user, pass)) {
						mp.setSize(1300, 750);
						mp.setLocationRelativeTo(null);
						lp.setVisible(false);
						tb.setVisible(true);
						if(globeID.equals("admin")) {
							tb.Title("ADMIN");
						} else {
							tb.Title("STAFF");
						}
						mp.setTitle("Library Management - Dashboard(ADMIN)");
						DashCont();
						
						dashB.setVisible(true);
						sb.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Sorry Wrong Credentials", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if(sql.SQLAccount("member", user, pass)) {
						mp.setSize(1300, 750);
						mp.setLocationRelativeTo(null);
						lp.setVisible(false);
						tb.setVisible(true);
						tb.Title("MEMBER");
						mp.setTitle("Library Management - Dashboard(MEMBER)");
						md.setVisible(true);
						tb.home.setVisible(true);
						
						sql.GetData("book", md.model, "", "");
					}else {
						JOptionPane.showMessageDialog(null, "Sorry Wrong Credentials", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				lp.tf.setText("Enter Staff ID");
				lp.pf.setText("");
				
				break;
			case "STAFF":
				lp.changeDefault(lp.staff, lp.member);
				lp.tf.setText("Enter Staff ID");
				lp.admin = true;
				break;
			case "MEMBER":
				lp.changeDefault(lp.member, lp.staff);
				lp.tf.setText("Enter Member ID");
				lp.admin = false;
				break;
			case "DASHBOARD":
				sb.active(sb.dash, "dash");
				sb.Default(sb.member, "member");
				sb.Default(sb.empl, "employee");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				dashB.setVisible(true);
				m_list.setVisible(false);
				employee.setVisible(false);
				bs.setVisible(false);
				ib.setVisible(false);
				up.setVisible(false);
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				DashCont();
				break;
			case "MEMBERS":
				sb.Default(sb.dash, "dash");
				sb.active(sb.member, "member");
				sb.Default(sb.empl, "employee");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				m_list.setVisible(true);
				dashB.setVisible(false);
				employee.setVisible(false);
				bs.setVisible(false);
				ib.setVisible(false);
				up.setVisible(false);
				m_list.search.setText("Search");
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				sql.GetData("member", m_list.model, "", "");
				
				break;
			case "EMPLOYEES":
				sb.Default(sb.dash, "dash");
				sb.Default(sb.member, "member");
				sb.active(sb.empl, "employee");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				m_list.setVisible(false);
				employee.setVisible(true);
				dashB.setVisible(false);
				bs.setVisible(false);
				ib.setVisible(false);
				up.setVisible(false);
				employee.search.setText("Search");
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				sql.GetData("employees", employee.model, "", "");
				
				break;
			case "BOOK SHELF":
				sb.Default(sb.dash, "dash");
				sb.Default(sb.member, "member");
				sb.Default(sb.empl, "employee");
				sb.active(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				bs.setVisible(true);
				dashB.setVisible(false);
				m_list.setVisible(false);
				employee.setVisible(false);
				ib.setVisible(false);
				up.setVisible(false);
				bs.search.setText("Search");
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				sql.GetData("book", bs.model, "", "");
				
				break;
			case "ISSUED":
				sb.active(sb.issued, "book");
				sb.Default(sb.member, "member");
				sb.Default(sb.empl, "employee");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.dash, "dash");
				ib.setVisible(true);
				dashB.setVisible(false);
				m_list.setVisible(false);
				employee.setVisible(false);
				bs.setVisible(false);
				ib.search.setText("Search");
				up.setVisible(false);
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				sql.GetData("booksissued", ib.model, "", "");
				
				break;
			case "Search Member":
				String[] arr = sql.SQLRead("member", m_list.search.getText());
				if(arr[0] == null) {
					JOptionPane.showMessageDialog(null, "No Result Found!", "Error 404", JOptionPane.ERROR_MESSAGE);
				} else {
					mr = new M_RUD(700, 720, arr);
					mr.setVisible(true);
					
					for(int i = 0; i < mr.tf.length; i++) {
						ctf.placeHolder(mr.tf[i], arr[i]);
					}
					sql.GetData("booksissued", mr.modelInfo, mr.tf[1].getText(), "modelInfo");
				}
					
				break;
			case "Search Staff":
				String[] arr4 = sql.SQLRead("employees", employee.search.getText());
				if(arr4[0] == null) {
					JOptionPane.showMessageDialog(null, "No Result Found!", "Error 404", JOptionPane.ERROR_MESSAGE);
				} else {
					erd = new E_RUD("Search Member", 700, 520, arr4);
					erd.setVisible(true);
					
					for(int i = 0; i < erd.tf.length; i++) {
						ctf.placeHolder(erd.tf[i], arr4[i]);
					}
					sql.GetData("employees", employee.model, "", "");
				}
					
				break;
			case "Search Book":
				String[] arr2 = sql.SQLRead("book", bs.search.getText());
				if(arr2[0] == null) {
					JOptionPane.showMessageDialog(null, "No Result Found!", "Error 404", JOptionPane.ERROR_MESSAGE);
				} else {
					brud = new B_RUD(600, 480, arr2);
					brud.setVisible(true);
					
					for(int i = 0; i < brud.tf.length; i++) {
						ctf.placeHolder(brud.tf[i], arr2[i]);
					}
				}
				sql.GetData("book", bs.model, "", "");
				break;
			case "Add New Member":
				nmr = new NewMemberRecord(550, 550);
				nmr.setVisible(true);
				sql.GetData("member", m_list.model, "", "");
				
				break;
			case "Add New Staff":
				ns = new NewStaff(550, 550);
				ns.setVisible(true);
				sql.GetData("employees", employee.model, "", "");
				
				break;
			case "Add New Book":
				nbr = new NewBookRecord(600, 450);
				nbr.setVisible(true);
				sql.GetData("book", bs.model, "", "");
				
				break;
			case "Search Issued Book":
				String[] arr3 = sql.SQLRead("booksissued", ib.search.getText());
				if(arr3[0] == null) {
					JOptionPane.showMessageDialog(null, "No Result Found!", "Error 404", JOptionPane.ERROR_MESSAGE);
				} else {
					vib = new ViewIssuedBook(600, 550, arr3);
					vib.setVisible(true);
					sql.GetData("booksissued", ib.model, "", "");
					
				}
				
				break;
			case "SEARCH":
				String[] arr5 = sql.SQLRead("book", md.searchQry.getText());
				if(arr5[0] == null) {
					JOptionPane.showMessageDialog(null, "No Result Found!", "Error 404", JOptionPane.ERROR_MESSAGE);
				} else {
					bki= new BookInfo(600, 370, arr5);
					bki.setVisible(true);
					sql.GetData("book", md.model, "", "");
					
				}
				break;
			case "Edit Staff":
				String id6 = up.tf[0].getText();
				String[] temp3 = new String[7];
				for(int i = 0; i < temp3.length; i++) {
					temp3[i] = up.tf[i+1].getText();
				}
				sql.SQLUpdate("employees", id6, temp3, m_list.model);
				
				break;
			case "Delete Staff":
				String id7 = up.tf[0].getText();
				sql.SQLDelete("employees", id7, m_list.model);
				
				break;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == tb.arrow) {
			if(mp.dropdown.isVisible()) {
				mp.dropdown.setVisible(false);
			} else { 
				mp.dropdown.setVisible(true);
			}
		} else if (e.getSource() == mp.out) {
			 logout();
		} else if (e.getSource() ==  tb.name) {
			mp.setTitle("Library Management - User Profile");
			sb.Default(sb.issued, "book");
			sb.Default(sb.member, "member");
			sb.Default(sb.empl, "employee");
			sb.Default(sb.books, "shelf");
			sb.Default(sb.dash, "dash");
			up.setVisible(true);
			ib.setVisible(false);
			md.setVisible(false);
			employee.setVisible(false);
			dashB.setVisible(false);
			m_list.setVisible(false);
			bs.setVisible(false);
			
			up.modify.addActionListener(this);
			up.modify2.addActionListener(this);
			up.delS.addActionListener(this);
			
			String[] arr;
			if(tb.name.getText().equals("ADMIN")) {
				up.setBounds(150, 70, 1120, 630, 20);
				up.scrollTable.setVisible(false);
				up.delS.setVisible(false);
				arr = sql.SQLRead("employees",globeID);
				
			}else if(tb.name.getText().equals("STAFF")) {
				up.setBounds(150, 70, 1120, 630, 20);
				up.scrollTable.setVisible(false);
				up.delS.setVisible(true);
				arr = sql.SQLRead("employees",globeID);
				
			}else {
				up.setBounds(10, 70, 1260, 630, 20);
				up.scrollTable.setVisible(true);
				arr = sql.SQLRead("member",globeID);
			}
			for(int i = 0; i < up.tf.length; i++) {
				up.tf[i].setText(arr[i]);
				ctf.placeHolder(up.tf[i], arr[i]);
			}
		} 
		else if (e.getSource() == tb.home) {
			md.setVisible(true);
			up.setVisible(false);
		}
	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void mouseEntered(MouseEvent e) {
		Font font;
		
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (e.getSource() == mp.out) {
			font = mp.out.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			mp.out.setFont(font.deriveFont(attributes));
			mp.out.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		} else if(e.getSource() == tb.name) {
			font = tb.name.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			tb.name.setFont(font.deriveFont(attributes));
			tb.name.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if(e.getSource() == tb.home) {
			font = tb.home.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			tb.home.setFont(font.deriveFont(attributes));
			tb.home.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void mouseExited(MouseEvent e) {
		Font font;
		
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (e.getSource() == mp.out) {
			font = mp.out.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, -1);
			mp.out.setFont(font.deriveFont(attributes));
			mp.out.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if(e.getSource() == tb.name) {
			font = tb.name.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, -1);
			tb.name.setFont(font.deriveFont(attributes));
			tb.name.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if(e.getSource() == tb.home) {
			font = tb.home.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, -1);
			tb.home.setFont(font.deriveFont(attributes));
			tb.home.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	public void logout() {
		mp.setSize(500, 650);
		mp.setLocationRelativeTo(null);
        mp.setTitle("Library Management");
        up.setVisible(false);
        lp.setVisible(true);
		mp.dropdown.setVisible(false);
		tb.setVisible(false);
		sb.setVisible(false);
		dashB.setVisible(false);
		ib.setVisible(false);
		md.setVisible(false);
		m_list.setVisible(false);
		tb.home.setVisible(false);
		bs.setVisible(false);
		for(int i = 0; i < 6; i++) {
			dashB.setContent(i, "");
		}
		sb.active(sb.dash, "dash");
		sb.Default(sb.member, "member");
		sb.Default(sb.empl, "employee");
		sb.Default(sb.books, "shelf");
		sb.Default(sb.issued, "book");
	}
	
	public void DashCont() {
		dashB.setContent(0, String.valueOf(dr.GetTotalBooks()));
		dashB.setContent(1, String.valueOf(dr.GetTotalIssued()));
		dashB.setContent(2, String.valueOf(dr.GetTotalAvailable()));
		dashB.setContent(3, String.valueOf(dr.GetLateBooks()));
		dashB.setContent(4, String.valueOf(dr.GetTotalStudent()));
		dashB.setContent(5, String.valueOf(dr.GetTotalTeachers()));
	}
}
