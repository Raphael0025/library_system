package screens;
import javax.swing.*;

import api_service.SQLapi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import custom.*;

@SuppressWarnings("serial")
public class ViewIssuedBook extends JDialog implements ActionListener, MouseListener{

	static String name = "ISSUED BOOK";
	public JLabel header = new JLabel(name);
	public JButton returnB = new RoundedButton("Return Book");
	JLabel[] labels = new JLabel[8];
	public JTextField[] tf = new JTextField[8];
	String[] txt = {"Issue ID", "Book ID", "Book Title", "Member Name", "Issued Date", "Returned Date", "Late Fine Fee", "Issued Status"};
	JPanel pan = new JPanel();
	issuedBook ib = new issuedBook();
	SQLapi sql = new SQLapi();
	bookShelf bs = new bookShelf();
	
	public ViewIssuedBook(int w, int h, String[] arr) {
		init(w, h, arr);
	}
	public void init(int w, int h, String[] arr) {
		this.setTitle("Issued Book");
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

		((RoundedButton)returnB).setBounds(380, 80, 180, 40);
		((RoundedButton)returnB).setArc(20);
		Default(returnB, "openbook");
		returnB.setFont(new Font("Open Sans", 1, 16));
		returnB.addActionListener(this);
		returnB.addMouseListener(this);
		
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
			tf[i].setEditable(false);
			y2+=40;
		}

		for(int i = 0; i < labels.length; i++) {
			pan.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			pan.add(tf[i]);
		}
        
        if(arr[7].equals("returned")) {
			returnB.setVisible(false);
			for(JTextField tf2: tf) {
				tf2.setFocusable(false);
			}
		}
        
        pan.add(returnB);
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
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		returnB.setIcon(new ImageIcon("src\\assets\\red-book.png"));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		returnB.setIcon(new ImageIcon("src\\assets\\white-book.png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		sql.setBookStatus("booksissued", "returned", tf[0].getText(), ib.model);
		sql.setBookStatus("book", "available", tf[1].getText(), bs.model);
		JOptionPane.showMessageDialog(null, "Book Successfully Returned", "Returned", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
}
