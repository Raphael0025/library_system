package custom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements ActionListener, MouseListener{
	AguaComponents ac;
	JTextField ctf;
	CustomPasswordField cpf;
	Date date = new Date();
	Time time = new Time();
	
	JButton btn1 = new RoundedButton("World");
	
	public TestFrame() {
		this.setLayout(null);
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		ctf = new CustomTextField(20, "Username");
		cpf = new CustomPasswordField(20, "Password");
		ac = new AguaComponents(); 
		ac.setBounds(10, 50, 150, 50, 20);
		ac.setColor(100, 140, 230, 100);
		ac.borderColor(0, 0, 0);
		
		btn1.setBounds(150,100,150,50);
		
		((RoundedButton) btn1).setFill(34,105,187, 255, 10, 10);
		((RoundedButton) btn1).setBorder(0,0,0, 100, 255, 10);
		((RoundedButton) btn1).setFore(255, 255, 255, 0, 0, 0);
		((RoundedButton) btn1).setArc(50);
		btn1.addActionListener(this);
		btn1.setFont(new Font("Arial Black", 4, 18));
		
		ctf.setBounds(300, 250, 130, 40);
		ctf.setHorizontalAlignment(SwingConstants.CENTER);
		ctf.setFont(new Font("Sans Serif", 4, 14));
		ctf.setForeground(new Color(0, 0, 0));
		
		cpf.setBounds(450, 250, 130, 40);
		cpf.setHorizontalAlignment(SwingConstants.CENTER);
		cpf.setFont(new Font("Sans Serif", 4, 14));
		cpf.setForeground(new Color(0, 0, 0));
		
		date.setBounds(100, 200, 150, 40, 20);
		date.setColor(255, 255, 255);
		date.borderColor(0, 0, 0);
		date.setFont("Arial", 1, 16);
		date.setForeground(0,0,0);
		
		time.setBounds(100, 350, 120, 40, 20);
		time.setColor(255, 255, 255);
		time.borderColor(0, 0, 0);
		time.setFont("Arial", 1, 16);
		time.setForeground(0,0,0);
		
		this.add(time);
		this.add(date);
		this.add(ctf);
		this.add(cpf);
		this.add(ac);
		this.add(btn1);
	}
	public static void main(String[] args) {
		TestFrame tf = new TestFrame();
		tf.setVisible(true);
		tf.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "It Works");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(ctf.getText().equals("agua")) {
			JOptionPane.showMessageDialog(null, "Right");
		} else {
			JOptionPane.showMessageDialog(null, "Wrong");
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	

}
