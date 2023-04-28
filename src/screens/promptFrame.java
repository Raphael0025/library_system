package screens;

import javax.swing.*;
import custom.*;

@SuppressWarnings("serial")
public class promptFrame extends MyFrame{
	
	// put width and height
	public promptFrame(String name, int w, int h) {
		super(name, w, h);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
