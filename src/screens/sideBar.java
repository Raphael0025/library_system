package screens;

import java.awt.*;
import java.awt.event.*;

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
		hover(member, "members");
		hover(books, "shelf");
		hover(issued, "book");
		
		dash.addActionListener(this);
		member.addActionListener(this);
		books.addActionListener(this);
		issued.addActionListener(this);
		
		menu.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		menu.setOpaque(false);
		this.add(logo);
		this.add(menu);
		menu.add(dash);
		menu.add(member);
		menu.add(books);
		menu.add(issued);
	}
	
	public void hover(JButton var, String img) {
		((RoundedButton)var).setFill(233, 62, 62, 255, 255, 255);
		((RoundedButton)var).setBorder(233, 62, 62, 255, 255, 255);
		((RoundedButton)var).setFore(255, 255, 255, 233, 62, 62);
		var.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				var.setIcon(new ImageIcon("src\\assets\\red-"+img+".png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
			}
		});
	}
	
	public void active(JButton var, String img) {
		((RoundedButton)var).setFill(255, 255, 255, 255, 255, 255);
		((RoundedButton)var).setBorder(255, 255, 255, 255, 255, 255);
		((RoundedButton)var).setFore(233, 62, 62, 233, 62, 62);
		var.setIcon(new ImageIcon("src\\assets\\red-"+img+".png"));
	}
}
