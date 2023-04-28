package screens;
import javax.swing.*;
import java.awt.*;
import custom.*;

@SuppressWarnings("serial")
public class NewBookRecord extends AguaComponents{
	static String name = "ADD NEW BOOK";
	public JLabel header = new JLabel(name);
	public JButton adb = new RoundedButton("ADD BOOK");
	JLabel[] labels = new JLabel[5];
	JTextField[] tf = new JTextField[5];
	String[] txt = {"Book Type", "Book Title", "Author(s)", "Published Date", "Shelf No."};
	
	public NewBookRecord(int w, int h) {
		init(w, h);
	}
	
	public void init(int w, int h) {
		setLayout(null);
		setBounds(0, 0, w, h, 0);
		setColor(233,62,62, 255);
		
		header.setBounds(30,10,w,50);
		header.setFont(new Font("Open Sans", 1, 30));
		header.setForeground(Color.white);
		
		((RoundedButton)adb).setBounds(360, 330, 200, 50);
		((RoundedButton)adb).setArc(20);
		Default(adb, "book");
		adb.setFont(new Font("Open Sans", 1, 20));
		
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
			((CustomTextField)tf[i]).setBounds(210, y2, 350, 30);
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
		
		this.add(adb);
		this.add(header);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(219, 105, 108, 255,255,255);
		((RoundedButton)var).setBorder(219, 105, 108, 255,255,255);
		((RoundedButton)var).setFore(255, 255, 255, 255, 0, 0);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
}
