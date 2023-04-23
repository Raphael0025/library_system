package custom;
import javax.swing.*;
@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	public MyFrame(String _AppName, int w, int h){
        this.setTitle(_AppName);
        this.setSize(w, h);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
