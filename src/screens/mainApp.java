package screens;

import java.awt.*;
import javax.swing.*;
import MVC.Controller;
import custom.*;

@SuppressWarnings("serial")
public class mainApp extends MyFrame{
	public JLabel out = new JLabel("LOGOUT");
	public JPanel log, dashboard, tB, sb, membership, ib, bs, up, memberDash;
	static String name = "Library Management";
	public JPanel dropdown = new JPanel();
	
	public mainApp() {
		super(name, 500, 650);
		init();
	}
	
	public void init() {
		log = new login(getWidth(), getHeight());
		dashboard = new Dashboard();
		sb = new sideBar();
		tB = new topBar();
		membership = new memberList();
		bs = new bookShelf(); 
		ib = new issuedBook();
		up = new UserProfile();
		memberDash = new MemberDashboard();
		
		new Controller(this, ((MemberDashboard)memberDash), ((UserProfile)up), ((login)log), ((Dashboard)dashboard), ((topBar)tB), ((sideBar)sb), ((memberList)membership), ((bookShelf)bs), ((issuedBook)ib));
		dropdown.setBackground(new Color(221, 221, 221));
		dropdown.setBounds(1090, 60, 190, 50);
		dropdown.setVisible(false);
		dropdown.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		out.setFont(new Font("Open Sans", 1, 18));
		out.setForeground(new Color(202, 2, 4));
		dropdown.add(out);
		
		this.add(dropdown);
		
		this.add(memberDash);
		this.add(membership);
		this.add(up);
		this.add(ib);
		this.add(bs);
		this.add(dashboard);
		this.add(sb);
		this.add(tB);
		this.add(log);
	}
}
