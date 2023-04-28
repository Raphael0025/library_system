package screens;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import custom.*;
import components.*;

@SuppressWarnings("serial")
public class NewMemberRecord extends AguaComponents{
	static String name = "CREATE NEW RECORD";
	public JLabel header = new JLabel(name);
	public JButton create = new RoundedButton("CREATE");
	public JButton gen = new RoundedButton("GENERATE");
	JLabel[] labels = new JLabel[8];
	JTextField[] tf = new JTextField[8];
	String[] txt = {"Name", "Member Type", "Address", "Age", "Contact No.", "Email Address", "Password","Member ID"};
	   
	public NewMemberRecord(int w, int h) {
		init(w, h);
	}
	
	public void init(int w, int h) {
		setLayout(null);
		setBounds(0, 0, w, h, 0);
		setColor(233,62,62, 255);
		
		header.setBounds(30,10,w,50);
		header.setFont(new Font("Open Sans", 1, 30));
		header.setForeground(Color.white);
		
		((RoundedButton)create).setBounds(150, 430, 250, 45);
		((RoundedButton)create).setArc(20);
		Default(create, "edit");
		create.setFont(new Font("Open Sans", 1, 20));
		
		((RoundedButton)gen).setBounds(370, 340, 110, 30);
		((RoundedButton)gen).setArc(20);
		Default(gen, null);
		gen.setFont(new Font("Open Sans", 1, 12));
		

		int y = 100;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 16));
			labels[i].setForeground(Color.white);
			y+=40;
		}
		
		int y2 = 100;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, "", 10);
			if(i > 5) {
				((CustomTextField)tf[i]).setBounds(210, y2, 150, 30);
				((CustomTextField)tf[i]).setEditable(false);
			} else {
				((CustomTextField)tf[i]).setBounds(210, y2, 280, 30);
			}
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(new Color(202, 2, 4));
			y2+=40;
		}
		
		for(int i = 0; i < labels.length; i++) {
			this.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			this.add(tf[i]);
		}
		this.add(header);
		this.add(create);
		this.add(gen);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(219, 105, 108, 255,255,255);
		((RoundedButton)var).setBorder(219, 105, 108, 255,255,255);
		((RoundedButton)var).setFore(255, 255, 255, 255, 0, 0);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
}
