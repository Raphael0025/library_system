package custom;
import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
public class RoundedButton extends JButton{
	int redFill = 120, greenFill = 115, blueFill = 115, 					// Color Fill
		redBorder = 255, greenBorder = 255, blueBorder = 255,
		redHover = 217, greenHover = 217, blueHover = 217, 					//Hover Fill
		redBorderHover = 34, greenBorderHover = 105, blueBorderHover = 187,
		redFore = 217, greenFore = 217, blueFore = 217, 					//Fore Color
		redForerHover = 34, greenForeHover = 105, blueForeHover = 187,
		arc = 20;															//Default arc size
	public RoundedButton(String lab) {
		super(lab);
		Dimension size = getPreferredSize();
	    size.width = size.height = Math.max(size.width,size.height);
	    setPreferredSize(size);
	    setContentAreaFilled(false);
	    setFocusPainted(false);
	}
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
	    	g.setColor(new Color(redFill, greenFill, blueFill));
	    	setForeground(new Color(redFore, greenFore, blueFore));
	    } else if(getModel().isRollover()) {
	    	setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	g.setColor(new Color(redHover, greenHover, blueHover));
	    	setForeground(new Color(redForerHover, greenForeHover, blueForeHover));
	    } else {
	    	g.setColor(new Color(redFill, greenFill, blueFill));
	    	setForeground(new Color(redFore, greenFore, blueFore));
	    }
	    g.fillRoundRect(0, 0, getSize().width-1,getSize().height-1, arc, arc);
	    super.paintComponent(g);
	}
	protected void paintBorder(Graphics g) {
		if (getModel().isArmed()) {
	    	g.setColor(new Color(redBorder, greenBorder, blueBorder));
	    } else if(getModel().isRollover()) {
	    	g.setColor(new Color(redBorderHover, greenBorderHover, blueBorderHover));
	    } else {
	    	g.setColor(new Color(redBorder, greenBorder, blueBorder));
	    }
	    g.drawRoundRect(0, 0, getSize().width-1,getSize().height-1, arc, arc);
    }
	public void setFill(int r, int g, int b, int rHover, int gHover, int bHover) {
		this.redFill = r;
		this.greenFill = g;
		this.blueFill = b;
		this.redHover = rHover;
		this.greenHover = gHover;
		this.blueHover = bHover;
	}
	public void setBorder(int r, int g, int b, int rHover, int gHover, int bHover) {
		this.redBorder = r;
		this.greenBorder = g;
		this.blueBorder = b;
		this.redBorderHover = rHover;
		this.greenBorderHover = gHover;
		this.blueBorderHover = gHover;
	}
	public void setFore(int r, int g, int b, int rHover, int gHover, int bHover) {
		this.redFore = r;
		this.greenFore = g;
		this.blueFore = b;
		this.redForerHover = rHover;
		this.greenForeHover = gHover;
		this.blueForeHover = gHover;
	}
	public void setArc(int arc) {
		this.arc = arc;
	}
}
