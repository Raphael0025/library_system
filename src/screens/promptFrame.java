package screens;

import java.awt.*;
import javax.swing.*;
import MVC.Controller;
import custom.*;


@SuppressWarnings("serial")
public class promptFrame extends MyFrame{
	
	static String name ="prompt";
	//Membership
	public JPanel mrud;
	
	
	//Books
	
	
	
	//Issued
	
	
	// put width and height
	public promptFrame() {
		super(name, 700, 720);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
		//mrud = new M_RUD(getWidth(), getHeight());
		//this.add(mrud);
	}
}
