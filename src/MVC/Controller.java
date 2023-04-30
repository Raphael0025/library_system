package MVC;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.*;
import javax.swing.*;
import custom.*; 
import api_service.*;
import screens.*;

@SuppressWarnings("serial")
public class Controller extends MyClass{
	mainApp mp;
	login lp;
	Dashboard dashB;
	topBar tb;
	sideBar sb;
	memberList m_list;
	issuedBook ib;
	bookShelf bs;
	promptFrame pf;
	M_RUD mr;
	NewMemberRecord nmr;
	NewBookRecord nbr;
	B_RUD brud;
	BookRent br;
	ViewIssuedBook vib;
	UserProfile up;
	MemberDashboard md;
	BookInfo bki;
	SQLapi sql = new SQLapi();
	DashResult dr = new DashResult();
	
	public String[] arr;
	
	public Controller(mainApp mp, MemberDashboard memberDash, UserProfile up, login lp, Dashboard dash, topBar tBar, sideBar sb, memberList mbList, bookShelf bs, issuedBook ib){
		this.mp = mp;
		this.lp = lp;
		this.dashB = dash;
		this.tb = tBar;
		this.sb = sb;
		this.m_list = mbList;
		this.bs = bs;
		this.md = memberDash;
		this.ib = ib;
		this.up = up;
		
		lp.loginBtn.addActionListener(this);
		sb.dash.addActionListener(this);
		sb.member.addActionListener(this);
		sb.books.addActionListener(this);
		sb.issued.addActionListener(this);
		lp.member.addActionListener(this);
		lp.staff.addActionListener(this);
		mp.out.addMouseListener(this);
		tb.arrow.addMouseListener(this);
		m_list.query.addActionListener(this);
		m_list.addMember.addActionListener(this);
		bs.addB.addActionListener(this);
		bs.query.addActionListener(this);
		ib.query.addActionListener(this);
		tb.name.addMouseListener(this);
		tb.home.addMouseListener(this);
		md.query.addActionListener(this);
		
//		int len = dashB.def.length;
//		arr = new String[len];
//		arr = dashB.def;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
        String btn = pressed.getText();
        
		switch(btn) {
			case "LOGIN" :
				String user = lp.tf.getText();
				@SuppressWarnings("deprecation") 
				String pass = lp.pf.getText();
				
				if(lp.admin) {
					if(sql.SQLAccount("employees", user, pass)) {
						mp.setSize(1300, 750);
						mp.setLocationRelativeTo(null);
						lp.setVisible(false);
						tb.setVisible(true);
						tb.Title("ADMIN");
						mp.setTitle("Library Management - Dashboard(ADMIN)");
						DashCont();
						
						dashB.setVisible(true);
						sb.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Sorry Wrong Credentials", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if(sql.SQLAccount("member", user, pass)) {
						mp.setSize(1300, 750);
						mp.setLocationRelativeTo(null);
						lp.setVisible(false);
						tb.setVisible(true);
						tb.Title("MEMBER");
						mp.setTitle("Library Management - Dashboard(MEMBER)");
						md.setVisible(true);
						tb.home.setVisible(true);
						
						sql.GetData("book", md.model, "", "");
					}else {
						JOptionPane.showMessageDialog(null, "Sorry Wrong Credentials", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				break;
			case "STAFF":
				lp.changeDefault(lp.staff, lp.member);
				lp.tf.setText("Enter Staff ID");
				lp.admin = true;
				break;
			case "MEMBER":
				lp.changeDefault(lp.member, lp.staff);
				lp.tf.setText("Enter Member ID");
				lp.admin = false;
				break;
			case "DASHBOARD":
				sb.active(sb.dash, "dash");
				sb.Default(sb.member, "members");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				dashB.setVisible(true);
				m_list.setVisible(false);
				bs.setVisible(false);
				ib.setVisible(false);
				up.setVisible(false);
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				DashCont();
				break;
			case "MEMBERS":
				sb.Default(sb.dash, "dash");
				sb.active(sb.member, "members");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				m_list.setVisible(true);
				dashB.setVisible(false);
				bs.setVisible(false);
				ib.setVisible(false);
				up.setVisible(false);
				m_list.search.setText("Search");
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				sql.GetData("member", m_list.model, "", "");
				
				break;
			case "BOOK SHELF":
				sb.Default(sb.dash, "dash");
				sb.Default(sb.member, "members");
				sb.active(sb.books, "shelf");
				sb.Default(sb.issued, "book");
				bs.setVisible(true);
				dashB.setVisible(false);
				m_list.setVisible(false);
				ib.setVisible(false);
				up.setVisible(false);
				bs.search.setText("Search");
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				sql.GetData("book", bs.model, "", "");
				
				break;
			case "ISSUED":
				sb.active(sb.issued, "book");
				sb.Default(sb.member, "members");
				sb.Default(sb.books, "shelf");
				sb.Default(sb.dash, "dash");
				ib.setVisible(true);
				dashB.setVisible(false);
				m_list.setVisible(false);
				bs.setVisible(false);
				ib.search.setText("Search");
				up.setVisible(false);
				for(int i = 0; i < up.tf.length; i++) {
					up.tf[i].setText("");
				}
				sql.GetData("booksissued", ib.model, "", "");
				
				break;
			case "Edit":
				JOptionPane.showMessageDialog(null, mr.tf[0].getText(), "Input Error", JOptionPane.ERROR_MESSAGE);
				break;
			case "Search Member":
				String[] arr = sql.SQLRead("member", m_list.search.getText());
				if(arr[0] == null) {
					JOptionPane.showMessageDialog(null, "No Result Found!", "Error 404", JOptionPane.ERROR_MESSAGE);
				} else {
					
					pf = new promptFrame("Search Member", 700, 720);
					pf.setVisible(true);
					mr = new M_RUD(pf.getWidth(), pf.getHeight());
					mr.edit.addActionListener(this);
					mr.del.addActionListener(this);
					pf.add(mr);
					
					int y2 = 130;
					for(int i = 0; i < mr.tf.length; i++) {
						mr.tf[i] = new CustomTextField(20, arr[i], 10);
						((CustomTextField)mr.tf[i]).setBounds(210, y2, 400, 30);
						mr.tf[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
						mr.tf[i].setFont(new Font("Open Sans", 1, 14));
						mr.tf[i].setForeground(new Color(202, 2, 4));
						y2+=40;
					}
					mr.tf[0].setEditable(false);
					mr.tf[6].setEditable(false);
					mr.tf[7].setEditable(false);
					for(int i = 0; i < mr.tf.length; i++) {
						mr.add(mr.tf[i]);
					}
					sql.GetData("booksissued", mr.modelInfo, mr.tf[1].getText(), "modelInfo");
				}
					
				break;
			case "Issue this Book":
				pf.dispose();
				pf = new promptFrame("Book Rent", 600, 480);
				pf.setVisible(true);
				br = new BookRent(pf.getWidth(), pf.getHeight());
				//br.edit.addActionListener(this);
				pf.add(br);
				
				br.issB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						br.issB.setIcon(new ImageIcon("src\\assets\\red-openbook.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						br.issB.setIcon(new ImageIcon("src\\assets\\white-openbook.png"));
					}
				});
				
				break;
			case "Search Book":
				pf = new promptFrame("Search Book", 600, 480);
				pf.setVisible(true);
				brud = new B_RUD(pf.getWidth(), pf.getHeight());
				//brud.edit.addActionListener(this);
				pf.add(brud);
				brud.editB.addActionListener(this);
				brud.issueB.addActionListener(this);
				
				brud.editB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						brud.editB.setIcon(new ImageIcon("src\\assets\\red-edit.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						brud.editB.setIcon(new ImageIcon("src\\assets\\white-edit.png"));
					}
				});
				
				brud.issueB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						brud.issueB.setIcon(new ImageIcon("src\\assets\\red-openbook.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						brud.issueB.setIcon(new ImageIcon("src\\assets\\white-openbook.png"));
					}
				});
				
				brud.delB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						brud.delB.setIcon(new ImageIcon("src\\assets\\red-del.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						brud.delB.setIcon(new ImageIcon("src\\assets\\white-del.png"));
					}
				});
				
				break;
				
			case "Add New Member":
				pf = new promptFrame("Create New Record", 550, 550);
				pf.setVisible(true);
				nmr = new NewMemberRecord(pf.getWidth(), pf.getHeight());
				nmr.create.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						nmr.create.setIcon(new ImageIcon("src\\assets\\red-edit.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						nmr.create.setIcon(new ImageIcon("src\\assets\\white-edit.png"));
					}
				});
				nmr.gen.addActionListener(this);
				nmr.create.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						sql.SQLCreate("member", nmr.tf[7].getText(), nmr.tf[0].getText(), nmr.tf[1].getText(), nmr.tf[3].getText(), nmr.tf[2].getText(), nmr.tf[4].getText(), nmr.tf[5].getText(), nmr.tf[6].getText(), m_list.model);
				
					}
				});
				
				pf.add(nmr);
				break;
			case "Add New Book":
				pf = new promptFrame("New Book", 600, 450);
				pf.setVisible(true);
				nbr = new NewBookRecord(pf.getWidth(), pf.getHeight());
				nbr.adb.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						nbr.adb.setIcon(new ImageIcon("src\\assets\\red-book.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						nbr.adb.setIcon(new ImageIcon("src\\assets\\white-book.png"));
					}
				});
				
				pf.add(nbr);
				break;
			case "GENERATE":
				PasswordGenerator pg = new PasswordGenerator();
				IDGenerator ig = new IDGenerator();
				
				pg.generator("member");
				ig.generator("member");
				String p = pg.GetPassword();
				String id = ig.GetID();
				
				nmr.tf[6].setText(p);
				nmr.tf[7].setText(id);
				System.out.println(id + " " + p);
				break;
			case "Edit Book":
				JOptionPane.showMessageDialog(null, "Oks");
				break;
			case "Search Issued Book":
				pf = new promptFrame("New Book", 600, 520);
				pf.setVisible(true);
				vib = new ViewIssuedBook(pf.getWidth(), pf.getHeight());
				vib.returnB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						vib.returnB.setIcon(new ImageIcon("src\\assets\\red-book.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						vib.returnB.setIcon(new ImageIcon("src\\assets\\white-book.png"));
					}
				});
				pf.add(vib);
				break;
			case "SEARCH":
				pf = new promptFrame("Search Book", 600,  370);
				pf.setVisible(true);
				bki= new BookInfo(pf.getWidth(), pf.getHeight());
				pf.add(bki);
				break;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == tb.arrow) {
			if(mp.dropdown.isVisible()) {
				mp.dropdown.setVisible(false);
			} else { 
				mp.dropdown.setVisible(true);
			}
		} else if (e.getSource() == mp.out) {
			 logout();
		} else if (e.getSource() ==  tb.name) {
			mp.setTitle("Library Management - User Profile");
			sb.Default(sb.issued, "book");
			sb.Default(sb.member, "members");
			sb.Default(sb.books, "shelf");
			sb.Default(sb.dash, "dash");
			up.setVisible(true);
			ib.setVisible(false);
			md.setVisible(false);
			dashB.setVisible(false);
			m_list.setVisible(false);
			bs.setVisible(false);
			if(lp.admin) {
				up.setBounds(150, 70, 1120, 630, 20);
				up.scrollTable.setVisible(false);
			} else {
				up.setBounds(10, 70, 1260, 630, 20);
				up.scrollTable.setVisible(true);
			}
		} 
		else if (e.getSource() == tb.home) {
			md.setVisible(true);
			up.setVisible(false);
		}
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		Font font;
		
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (e.getSource() == mp.out) {
			font = mp.out.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			mp.out.setFont(font.deriveFont(attributes));
			mp.out.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		} else if(e.getSource() == tb.name) {
			font = tb.name.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			tb.name.setFont(font.deriveFont(attributes));
			tb.name.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if(e.getSource() == tb.home) {
			font = tb.home.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			tb.home.setFont(font.deriveFont(attributes));
			tb.home.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		Font font;
		
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (e.getSource() == mp.out) {
			font = mp.out.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, -1);
			mp.out.setFont(font.deriveFont(attributes));
			mp.out.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if(e.getSource() == tb.name) {
			font = tb.name.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, -1);
			tb.name.setFont(font.deriveFont(attributes));
			tb.name.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if(e.getSource() == tb.home) {
			font = tb.home.getFont();

			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, -1);
			tb.home.setFont(font.deriveFont(attributes));
			tb.home.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	public void logout() {
		mp.setSize(500, 650);
		mp.setLocationRelativeTo(null);
        mp.setTitle("Library Management");
        up.setVisible(false);
        lp.setVisible(true);
		mp.dropdown.setVisible(false);
		tb.setVisible(false);
		sb.setVisible(false);
		dashB.setVisible(false);
		ib.setVisible(false);
		md.setVisible(false);
		m_list.setVisible(false);
		tb.home.setVisible(false);
		bs.setVisible(false);
		for(int i = 0; i < 6; i++) {
			dashB.setContent(i, "");
		}
		sb.active(sb.dash, "dash");
		sb.Default(sb.member, "members");
		sb.Default(sb.books, "shelf");
		sb.Default(sb.issued, "book");
	}
	
	public void DashCont() {

		dashB.setContent(0, String.valueOf(dr.GetTotalBooks()));
		dashB.setContent(1, String.valueOf(dr.GetTotalIssued()));
		dashB.setContent(2, String.valueOf(dr.GetTotalAvailable()));
		dashB.setContent(3, String.valueOf(dr.GetLateBooks()));
		dashB.setContent(4, String.valueOf(dr.GetTotalStudent()));
		dashB.setContent(5, String.valueOf(dr.GetTotalTeachers()));
		
	}
}
