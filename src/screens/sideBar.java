package screens;

import javax.swing.*;
import custom.*;
@SuppressWarnings("serial")
public class sideBar extends AguaComponents{
	public JLabel icon = new JLabel();
	public JButton dash = new RoundedButton("DASHBOARD");
	public JButton member = new RoundedButton("MEMBERS");
	public JButton books = new RoundedButton("BOOK SHELF");
	public JButton issued = new RoundedButton("ISSUED");
	
	public sideBar() {
		init();
	}
	
	public void init() {
		
	}
}
