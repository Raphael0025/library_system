package screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import custom.*;
import components.*;

@SuppressWarnings("serial")
public class UserProfile extends AguaComponents{
	
	public JLabel title = new JLabel("USER PROFILE");
	public JButton modify = new RoundedButton("Edit Staff");
	public JButton delS = new RoundedButton("Delete Staff");
	String[] txt = {"Staff ID", "Staff Name", "Staff Type", "Age","Address", "Contact No.", "Email Address", "Password"};
	JLabel[] labels = new JLabel[txt.length];
	public JTextField[] tf = new JTextField[txt.length];
	
	public UserProfile() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setVisible(false);
		setBounds(150, 70, 1120, 630, 20);
		setColor(219,105,108, 255);
		
		title.setBounds(30,0,300,80);
		title.setFont(new Font("Open Sans", 1, 36));
		title.setForeground(Color.white);
		
		int y = 100;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 20));
			labels[i].setForeground(Color.white);
			y+=50;
		}
		
		int y2 = 100;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, "", 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 280, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 18));
			tf[i].setForeground(new Color(202, 2, 4));
			y2+=50;
		}
		
		((RoundedButton)modify).setBounds(30, 550, 160, 40);
		((RoundedButton)modify).setArc(20);
		Default(modify, "edit");
		modify.setFont(new Font("Open Sans", 1, 20));
		
		((RoundedButton)delS).setBounds(200, 550, 180, 40);
		((RoundedButton)delS).setArc(20);
		Default(delS, "del");
		delS.setFont(new Font("Open Sans", 1, 20));
		
		modify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				modify.setIcon(new ImageIcon("src\\assets\\red-edit.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				modify.setIcon(new ImageIcon("src\\assets\\white-edit.png"));
			}
		});
		
		delS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				delS.setIcon(new ImageIcon("src\\assets\\red-del.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				delS.setIcon(new ImageIcon("src\\assets\\white-del.png"));
			}
		});
		
		for(int i = 0; i < labels.length; i++) {
			this.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			this.add(tf[i]);
		}
		
        this.add(delS);
        this.add(modify);
		this.add(title);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(233, 62, 62, 255,255,255);
		((RoundedButton)var).setBorder(233, 62, 62, 255,255,255);
		((RoundedButton)var).setFore(255, 255, 255, 255, 0, 0);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
}
