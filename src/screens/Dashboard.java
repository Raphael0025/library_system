package screens;
import javax.swing.*; 
import java.awt.*;
import custom.*;
import components.*;

@SuppressWarnings("serial")
public class Dashboard extends AguaComponents{
	
	JLabel title = new JLabel("DASHBOARD");
	GridLayout lay = new GridLayout(2, 3);
	JPanel pane = new JPanel(lay);
	JPanel ditm;
	
	public Dashboard() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setVisible(false);
		setBounds(150, 70, 1120, 630, 20);
		setColor(219,105,108, 255);
		
		title.setBounds(30,0,250,80);
		title.setFont(new Font("Open Sans", 1, 36));
		title.setForeground(Color.white);
		pane.setBounds(0,60,getWidth(), getHeight()-70);
		
		lay.setHgap(60);
		lay.setVgap(50);
		
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
		
		pane.add(ditm = new dash_item("Total Books", "1, 150", "books"));
		pane.add(ditm = new dash_item("Issued Books", "25", "open-books"));
		pane.add(ditm = new dash_item("Returned Books", "50", "carry-books"));
		pane.add(ditm = new dash_item("Late Returns", "20", "opened-book"));
		pane.add(ditm = new dash_item("No. of Students", "2, 050", "student"));
		pane.add(ditm = new dash_item("No. of Teachers", "10", "teacher"));
		
		this.add(title);
		this.add(pane);
	}
}
