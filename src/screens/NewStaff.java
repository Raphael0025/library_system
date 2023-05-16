package screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import custom.*;
import api_service.*;

@SuppressWarnings("serial")
public class NewStaff extends JDialog implements ActionListener, MouseListener{
	static String name = "CREATE NEW RECORD";
	public JLabel header = new JLabel(name);
	public JButton create = new RoundedButton("CREATE");
	public JButton gen = new RoundedButton("Generate");
	JLabel[] labels = new JLabel[8];
	public JTextField[] tf = new JTextField[8];
	String[] txt = {"Name", "Role", "Address", "Age", "Contact No.", "Email Address", "Password","Staff ID"};
	JPanel pan = new JPanel();
	SQLapi sql = new SQLapi();
	EmployeeList employee = new EmployeeList();
	
	public NewStaff(int w, int h) {
		init(w, h);
	}
	
	public void init( int w, int h) {
		this.setTitle(name);
		this.setSize(w, h);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		pan.setLayout(null);
		pan.setBounds(0, 0, w, h);
		pan.setBackground(new Color(233,62,62));
		
		header.setBounds(30,10,w,50);
		header.setFont(new Font("Open Sans", 1, 30));
		header.setForeground(Color.white);
		
		((RoundedButton)create).setBounds(150, 430, 250, 45);
		((RoundedButton)create).setArc(20);
		Default(create, "edit");
		create.setFont(new Font("Open Sans", 1, 20));
		create.addActionListener(this);
		create.addMouseListener(this);
		
		((RoundedButton)gen).setBounds(370, 340, 110, 30);
		((RoundedButton)gen).setArc(20);
		Default(gen, null);
		gen.setFont(new Font("Open Sans", 1, 12));
		gen.addActionListener(this);
		
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
			pan.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			pan.add(tf[i]);
		}
		pan.add(header);
		pan.add(create);
		pan.add(gen);
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
		String btn = press.getText(), id, pass;
		
		if(btn.equals("Generate")) {
			PasswordGenerator pg2 = new PasswordGenerator();
			IDGenerator idg = new IDGenerator();
			
			pg2.generator("employees");
			pass = pg2.GetPassword();
			id = idg.generator("employees");
			
			tf[6].setText(pass);
			tf[7].setText(id);
		} else if (btn.equals("CREATE")) {
			sql.SQLCreate("employees", tf[7].getText(), tf[0].getText(), tf[1].getText(), tf[3].getText(), tf[2].getText(), tf[4].getText(), tf[5].getText(), tf[6].getText(), employee.model);
			
			setVisible(false);
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
		create.setIcon(new ImageIcon("src\\assets\\red-edit.png"));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		create.setIcon(new ImageIcon("src\\assets\\white-edit.png"));
	}
}
