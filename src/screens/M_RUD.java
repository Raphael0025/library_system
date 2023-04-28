package screens;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import custom.*;

@SuppressWarnings("serial")
public class M_RUD extends AguaComponents{
	
	static String name = "MEMBER INFORMATION";
	public JLabel header = new JLabel(name);
	public JButton edit = new RoundedButton("Edit");
	public JButton del = new RoundedButton("Delete");
	JLabel[] labels = new JLabel[8];
	JTextField[] tf = new JTextField[8];

	String[] column = {"Books Issued","Issued Date", "Returned Date", "Return Status"};
	String[] txt = {"Member ID", "Name", "Member Type", "Address", "Age", "Contact No.", "Email Address", "Password"};
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    JTable table = new JTable(model){
        @Override
        public boolean editCellAt(int row, int col, java.util.EventObject e){
            return false;
        }
    };
    JScrollPane scrollTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
	public M_RUD(int w, int h) {
		init(w, h);
	}
	public void init(int w, int h) {
		
		setLayout(null);
		setBounds(0, 0, w, h, 0);
		setColor(233,62,62, 255);
		
		header.setBounds(30,10,w,50);
		header.setFont(new Font("Open Sans", 1, 30));
		header.setForeground(Color.white);
		
		((RoundedButton)edit).setBounds(370, 70, 120, 50);
		((RoundedButton)edit).setArc(20);
		Default(edit, "edit");
		edit.setFont(new Font("Open Sans", 1, 20));
		
		((RoundedButton)del).setBounds(490, 70, 130, 50);
		((RoundedButton)del).setArc(20);
		Default(del, "del");
		del.setFont(new Font("Open Sans", 1, 20));
		
		int y = 130;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 16));
			labels[i].setForeground(Color.white);
			y+=40;
		}
		
		int y2 = 130;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, "", 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 400, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(new Color(202, 2, 4));
			y2+=40;
		}
		//JTable
        for (String column1 : column) {
            model.addColumn(column1);
        }
        scrollTable.setBounds(30, 470, 620, 200);
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
        for(int i = 0; i < labels.length; i++) {
			this.add(labels[i]);
		}
        for(int i = 0; i < tf.length; i++) {
			this.add(tf[i]);
		}
        this.add(header);
        this.add(scrollTable);
		this.add(edit);
		this.add(del);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(233, 62, 62, 219,105,108);
		((RoundedButton)var).setBorder(233, 62, 62, 219,105,108);
		((RoundedButton)var).setFore(255, 255, 255, 255, 255, 255);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
}
