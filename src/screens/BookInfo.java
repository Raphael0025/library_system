package screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import custom.*;

@SuppressWarnings("serial")
public class BookInfo extends JDialog {

	static String name = "BOOK INFORMATION";
	public JLabel header = new JLabel(name);
	String[] txt = {"Book ID", "Book Type", "Book Title", "Author(s)", "Publication Date", "Shelf No."};
	JLabel[] labels = new JLabel[txt.length];
	JTextField[] tf = new JTextField[txt.length];
	JPanel pan = new JPanel();
	
	public BookInfo(int w, int h, String[] arr) {
		init(w, h, arr);
	}
	public void init(int w, int h, String[] arr) {
		this.setTitle("Search Book");
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
			tf[i] = new CustomTextField(20, arr[i], 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 350, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(new Color(202, 2, 4));
			tf[i].setEditable(false);
			tf[i].setFocusable(false);
			y2+=40;
		}

		for(int i = 0; i < labels.length; i++) {
			pan.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			pan.add(tf[i]);
		}
        
        pan.add(header);
        this.add(pan);
	}
}