package screens;
import javax.swing.*;

import api_service.*;
import api_service.SQLapi;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import custom.*;

@SuppressWarnings("serial")
public class B_RUD extends JDialog implements ActionListener, MouseListener{
	static String name = "BOOK INFORMATION";
	public JLabel header = new JLabel(name);
	public JButton issueB = new RoundedButton("Issue this Book");
	public JButton editB = new RoundedButton("Edit Book");
	public JButton delB = new RoundedButton("Delete Book");
	JLabel[] labels = new JLabel[7];
	public JTextField[] tf = new JTextField[7];
	String[] txt = {"Book ID", "Book Type", "Book Title", "Author(s)", "Publication Date", "Shelf No.", "Book Status"};
	JPanel pan = new JPanel();
	bookShelf ml = new bookShelf();
	SQLapi sql = new SQLapi();
	BookRent br;
	bookShelf bs = new bookShelf();
	
	public B_RUD(int w, int h, String[] arr) {
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
		
		((RoundedButton)issueB).setBounds(40, 80, 180, 40);
		((RoundedButton)issueB).setArc(20);
		Default(issueB, "openbook");
		issueB.setFont(new Font("Open Sans", 1, 16));
		
		((RoundedButton)editB).setBounds(230, 80, 140, 40);
		((RoundedButton)editB).setArc(20);
		Default(editB, "edit");
		editB.setFont(new Font("Open Sans", 1, 16));
		
		((RoundedButton)delB).setBounds(380, 80, 160, 40);
		((RoundedButton)delB).setArc(20);
		Default(delB, "del");
		delB.setFont(new Font("Open Sans", 1, 16));
		
		int y = 150;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 16));
			labels[i].setForeground(Color.white);
			y+=40;
		}
		
		int y2 = 150;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, arr[i], 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 350, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(new Color(202, 2, 4));
			y2+=40;
		}
		tf[6].setEditable(false);
		tf[0].setEditable(false);

		if(tf[6].getText().equals("unavailable")) {
			issueB.setVisible(false);
		}
		
		for(int i = 0; i < labels.length; i++) {
			pan.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			pan.add(tf[i]);
		}
		issueB.addActionListener(this);
		editB.addActionListener(this);
		delB.addActionListener(this);
		issueB.addMouseListener(this);
		editB.addMouseListener(this);
		delB.addMouseListener(this);
        
        pan.add(issueB);
        pan.add(editB);
        pan.add(delB);
        pan.add(header);
        this.add(pan);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(219, 105, 108, 255,255,255);
		((RoundedButton)var).setBorder(219, 105, 108, 255,255,255);
		((RoundedButton)var).setFore(255, 255, 255, 255, 0, 0);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton press = ( JButton ) e.getSource();
		String btn = press.getText();
		
		if(btn.equals("Issue this Book")) {
			br = new BookRent(600, 480, tf[0].getText(), tf[1].getText(), tf[2].getText());
			br.setVisible(true);
			
		} else if (btn.equals("Delete Book")) {
			String id5 = tf[0].getText();
			sql.SQLDelete("book", id5, ml.model);
			this.dispose();
		} else if(btn.equals("Edit Book")) {
			String id4 = tf[0].getText();
			String[] temp2 = new String[6];
			for(int i = 0; i < temp2.length; i++) {
				temp2[i] = tf[i+1].getText();
			}
			sql.SQLUpdate("book", id4, temp2, ml.model);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton press = ( JButton ) e.getSource();
		String btn = press.getText();
		if(btn.equals("Edit Book")) {
			editB.setIcon(new ImageIcon("src\\assets\\red-edit.png"));
			
		} else if(btn.equals("Delete Book")) {
			delB.setIcon(new ImageIcon("src\\assets\\red-del.png"));
			
		} else if(btn.equals("Issue this Book")) {
			issueB.setIcon(new ImageIcon("src\\assets\\red-openbook.png"));
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton press = ( JButton ) e.getSource();
		String btn = press.getText();
		if(btn.equals("Edit Book")) {
			editB.setIcon(new ImageIcon("src\\assets\\white-edit.png"));
			
		} else if(btn.equals("Delete Book")) {
			delB.setIcon(new ImageIcon("src\\assets\\white-del.png"));
			
		} else if(btn.equals("Issue this Book")) {
			issueB.setIcon(new ImageIcon("src\\assets\\white-openbook.png"));
			
		}
	}
}
