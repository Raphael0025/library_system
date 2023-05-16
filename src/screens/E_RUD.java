package screens;
import javax.swing.*;
import javax.swing.table.*;

import api_service.SQLapi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import custom.*;

@SuppressWarnings("serial")
public class E_RUD extends JDialog implements ActionListener{
	
	static String name = "STAFF DETAILS";
	public JLabel header = new JLabel(name);
	public JButton edit = new RoundedButton("Edit Employee");
	public JButton del = new RoundedButton("Delete Employee");
	JLabel[] labels = new JLabel[8];
	JPanel pan = new JPanel();
	public JTextField[] tf = new JTextField[8];
	SQLapi sql = new SQLapi();
	String[] txt = {"STAFF ID", "Name", "Role", "Age", "Address", "Contact No.", "Email Address", "Password"};
    EmployeeList employee = new EmployeeList();
    
	public E_RUD(String name, int w, int h, String[] arr) {
		init(name, w, h, arr);
	}
	public void init(String name, int w, int h, String[] arr) {
		this.setTitle(name);
		this.setSize(w, h);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		pan.setBounds(0, 0, w, h);
		pan.setBackground(new Color(233,62,62));
		pan.setLayout(null);
		
		header.setBounds(30,10,w,50);
		header.setFont(new Font("Open Sans", 1, 30));
		header.setForeground(Color.white);
		
		((RoundedButton)edit).setBounds(210, 70, 210, 50);
		((RoundedButton)edit).setArc(20);
		Default(edit, "edit");
		edit.setFont(new Font("Open Sans", 1, 20));
		edit.addActionListener(this);
		
		((RoundedButton)del).setBounds(420, 70, 220, 50);
		((RoundedButton)del).setArc(20);
		Default(del, "del");
		del.setFont(new Font("Open Sans", 1, 20));
		del.addActionListener(this);
		
		int y2 = 130;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, arr[i], 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 400, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(Color.red);
			y2+=40;
		}
		tf[0].setEditable(false);
		tf[0].setEnabled(false);
		tf[6].setEditable(false);
		tf[7].setEditable(false);
		for(int i = 0; i < tf.length; i++) {
			pan.add(tf[i]);
		}
		
		int y = 130;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 16));
			labels[i].setForeground(Color.white);
			y+=40;
		}
		
        for(int i = 0; i < labels.length; i++) {
			pan.add(labels[i]);
		}
        pan.add(header);
		pan.add(edit);
		pan.add(del);
		
		this.add(pan);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(233, 62, 62, 219,105,108);
		((RoundedButton)var).setBorder(233, 62, 62, 219,105,108);
		((RoundedButton)var).setFore(255, 255, 255, 255, 255, 255);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton press = ( JButton ) e.getSource();
		String btn = press.getText();
		
		switch(btn) {
			case "Edit Employee":
				String id6 = tf[0].getText();
				String[] temp3 = new String[7];
				for(int i = 0; i < temp3.length; i++) {
					temp3[i] = tf[i+1].getText();
				}
				sql.SQLUpdate("employees", id6, temp3, employee.model);
				
				break;
			case "Delete Employee":
				String id7 = tf[0].getText();
				sql.SQLDelete("employees", id7, employee.model);
				
				break;
		}

	}
}
