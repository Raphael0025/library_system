package screens;

import java.awt.*;
import javax.swing.*;
import MVC.Controller;
import custom.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class mainApp extends MyFrame{
	JLabel out = new JLabel("LOGOUT");
	JPanel log, dashboard, tB, sb;
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
		
		this.setBackground(Color.cyan);
		
		new Controller(this, ((login)log), ((Dashboard)dashboard), ((topBar)tB), ((sideBar)sb));
		dropdown.setBackground(new Color(221, 221, 221));
		dropdown.setBounds(1090, 60, 190, 50);
		dropdown.setVisible(false);
		dropdown.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		out.setFont(new Font("Open Sans", 1, 18));
		out.setForeground(new Color(202, 2, 4));
		dropdown.add(out);
		
		out.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	          logout();
	        }
			@Override
			public void mouseEntered(MouseEvent e) {
				out.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				out.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		((topBar) tB).arrow.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	           if(dropdown.isVisible()) 
	        	   dropdown.setVisible(false);
	           else 
	        	   dropdown.setVisible(true);
	         }
			@Override
			public void mouseEntered(MouseEvent e) {
				((topBar) tB).arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				((topBar) tB).arrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.add(dropdown);
		this.add(dashboard);
		this.add(tB);
		this.add(sb);
		this.add(log);
	}

	public void logout() {
		this.setSize(500, 650);
		this.setLocationRelativeTo(null);
        this.setTitle("Library Management - Dashboard");
        log.setVisible(true);
		dashboard.setVisible(false);
		dropdown.setVisible(false);
		tB.setVisible(false);
		sb.setVisible(false);
	}
}
