package custom;
import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
@SuppressWarnings("serial")
public class Date extends AguaComponents{
	AguaComponents ac = new AguaComponents();
	JLabel date = new JLabel();
	public Date() {
		this.setLayout(null);
		this.setOpaque(false);
		date.setHorizontalAlignment(SwingConstants.CENTER);
		DateTimeFormatter digital_date = DateTimeFormatter.ofPattern("MMM. dd, yyyy E.");
        LocalDateTime date_now = LocalDateTime.now();
        date.setText(digital_date.format(date_now));
        this.add(date);
        this.add(ac);
	}
	public void setBounds(int xAxis, int yAxis, int width, int height, int arc) {
		this.setBounds(xAxis, yAxis, (width+3), (height+3));
		ac.setBounds(0, 0, width, height, arc);
		date.setBounds(0, 0, width, height);
	}
	public void setForeground(int red, int green ,int blue) {
		date.setForeground(new Color(red, green, blue));
	}
	public void setFont(String type, int weight, int size) {
		date.setFont(new Font(type, weight, size));
	}
	public void setColor(int red, int green ,int blue) {
		setColor(red, green, blue, 255);
	}
	public void borderColor(int red, int green, int blue) {
		borderColor(red, green, blue);
	}
}
