package screens;
import javax.swing.*;
import api_service.*;
import java.awt.*;
import java.awt.event.*;
import custom.*;

@SuppressWarnings("serial")
public class NewBookRecord extends JDialog implements ActionListener, MouseListener{
	static String name = "ADD NEW BOOK";
	public JLabel header = new JLabel(name);
	public JButton adb = new RoundedButton("ADD BOOK");
	String[] txt = {"Book Type", "Book Title", "Author(s)", "Published Date", "Shelf No.", "Book Status"};
	JLabel[] labels = new JLabel[txt.length];
	public JTextField[] tf = new JTextField[txt.length];
	JPanel pan = new JPanel();
	SQLapi sql = new SQLapi();
	bookShelf bs = new bookShelf();
	IDGenerator ig = new IDGenerator();
	
	public NewBookRecord(int w, int h) {
		init(w, h);
	}
	
	public void init(int w, int h) {
		this.setTitle("New Book");
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
		
		((RoundedButton)adb).setBounds(360, 330, 200, 50);
		((RoundedButton)adb).setArc(20);
		Default(adb, "book");
		adb.setFont(new Font("Open Sans", 1, 20));
		adb.addActionListener(this);
		adb.addMouseListener(this);
		
		int y = 80;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 16));
			labels[i].setForeground(Color.white);
			y+=40;
		}
		
		int y2 = 80;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, "", 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 350, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(new Color(202, 2, 4));
			y2+=40;
		}

		for(int i = 0; i < labels.length; i++) {
			pan.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			pan.add(tf[i]);
		}
		
		pan.add(adb);
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
		adb.setIcon(new ImageIcon("src\\assets\\red-book.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		adb.setIcon(new ImageIcon("src\\assets\\white-book.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = ig.generator("book");
		sql.SQLCreate("book", id, tf[0].getText(), tf[1].getText(), tf[2].getText(), tf[3].getText(), tf[4].getText(), tf[5].getText(), null, bs.model);
		
		this.dispose();
	}
}
