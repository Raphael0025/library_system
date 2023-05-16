package screens;
import javax.swing.*;
import javax.swing.table.*;
import api_service.*;
import java.awt.*;
import java.awt.event.*;
import custom.*;

@SuppressWarnings("serial")
public class M_RUD extends JDialog implements ActionListener{
	
	static String name = "MEMBER INFORMATION";
	public JLabel header = new JLabel(name);
	public JButton edit = new RoundedButton("Edit");
	public JButton del = new RoundedButton("Delete");
	JLabel[] labels = new JLabel[8];
	public JTextField[] tf = new JTextField[8];
	JPanel pan = new JPanel();
	memberList ml = new memberList();
	SQLapi sql = new SQLapi();
	
	String[] column = {"Books Issued","Issued Date", "Returned Date", "Return Status"};
	String[] txt = {"Member ID", "Name", "Member Type", "Age", "Address", "Contact No.", "Email Address", "Password"};
    public DefaultTableModel modelInfo = new DefaultTableModel();
    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    public JTable table = new JTable(modelInfo){
        @Override
        public boolean editCellAt(int row, int col, java.util.EventObject e){
            return false;
        }
    };
    public  JScrollPane scrollTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
	public M_RUD(int w, int h, String[] arr) {
		init(w, h, arr);
	}
	public void init(int w, int h, String[] arr) {
		this.setTitle("Search Member");
		this.setSize(w, h);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		pan.setBounds(0, 0, w, h);
		pan.setBackground(new Color(233,62,62));
		pan.setLayout(null);
		
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
		
		int y2 = 130;
		for(int i = 0; i < tf.length; i++) {
			tf[i] = new CustomTextField(20, arr[i], 10);
			((CustomTextField)tf[i]).setBounds(210, y2, 400, 30);
			tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			tf[i].setFont(new Font("Open Sans", 1, 14));
			tf[i].setForeground(new Color(202, 2, 4));
			y2+=40;
		}
		tf[0].setEditable(false);
		tf[0].setEnabled(false);
		tf[6].setEditable(false);
		tf[7].setEditable(false);
		for(int i = 0; i < tf.length; i++) {
			pan.add(tf[i]);
		}
		
		int y = 130;
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(txt[i].concat(":"));
			labels[i].setBounds(40, y, 150, 30);
			labels[i].setFont(new Font("Open Sans", 1, 16));
			labels[i].setForeground(Color.white);
			y+=40;
		}
		//JTable
        for (String column1 : column) {
        	modelInfo.addColumn(column1);
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
        
        edit.addActionListener(this);
		del.addActionListener(this);
		for(int i = 0; i < labels.length; i++) {
			pan.add(labels[i]);
		}
        pan.add(header);
        pan.add(scrollTable);
		pan.add(edit);
		pan.add(del);
		
		this.add(pan);
	}
	
	public void Default(JButton var, String img) {
		((RoundedButton)var).setFill(233, 62, 62, 219,105,108);
		((RoundedButton)var).setBorder(233, 62, 62, 219,105,108);
		((RoundedButton)var).setFore(255, 255, 255, 255, 255, 255);
		var.setIcon(new ImageIcon("src\\assets\\white-"+img+".png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton press = ( JButton ) e.getSource();
		String btn = press.getText(), id, pass;
		
		if(btn.equals("Generate")) {
			PasswordGenerator pg2 = new PasswordGenerator();
			IDGenerator idg = new IDGenerator();
			
			pg2.generator("member");
			pass = pg2.GetPassword();
			id = idg.generator("member");
			
			tf[6].setText(pass);
			tf[7].setText(id);
		} else if (btn.equals("Delete")) {
			String id3 = tf[0].getText();
			sql.SQLDelete("member", id3, ml.model);
		} else if(btn.equals("Edit")) {
			String id2 = tf[0].getText();
			String[] temp = new String[7];
			for(int i = 0; i < temp.length; i++) {
				temp[i] = tf[i+1].getText();
			}
			sql.SQLUpdate("member", id2, temp, ml.model);
		}
	}
}
