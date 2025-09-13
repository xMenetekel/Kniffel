package gui;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;

import model.SpielLogik;
import presenter.EndePresenter;

public class EndeGUI extends AbstractGUI {

	public EndeGUI(Container eimer, EndePresenter presenter) {
		super(0,0,1,1, eimer);
		
		this.eimer.setLayout(new GridBagLayout()); 
		bildIcon = new ImageIcon("src/Other/startHintergrund.jpg");	
		bild.setBounds(0,0, eimer.getWidth(), eimer.getHeight());
		bild.setIcon(bildIcon);
		
		
		
		
		
		
		
		
		
		
		
		panel.add(bild);
	}

}
