package gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WuerfelBankGUI extends AbstractGUI {

	
	private static final double X = 0.09375; 	
	private static final double Y = 0.592857;	 
	private static final double H = 0.085714; //nicht nutzen zum resizen //sagte sie und nutzt nur diese werte. also jedenfalls nicht skalieren diese komponente haha
	private static final double B = 0.35;	 //auch nicht nutzen zum resizen
	
	public WuerfelBankGUI(Container Eimer) {
		super(X, Y, B, H, Eimer);
		bildIcon = new ImageIcon("Other/wuerfelbank.jpg" );
		
		bild.setIcon(bildIcon);
		panel.setPreferredSize(new Dimension((int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight())));
		bild.setBounds(0,0,(int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight()));
			
		
		panel.add(bild);
		
	}

	
	

	
	
	
	

}
