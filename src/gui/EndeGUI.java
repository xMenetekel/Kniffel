package gui;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;

public class EndeGUI extends AbstractGUI {

	public EndeGUI(Container Eimer) {
		super(0,0,1,1, Eimer);
		
		this.Eimer.setLayout(new GridBagLayout()); 
		bildIcon = new ImageIcon("src/Other/startHintergrund.jpg");	
		bild.setBounds(0,0, Eimer.getWidth(), Eimer.getHeight());
		bild.setIcon(bildIcon);
		panel.add(bild);
	}

}
