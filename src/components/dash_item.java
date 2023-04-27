package components;

import java.awt.*;
import javax.swing.*;
import custom.*;

@SuppressWarnings("serial")
public class dash_item extends AguaComponents{
	
	JLabel title, value, img;
	
	public dash_item(String head, String icon) {
		title = new JLabel(head);
		value = new JLabel();
		img = new JLabel(new ImageIcon("src\\assets\\"+icon+".png"));
		init();
	}
	
	public void init() {
		setLayout(new FlowLayout());
		setColor(255,255,255, 255);
		setBounds(0, 0, getPreferredSize().width*29, getPreferredSize().height*22, 20);
		title.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
		value.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		img.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		
		title.setPreferredSize(new Dimension(280, 30));
		value.setPreferredSize(new Dimension(280, 83));
		img.setPreferredSize(new Dimension(280, 90));
		
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setFont(new Font("Open Sans", 1, 14));
		title.setForeground(new Color(202, 2, 4));
		
		value.setHorizontalAlignment(JLabel.CENTER);
		value.setVerticalAlignment(JLabel.CENTER);
		value.setFont(new Font("Open Sans", 1, 50));
		value.setForeground(new Color(202, 2, 4));
		
		img.setHorizontalAlignment(JLabel.RIGHT);
		img.setVerticalAlignment(JLabel.CENTER);
		
		this.add(title);
		this.add(value);
		this.add(img);
	}
	
	public void setVal(String val) {
		value.setText(val);
	}
}
