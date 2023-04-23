package custom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
@SuppressWarnings("serial")
public class CustomTextField extends JTextField{
	private Shape shape;
	public CustomTextField(int size, String ph) {
		super(size);
		setOpaque(false);
		this.setBackground(null);
		setText(ph);
		placeHolder(this, ph);
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
		super.paintComponent(g);
	}
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
	}
	public boolean contains(int x, int y) {
		if(shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight() -1, 15, 15);
		}
		return shape.contains(x, y);
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