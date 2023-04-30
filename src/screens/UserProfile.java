package screens;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import custom.*;

@SuppressWarnings("serial")
public class UserProfile extends AguaComponents{
	
	public JLabel title = new JLabel("USER PROFILE");
	public JButton modify = new RoundedButton("Edit Staff");
	public JButton delS = new RoundedButton("Delete Staff");
	String[] txt = {"ID", "Name", "Type", "Age","Address", "Contact", "Email", "Password"};
	JLabel[] labels = new JLabel[txt.length];
	public JTextField[] tf = new JTextField[txt.length];

	String[] column = {"Book ID","Book Type", "Book Title", "Author", "Published Date", "Availability"};
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    JTable table = new JTable(model){
        @Override
        public boolean editCellAt(int row, int col, java.util.EventObject e){
            return false;
        }
    };
    public JScrollPane scrollTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
	public UserProfile() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setVisible(false);
		setBounds(150, 70, 1120, 630, 20);
		setColor(219,105,108, 255);
		
		//JTable
        for (String column1 : column) {
            model.addColumn(column1);
        }
        scrollTable.setBounds(520, 50, 720, 550);
        render.setFont(new Font("Arial", Font.PLAIN, 12));
        render.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Arial", 1, 12));
        table.getTableHeader().setForeground(Color.white);;
        table.getTableHeader().setBackground(new Color (202, 2, 4));
        for( int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }
        
		title.setBounds(30,0,300,80);
		title.setFont(new Font("Open Sans", 1, 36));
		title.setForeground(Color.white);
		
		int y = 100;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 20));
			labels[i].setForeground(Color.white);
			y+=50;
		}
		
		int y2 = 100;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, "", 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 280, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 18));
			tf[i].setForeground(new Color(202, 2, 4));
			y2+=50;
		}
		
		((RoundedButton)modify).setBounds(30, 550, 160, 40);
		((RoundedButton)modify).setArc(20);
		Default(modify, "edit");
		modify.setFont(new Font("Open Sans", 1, 20));
		
		((RoundedButton)delS).setBounds(200, 550, 180, 40);
		((RoundedButton)delS).setArc(20);
		Default(delS, "del");
		delS.setFont(new Font("Open Sans", 1, 20));
		
		modify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				modify.setIcon(new ImageIcon("src\\assets\\red-edit.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				modify.setIcon(new ImageIcon("src\\assets\\white-edit.png"));
			}
		});
		
		delS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				delS.setIcon(new ImageIcon("src\\assets\\red-del.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				delS.setIcon(new ImageIcon("src\\assets\\white-del.png"));
			}
		});
		
		for(int i = 0; i < labels.length; i++) {
			this.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			this.add(tf[i]);
		}
		
        this.add(scrollTable);
        this.add(delS);
        this.add(modify);
		this.add(title);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(233, 62, 62, 255,255,255);
		((RoundedButton)var).setBorder(233, 62, 62, 255,255,255);
		((RoundedButton)var).setFore(255, 255, 255, 255, 0, 0);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
}
