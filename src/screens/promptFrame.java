package screens;

import java.awt.Dialog;

import javax.swing.*;
import custom.*;

@SuppressWarnings("serial")
public class promptFrame extends JDialog{
	
	// put width and height
	public promptFrame(String name, int w, int h) {
		setTitle(name);
		setSize(w, h);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
}
