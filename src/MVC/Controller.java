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
	public String[] arr;
	
	public Controller(mainApp mp, login lp, Dashboard dash, topBar tBar, sideBar sb){
		this.mp = mp;
		this.lp = lp;
		this.dashB = dash;
		this.tb = tBar;
		this.sb = sb;
		lp.loginBtn.addActionListener(this);
		sb.dash.addActionListener(this);
		sb.member.addActionListener(this);
		sb.books.addActionListener(this);
		sb.issued.addActionListener(this);
		lp.member.addActionListener(this);
		lp.staff.addActionListener(this);
		
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
				mp.setTitle("Library Management - Dashboard");
				lp.setVisible(false);
				dashB.setVisible(true);
				tb.setVisible(true);
				sb.setVisible(true);
				if(lp.admin) {
					tb.Title("ADMIN");
					for(int i = 0; i < 6; i++) {
						dashB.setContent(i, arr[i]);
					}
				} else {
					tb.Title("MEMBER");
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
				
				for(int i = 0; i < 6; i++) {
					//dashB.setVal(i, def[i]);
					
				}
				
				break;
			case "MEMBERS":
				sb.Default(sb.dash, "dash");
				sb.active(sb.member, "members");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				
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
		}
	}
	
}
