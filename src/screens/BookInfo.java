package screens;
import javax.swing.*;
import java.awt.*;
import custom.*;

@SuppressWarnings("serial")
public class BookInfo extends AguaComponents{

	static String name = "BOOK INFORMATION";
	public JLabel header = new JLabel(name);
	String[] txt = {"Book ID", "Book Type", "Book Title", "Author(s)", "Publication Date", "Shelf No."};
	JLabel[] labels = new JLabel[txt.length];
	JTextField[] tf = new JTextField[txt.length];
	
	public BookInfo(int w, int h) {
		init(w, h);
	}
	public void init(int w, int h) {
		
		setLayout(null);
		setBounds(0, 0, w, h, 0);
		setColor(233,62,62, 255);
		
		header.setBounds(30,10,w,50);
		header.setFont(new Font("Open Sans", 1, 30));
		header.setForeground(Color.white);

		int y = 70;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 16));
			labels[i].setForeground(Color.white);
			y+=40;
		}
		
		int y2 = 70;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, "", 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 350, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(new Color(202, 2, 4));
			tf[i].setEditable(false);
			y2+=40;
		}

		for(int i = 0; i < labels.length; i++) {
			this.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			this.add(tf[i]);
		}
        
        this.add(header);
	}
}