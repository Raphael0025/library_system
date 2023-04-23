package screens;

import java.awt.*;
import javax.swing.*;
import custom.*;

public class mainApp extends MyFrame{

	JPanel login;
	
	public mainApp() {
		super("Library Management", 500, 650);
		init();
	}
	
	public void init() {
		login = new login(getWidth(), getHeight());
	}

}
