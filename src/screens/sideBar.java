package screens;

import java.awt.*;
import javax.swing.*;
import custom.*;

@SuppressWarnings("serial")
public class sideBar extends AguaComponents{
	public JLabel logo = new JLabel(new ImageIcon("src\\assets\\uep_logo.png"));
	public JButton dash = new RoundedButton("DASHBOARD");
	public JButton member = new RoundedButton("MEMBERS");
	public JButton books = new RoundedButton("BOOK SHELF");
	public JButton issued = new RoundedButton("ISSUED");
	public JPanel menu = new JPanel(new GridLayout(4, 1));
	
	public sideBar() {
		init();
	}
	
	public void init() {
		setVisible(false);
		setBounds(0,0,130,750,0);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setColor(233, 62, 62, 255);
		borderColor(233, 62, 62);
		
		dash.setHorizontalAlignment(SwingConstants.LEFT);
		member.setHorizontalAlignment(SwingConstants.LEFT);
		books.setHorizontalAlignment(SwingConstants.LEFT);
		issued.setHorizontalAlignment(SwingConstants.LEFT);
		
		dash.setFont(new Font("Open Sans", 1, 9));
		member.setFont(new Font("Open Sans", 1, 9));
		books.setFont(new Font("Open Sans", 1, 9));
		issued.setFont(new Font("Open Sans", 1, 9));
		
		dash.setPreferredSize(new Dimension(130, 50));
		member.setPreferredSize(new Dimension(130, 50));
		books.setPreferredSize(new Dimension(130, 50));
		issued.setPreferredSize(new Dimension(130, 50));
		
		active(dash, "dash");
		Default(member, "members");
		Default(books, "shelf");
		Default(issued, "book");
		
		menu.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		menu.setOpaque(false);
		this.add(logo);
		this.add(menu);
		menu.add(dash);
		menu.add(member);
		menu.add(books);
		menu.add(issued);
	}
	
	public void active(JButton var, String img) {
		((RoundedButton)var).setFill(255, 255, 255, 255, 255, 255);
		((RoundedButton)var).setBorder(255, 255, 255, 255, 255, 255);
		((RoundedButton)var).setFore(233, 62, 62, 233, 62, 62);
		var.setIcon(new ImageIcon("src\\assets\\red-"+img+".png"));
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(233, 62, 62, 219,105,108);
		((RoundedButton)var).setBorder(233, 62, 62, 219,105,108);
		((RoundedButton)var).setFore(255, 255, 255, 255, 255, 255);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
}
