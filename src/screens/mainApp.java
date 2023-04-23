package screens;

import java.awt.*;
import javax.swing.*;
import custom.*;

public class mainApp extends MyFrame{

	JPanel login;
	static String name = "Library Management";
	
	public mainApp() {
		super(name, 500, 650);
		init();
	}
	
	public void init() {
		login = new login(getWidth(), getHeight());
		this.setBackground(Color.cyan);
		this.add(login);
	}

}
