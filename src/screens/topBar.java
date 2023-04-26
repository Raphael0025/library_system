package screens;

import java.awt.*;
import javax.swing.*;
import custom.*;

@SuppressWarnings("serial")
public class topBar extends AguaComponents{
	public JLabel name = new JLabel("DEFAULT");
	public JLabel arrow = new JLabel(new ImageIcon("src\\assets\\arrow-down.png"));
	
	public topBar() {
		name.setFont(new Font("Open Sans", 1, 16));
		name.setForeground(Color.white);
		
		setVisible(false);
		setBounds(130,0,1155,60,0);
		setLayout(new FlowLayout(FlowLayout.TRAILING));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 30));
		setColor(233, 62, 62, 255);
		borderColor(233, 62, 62);
		
		this.add(name);
		this.add(arrow);
	}
	
	public void Title(String title) {
		name.setText(title.toUpperCase());
	}
}
