package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import custom.*;
@SuppressWarnings("serial")
public class login extends AguaComponents{
	public JButton staff = new RoundedButton("STAFF");
	public JButton member = new RoundedButton("MEMBER");
	public JButton loginBtn = new RoundedButton("LOGIN");
	public JPanel top = new JPanel(new GridLayout(1,2));
	public JTextField tf = new CustomTextField(20, "Enter Member ID", 20);
	public JPasswordField pf = new CustomPasswordField(20, "Enter Password", 20);
	public AguaComponents ac;
	public boolean admin = false;
	
	public login() {
		this.setLayout(null);
	}
	public login(int width, int height) {
		init(width, height);
	}
	public void init(int width, int height)  {
		this.setLayout(null);
		this.setFocusable(true);
		this.setSize(width, height);
		ac = new AguaComponents(); 
		ac.setBounds(0, 0, getWidth(), getHeight(), 0);
		ac.setColor(233, 62, 62, 255);
		ac.borderColor(233, 62, 62);
		
		((RoundedButton)staff).setSize(250, 60);
		staff.setFont(new Font("Open Sans", 1, 24));
		//Default button color
		((RoundedButton)member).setFill(233, 62, 62, 233, 62, 62);
		((RoundedButton)member).setBorder(233, 62, 62, 233, 62, 62);
		((RoundedButton)member).setFore(255, 255, 255, 255, 255, 255);
		((RoundedButton)member).setSize(250, 60);
		
		((RoundedButton)staff).setFill(255, 255, 255, 255, 255, 255);
		((RoundedButton)staff).setBorder(255, 255, 255, 255, 255, 255);
		((RoundedButton)staff).setFore(255, 0, 0, 255, 0, 0);
		((RoundedButton)staff).setArc(0);
		((RoundedButton)member).setArc(0);
		member.setFont(new Font("Open Sans", 1, 24));
		
		staff.addActionListener(this);
		member.addActionListener(this);
		
		((CustomTextField)tf).setBounds(90, 225, 310, 40);
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setFont(new Font("Open Sans", 1, 18));
		tf.setForeground(new Color(202, 2, 4));
		
		((CustomPasswordField)pf).setBounds(90, 280, 310, 40);
		pf.setHorizontalAlignment(SwingConstants.CENTER);
		pf.setFont(new Font("Open Sans", 1, 18));
		pf.setForeground(new Color(202, 2, 4));
		
		((RoundedButton)loginBtn).setBounds(90, 420, 310, 50);
		((RoundedButton)loginBtn).setArc(20);
		((RoundedButton)loginBtn).setFill(219, 105, 108, 255, 255, 255);
		((RoundedButton)loginBtn).setBorder(219, 105, 108, 255, 255, 255);
		((RoundedButton)loginBtn).setFore(255, 255, 255, 255, 0, 0);
		loginBtn.setFont(new Font("Open Sans", 1, 24));
		
		top.setBounds(0,0, getWidth()-15, 60);
		top.add(staff);
		top.add(member);
		
		this.add(top);
		this.add(tf);
		this.add(pf);
		this.add(loginBtn);
		this.add(ac);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == member) {
			changeDefault(member, staff);
			tf.setText("Enter Member ID");
			admin = false;
		} else {
			changeDefault(staff, member);
			tf.setText("Enter Staff ID");
			admin = true;
		}
	}
	
	public void changeDefault(JButton var, JButton var2) {
		((RoundedButton)var).setFill(233, 62, 62, 233, 62, 62);
		((RoundedButton)var).setBorder(233, 62, 62, 233, 62, 62);
		((RoundedButton)var).setFore(255, 255, 255, 255, 255, 255);
		
		((RoundedButton)var2).setFill(255, 255, 255, 255, 255, 255);
		((RoundedButton)var2).setBorder(255, 255, 255, 255, 255, 255);
		((RoundedButton)var2).setFore(255, 0, 0, 255, 0, 0);
		repaint();
	}
}
