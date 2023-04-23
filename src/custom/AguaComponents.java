package custom;
import java.awt.*;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AguaComponents extends MyClass{
	int width, height, arc, xAxis, yAxis, red = 255, green = 255, blue = 255, alpha = 255, red2 = 255, green2 = 255, blue2 = 255;
	ImageIcon icon = new ImageIcon();
	
	public AguaComponents() {
		this.setLayout(null);	
		this.setOpaque(false);
	}
	public AguaComponents(String url) {
		this();
		icon = new ImageIcon(url);
	}
	public void setBounds(int xAxis, int yAxis, int width, int height, int arc) {
		this.setBounds(xAxis, yAxis, (width+3), (height+3));
		this.width = width;
		this.height = height;
		this.arc= arc;
	}
	public void setColor(int red, int green ,int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.red2 = red;
		this.green2 = green;
		this.blue2 = blue;
		this.alpha = alpha;
	}
	public void borderColor(int red, int green, int blue) {
		this.red2 = red;
		this.green2 = green;
		this.blue2 = blue;
	}
	public void paintComponent(Graphics g) {
		Graphics2D gp = (Graphics2D) g;
		super.paintComponent(gp);
		gp.setColor(new Color(red, green, blue, alpha));
		gp.drawImage(icon.getImage(), 0, 0, null);
		gp.fillRoundRect(0, 0, width+2, height, arc, arc);
		
		
		gp.setColor(new Color(red2, green2, blue2));
		gp.drawRoundRect(0, 0, width+2, height, arc, arc);
		
	}
}
