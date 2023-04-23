package custom;
import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.awt.event.*;
@SuppressWarnings("serial")
public class Time extends AguaComponents{
	AguaComponents ac = new AguaComponents();
	JLabel time = new JLabel(); 
	public Time() {
		this.setLayout(null);
		this.setOpaque(false);
		time.setHorizontalAlignment(SwingConstants.CENTER);
		Timer clock = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss a");
                String formatClock = now.format(format);
                time.setText(formatClock);
			}
		});
		clock.start();
		setOpaque(false);
		this.add(time);
		this.add(ac);
	}
	public void setBounds(int xAxis, int yAxis, int width, int height, int arc) {
		this.setBounds(xAxis, yAxis, (width+3), (height+3));
		ac.setBounds(0, 0, width, height, arc);
		time.setBounds(0, 0, width, height);
	}
	public void setForeground(int red, int green ,int blue) {
		time.setForeground(new Color(red, green, blue));
	}
	public void setFont(String type, int weight, int size) {
		time.setFont(new Font(type, weight, size));
	}
	public void setColor(int red, int green ,int blue) {
		setColor(red, green, blue, 255);
	}
	public void borderColor(int red, int green, int blue) {
		borderColor(red, green, blue);
	}
}
