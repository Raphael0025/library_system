package screens;

import java.awt.*;
import javax.swing.*;
import MVC.Controller;
import custom.*;


@SuppressWarnings("serial")
public class promptFrame extends MyFrame{
	
	//Membership
	public JPanel mrud;
	
	
	//Books
	
	
	
	//Issued
	
	
	// put width and height
	public promptFrame(String name, int w, int h) {
		super(name, w, h);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
		//mrud = new M_RUD(getWidth(), getHeight());
		//this.add(mrud);
	}
}
