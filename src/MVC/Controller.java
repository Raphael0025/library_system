package MVC;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.*;
import custom.*; 
import screens.*;

@SuppressWarnings("serial")
public class Controller extends MyClass{
	mainApp mp;
	login lp;
	Dashboard dash;
	topBar tb;
	sideBar sb;
	
	public Controller(mainApp mp, login lp, Dashboard dash, topBar tBar, sideBar sb){
		this.mp = mp;
		this.lp = lp;
		this.dash = dash;
		this.tb = tBar;
		this.sb = sb;
		lp.loginBtn.addActionListener(this);
		
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
				dash.setVisible(true);
				tb.setVisible(true);
				sb.setVisible(true);
				if(lp.admin) {
					tb.Title("ADMIN");
				} else {
					tb.Title("MEMBER");
				}
				
				break;
			case "Dashboard":
				JOptionPane.showMessageDialog(null, "Dashboard, this is!\n-Yoda");
				break;
		}
	}
	
	public void verfication() {
		
	}
}
