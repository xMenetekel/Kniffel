package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class WuerfelBankGUI extends AbstractGUI {

	// Pos und Groeße  der Wuerfelbank in abhaengigkeit zum Container
	private static final double X = 0.09375; 	
	private static final double Y = 0.592857;	 
	private static final double H = 0.085714; // Hoehe Wuerfelbank nicht skalieren
	private static final double B = 0.35;	  // Breite Wuerfelbank nicht skalieren
	
	// Liste der Wuerfel in der Bank
	List<WuerfelGUI> reihenfolge;
	
	public WuerfelBankGUI(Container Eimer) {
		super(X, Y, B, H, Eimer);
		
		bildIcon = new ImageIcon("Other/wuerfelbank.jpg" );
		this.bild.setIcon(bildIcon);
		
		this.panel.setPreferredSize(new Dimension((int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight())));
		
		this.bild.setBounds(0,0,(int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight()));
		
		reihenfolge = new ArrayList<>();
		
		this.panel.add(bild);		
	}
	
	// Getter und Setter 
	public List<WuerfelGUI> getReihenfolge() {
		return reihenfolge;
	}

	public void setReihenfolge(List<WuerfelGUI> reihenfolge) {
		this.reihenfolge = reihenfolge;
	}	
}
