package screens;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import custom.*;

@SuppressWarnings("serial")
public class topBar extends AguaComponents{
	public JLabel name = new JLabel("DEFAULT");
	public JLabel arrow = new JLabel(new ImageIcon("src\\assets\\arrow-down.png"));
	public JLabel small_logo = new JLabel(new ImageIcon("src\\assets\\small-eup.png")); 
	public JLabel home = new JLabel("HOME");
	MemberDashboard md = new MemberDashboard();
	UserProfile up = new UserProfile();
	
	public topBar() {
		name.setFont(new Font("Open Sans", 1, 16));
		name.setForeground(Color.white);
		home.setFont(new Font("Open Sans", 1, 12));
		home.setForeground(Color.white);
		home.setVisible(false);
		
		setVisible(false);
		setBounds(0,0,1285,60,0);
		setLayout(null);
		setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 30));
		setColor(233, 62, 62, 255);
		borderColor(233, 62, 62);
		
		home.setBounds(90, 10, 40, 40);
		small_logo.setBounds(30,5,50,50);
		name.setBounds(1130, 10, 80, 40);
		arrow.setBounds(1200, 5, 50, 50);
		
		this.add(home);
		this.add(small_logo);
		this.add(name);
		this.add(arrow);
	}
	
	public void Title(String title) {
		name.setText(title.toUpperCase());
	}
}
