package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WuerfelBankGUI extends AbstractGUI {

	
	private static final double X = 0.09375; 	// 75 bei 800
	private static final double Y = 0.592857;	 //414 bei 700
	private static final double H = 0.085714; //nicht nutzen zum resizen
	private static final double B = 0.35;	 //auch nicht nutzen zum resizen
	
	public WuerfelBankGUI() {
		super(X, Y, B, H);
		ImageIcon bildi = new ImageIcon("Other/wuerfelbank.jpg" );
		
		bild.setIcon(bildi);
		
		bild.setBounds(0, 0, 280,60);		
		
		panel.add(bild);
		
	}

	
	

	
	
	
	

}
