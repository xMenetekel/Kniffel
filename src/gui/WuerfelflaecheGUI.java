package gui;

import java.awt.Container;
import javax.swing.ImageIcon;

public class WuerfelflaecheGUI extends AbstractGUI {

	private static final double X = 0.03;		 
	private static final double Y = 0.2;		
	private static final double B = 0.4375; 	
	private static final double H = 0.301659125;
	
	/**
	 * erzeugt Wuerfelflaeche
	 * 
	 * @param eimer Container in der die Wuerfelflaeche angezeigt wird
	 */
	public WuerfelflaecheGUI(Container eimer) {
		super(X, Y, B, H, eimer);
		
		bildIcon = new ImageIcon("src/other/wuerfelflaeche.jpg");		
		setzeNeueGroesse(
			(int)(B * eimer.getWidth()), 
			(int)(H * eimer.getHeight())
		);					
		panel.add(bild);			
	}
	
}
