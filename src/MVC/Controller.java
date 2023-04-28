package MVC;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import components.*;
import custom.*; 
import screens.*;

@SuppressWarnings("serial")
public class Controller extends MyClass{
	mainApp mp;
	login lp;
	Dashboard dashB;
	topBar tb;
	sideBar sb;
	memberList member_list;
	issuedBook ib;
	bookShelf bs;
	promptFrame pf;
	M_RUD mr;
	NewMemberRecord nmr;
	
	public String[] arr;
	
	public Controller(mainApp mp, login lp, Dashboard dash, topBar tBar, sideBar sb, memberList mbList){
		this.mp = mp;
		this.lp = lp;
		this.dashB = dash;
		this.tb = tBar;
		this.sb = sb;
		this.member_list = mbList;
//		this.bs = bookS;
//		this.ib = issued;
		
		lp.loginBtn.addActionListener(this);
		sb.dash.addActionListener(this);
		sb.member.addActionListener(this);
		sb.books.addActionListener(this);
		sb.issued.addActionListener(this);
		lp.member.addActionListener(this);
		lp.staff.addActionListener(this);
		mp.out.addMouseListener(this);
		tb.arrow.addMouseListener(this);
		member_list.query.addActionListener(this);
		member_list.addMember.addActionListener(this);
		
		int len = dashB.def.length;
		arr = new String[len];
		arr = dashB.def;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
        String btn = pressed.getText();
        
		switch(btn) {
			case "LOGIN" :
				mp.setSize(1300, 750);
				mp.setLocationRelativeTo(null);
				lp.setVisible(false);
				tb.setVisible(true);
				dashB.setVisible(true);
				sb.setVisible(true);
				
				if(lp.admin) {
					tb.Title("ADMIN");
					mp.setTitle("Library Management - Dashboard(ADMIN)");
					for(int i = 0; i < 6; i++) {
						dashB.setContent(i, arr[i]);
					}
				} else {
					tb.Title("MEMBER");
					mp.setTitle("Library Management - Dashboard(MEMBER)");
				}
				
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
				sb.Default(sb.member, "members");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				member_list.setVisible(false);
				dashB.setVisible(true);
				
				for(int i = 0; i < 6; i++) {
					//dashB.setVal(i, def[i]);
					
				}
				
				break;
			case "MEMBERS":
				sb.Default(sb.dash, "dash");
				sb.active(sb.member, "members");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				member_list.setVisible(true);
				dashB.setVisible(false);
				
				break;
			case "BOOK SHELF":
				sb.Default(sb.dash, "dash");
				sb.Default(sb.member, "members");
				sb.active(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				
				break;
			case "ISSUED":
				sb.active(sb.issued, "book");
				sb.Default(sb.member, "members");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.dash, "dash");
				
				break;
			case "Search Member":
				pf = new promptFrame("Search Member", 700, 720);
				pf.setVisible(true);
				mr = new M_RUD(pf.getWidth(), pf.getHeight());
				mr.edit.addActionListener(this);
				pf.add(mr);
				break;
				
			case "Add New Member":
				pf = new promptFrame("Create New Record", 550, 550);
				pf.setVisible(true);
				nmr = new NewMemberRecord(pf.getWidth(), pf.getHeight());
				nmr.create.addMouseListener(this);
				//nmr.edit.addActionListener(this);
				
				pf.add(nmr);
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
		}
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (e.getSource() == mp.out) {
			mp.out.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if(e.getSource() == nmr.create) {
			nmr.create.setIcon(new ImageIcon("src\\assets\\red-edit.png"));
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (e.getSource() == mp.out) {
			mp.out.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}else if(e.getSource() == nmr.create) {
			nmr.create.setIcon(new ImageIcon("src\\assets\\white-edit.png"));
		}
	}
	
	public void logout() {
		mp.setSize(500, 650);
		mp.setLocationRelativeTo(null);
        mp.setTitle("Library Management");
        lp.setVisible(true);
		dashB.setVisible(false);
		mp.dropdown.setVisible(false);
		tb.setVisible(false);
		sb.setVisible(false);
		for(int i = 0; i < 6; i++) {
			dashB.setContent(i, "");
		}
	}
}
