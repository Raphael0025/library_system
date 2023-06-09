package screens;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

import custom.*;
import javax.swing.table.*;
import api_service.*;

@SuppressWarnings("serial")
public class EmployeeList extends AguaComponents{

	public JLabel title = new JLabel("Employee List");
	public JTextField search = new CustomTextField(20, "Search", 20);
	public JButton query = new RoundedButton("Search Staff");
	public JButton addStaff = new RoundedButton("Add New Staff");
	
	String[] column = {"Staff ID","Role", "Staff Name", "Address", "Contact", "Email"};
    public DefaultTableModel model = new DefaultTableModel();
    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    JTable table = new JTable(model){
        @Override
        public boolean editCellAt(int row, int col, java.util.EventObject e){
            return false;
        }
    };
    public JScrollPane scrollTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
	public EmployeeList() {
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
        scrollTable.setBounds(30, 190, 1070, 430);
        render.setFont(new Font("Arial", Font.PLAIN, 12));
        render.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Arial", 1, 16));
        table.getTableHeader().setForeground(Color.white);;
        table.getTableHeader().setBackground(new Color (202, 2, 4));
        for( int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }
		title.setBounds(30,0,250,80);
		title.setFont(new Font("Open Sans", 1, 36));
		title.setForeground(Color.white);
		
		((CustomTextField)search).setBounds(25, 140, 380, 40);
		search.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		search.setFont(new Font("Open Sans", 1, 18));
		search.setForeground(new Color(202, 2, 4));
		
		((RoundedButton)query).setBounds(420, 140, 170, 40);
		((RoundedButton)query).setFill(233, 62, 62, 255, 255, 255);
		((RoundedButton)query).setBorder(233, 62, 62, 255, 255, 255);
		((RoundedButton)query).setFore(255, 255, 255, 255, 0, 0);
		query.setFont(new Font("Open Sans", 1, 18));
		
		((RoundedButton)addStaff).setBounds(890, 140, 170, 40);
		((RoundedButton)addStaff).setFill(233, 62, 62, 255, 255, 255);
		((RoundedButton)addStaff).setBorder(233, 62, 62, 255, 255, 255);
		((RoundedButton)addStaff).setFore(255, 255, 255, 255, 0, 0);
		addStaff.setFont(new Font("Open Sans", 1, 18));
		
		this.add(scrollTable);
		this.add(addStaff);
		this.add(query);
		this.add(search);
		this.add(title);
		
	}   
}
