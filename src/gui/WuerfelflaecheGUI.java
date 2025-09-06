package gui;

import java.awt.Image;

import javax.swing.ImageIcon;



public class WuerfelflaecheGUI extends AbstractGUI {
	
	
	private static final double X = 0.0625;	
	private static final double Y = 0.285;	
	private static final double B = 0.4375;
	private static final double H = 0.301659125;
	

	public WuerfelflaecheGUI() {
		super(X, Y, B, H);
		bildIcon = new ImageIcon("Other/wuerfelflaeche.jpg");			
		setzeneueGroesse(350,200);						
		panel.add(bild);				
		
	}

	
	public void zeigeWuerfel() {
		
	}
	

}
