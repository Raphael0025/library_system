package custom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
@SuppressWarnings("serial")
public class CustomTextField extends JTextField{
	private Shape shape;
	private int arc;
	public CustomTextField(int size, String ph, int arc) {
		super(size);
		setOpaque(false);
		this.setBackground(null);
		setText(ph);
		placeHolder(this, ph);
		this.arc = arc;
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
		super.paintComponent(g);
	}
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
	}
	
	public void placeHolder(JTextField obj, String ph) {
		String temp = ph;
		obj.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				obj.setText("");
			}
			@Override
			public void focusLost(FocusEvent fe) {
				if(obj.getText().isEmpty()) {
					obj.setText(temp);
				}
			}
		});
	}
}