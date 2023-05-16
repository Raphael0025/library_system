package screens;
import javax.swing.*;

import api_service.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.time.*;
import java.util.Date;

import custom.*;

@SuppressWarnings("serial")
public class BookRent extends JDialog implements ActionListener, MouseListener{

	static String name = "FILL OUT DETAILS TO ISSUE BOOK";
	public JLabel header = new JLabel(name);
	public JButton issB = new RoundedButton("Issue Book");
	JLabel[] labels = new JLabel[6];
	public JTextField[] tf = new JTextField[6];
	String[] txt = {"Book ID", "Book Title", "Member Name", "Issued Date", "Returned Date", "Late Fine Fee"};
	JPanel pan = new JPanel();
	bookShelf ml = new bookShelf();
	issuedBook ib = new issuedBook();
	SQLapi sql = new SQLapi();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	Date dt;
	CustomTextField ctf = new CustomTextField();
	
	public BookRent(int w, int h, String a, String b, String c) {
		init(w, h, a, b, c);
	}
	public void init(int w, int h, String a, String b, String c) {
		this.setTitle("Book Rent");
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
		header.setFont(new Font("Open Sans", 1, 18));
		header.setForeground(Color.white);

		((RoundedButton)issB).setBounds(380, 80, 180, 40);
		((RoundedButton)issB).setArc(20);
		Default(issB, "openbook");
		issB.setFont(new Font("Open Sans", 1, 16));
		issB.addActionListener(this);
		issB.addMouseListener(this);
		
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
        
        String cnvrt_date;
		try {
			dt = formatter.parse(LocalDate.now().toString());
			cnvrt_date = formatter.format(dt);
			
			tf[0].setText(a);
			tf[1].setText(c);
			tf[3].setText(cnvrt_date);
			
			ctf.placeHolder(tf[0], tf[0].getText());
			ctf.placeHolder(tf[1],  tf[1].getText());
			ctf.placeHolder(tf[3], cnvrt_date);
		} catch (ParseException e2) {}
        
        pan.add(issB);
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
		issB.setIcon(new ImageIcon("src\\assets\\red-openbook.png"));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		issB.setIcon(new ImageIcon("src\\assets\\white-openbook.png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date1, date2;
		IDGenerator ig = new IDGenerator();
		
		if(sql.verifyMember(tf[2].getText())) {
			int att = 0;
			String id = ig.generator("booksissued");
			for(int i = 0; i < tf.length; i++) {
				if(tf[i].getText().equals("")) {
					att++;
				}
			}
			if(att==0) {
				try {
					date1 = formatter1.parse(tf[3].getText());
					date2 = formatter1.parse(tf[4].getText());  
					if(date1.after(date2)) {
						JOptionPane.showMessageDialog(null, "Return Date must not be earlier than Issued Date", "Alert 204!", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showMessageDialog(null, "Date Format must be (yyyy-mm-dd)", "Reminder!", JOptionPane.INFORMATION_MESSAGE);
					}else {
						sql.SQLCreate("booksissued", id, tf[0].getText(), tf[1].getText(), tf[2].getText(), tf[3].getText(), tf[4].getText(), tf[5].getText(), "not returned", ib.model);
						sql.setBookStatus("book", "unavailable", tf[0].getText(), ml.model);
						this.dispose();
					}
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Input Valid Dates!", "Alert 404!", JOptionPane.ERROR_MESSAGE);
				}  
				att = 0;
			}else {
				JOptionPane.showMessageDialog(null, "Please fill out details!", "Alert!", JOptionPane.ERROR_MESSAGE);
				att = 0;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Member Not Found!", "Error 204", JOptionPane.ERROR_MESSAGE);
		}
	}
}
