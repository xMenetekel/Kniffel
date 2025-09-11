package gui;

import java.awt.Container;
import java.awt.Image;

import javax.swing.ImageIcon;



public class WuerfelflaecheGUI extends AbstractGUI {
	
	
	private static final double X = 0.03;	
	private static final double Y = 0.2;	
	private static final double B = 0.4375;
	private static final double H = 0.301659125;
	

	public WuerfelflaecheGUI(Container Eimer) {
		super(X, Y, B, H, Eimer);
		bildIcon = new ImageIcon("Other/wuerfelflaeche.jpg");			
		setzeNeueGroesse((int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight()));						
		panel.add(bild);	
		
		
	}

	
	public void zeigeWuerfel() {
		
	}
	

}
