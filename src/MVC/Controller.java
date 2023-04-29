package MVC;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import custom.*; 
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
		
		int len = dashB.def.length;
		arr = new String[len];
		arr = dashB.def;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
        String btn = pressed.getText();
        
		switch(btn) {
			case "LOGIN" :
				mp.setSize(1300, 750);
				mp.setLocationRelativeTo(null);
				lp.setVisible(false);
				tb.setVisible(true);
				
				if(lp.admin) {
					tb.Title("ADMIN");
					mp.setTitle("Library Management - Dashboard(ADMIN)");
					for(int i = 0; i < 6; i++) {
						dashB.setContent(i, arr[i]);
					}
					dashB.setVisible(true);
					sb.setVisible(true);
				} else {
					tb.Title("MEMBER");
					mp.setTitle("Library Management - Dashboard(MEMBER)");
					md.setVisible(true);
					tb.home.setVisible(true);
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
				
				for(int i = 0; i < 6; i++) {
					//dashB.setVal(i, def[i]);
					
				}
				
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
				
				break;
			case "Search Member":
				pf = new promptFrame("Search Member", 700, 720);
				pf.setVisible(true);
				mr = new M_RUD(pf.getWidth(), pf.getHeight());
				mr.edit.addActionListener(this);
				pf.add(mr);
				
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
				//nmr.edit.addActionListener(this);
				
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
		} 
//		else if (e.getSource() == tb.home) {
//			md.setVisible(true);
//			up.setVisible(false);
//		}
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (e.getSource() == mp.out) {
			mp.out.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if(e.getSource() == tb.name) {
			tb.name.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == tb.arrow) {
			tb.arrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (e.getSource() == mp.out) {
			mp.out.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if(e.getSource() == tb.name) {
			tb.name.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
}
