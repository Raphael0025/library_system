package screens;
import javax.swing.*; 
import java.awt.*;
import custom.*;

@SuppressWarnings("serial")
public class Dashboard extends AguaComponents{
	
	public Dashboard() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setVisible(false);
		setBounds(150, 70, 1120, 630, 20);
		setColor(219,105,108, 255);
	}
}
