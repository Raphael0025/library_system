package screens;
import javax.swing.*; 
import java.awt.*;
import custom.*;
import components.*;

@SuppressWarnings("serial")
public class Dashboard extends AguaComponents{
	
	public JLabel title = new JLabel("DASHBOARD");
	public GridLayout lay = new GridLayout(2, 3);
	public JPanel pane = new JPanel(lay);
	public JPanel[] ditm = new JPanel[6];
	
	public Dashboard() {
		init();
	}
	
	public String[] headers = {"Total Books", "Issued Books", "Returned Books", "Late Returns", "No. of Students", "No. of Teachers"}; 
	public String[] icons = {"books", "open-books", "carry-books", "opened-book", "student", "teacher"};
	public String[] def = {"1,150", "25", "50", "20", "2,050", "10"};
	
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
		
		for(int i = 0; i < 6; i++) {
			pane.add(ditm[i] = new dash_item(headers[i], icons[i]));
		}
		
		this.add(title);
		this.add(pane);
	}
	
	public void setContent(int inx, String value) {
		((dash_item)ditm[inx]).setVal(value);
	}
}
