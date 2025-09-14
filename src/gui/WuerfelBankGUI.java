package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class WuerfelBankGUI extends AbstractGUI {

	private static final double X = 0.09375; 	
	private static final double Y = 0.586;	 
	private static final double H = 0.085714; 
	private static final double B = 0.35;	 	
	
	List<WuerfelGUI> reihenfolge;
	
	/**
	 * erstellt Wuerfelbank in Containter
	 * 
	 * @param eimer		Container in dem die Bank angezeigt wird
	 */
	public WuerfelBankGUI(Container eimer) {
		super(X, Y, B, H, eimer);
		
		bildIcon = new ImageIcon("src/other/wuerfelbank.jpg" );
		this.bild.setIcon(bildIcon);		
		this.panel.setPreferredSize(new Dimension((int)(B * eimer.getWidth()), (int)(H * eimer.getHeight())));		
		this.bild.setBounds(0,0,(int)(B * eimer.getWidth()), (int)(H * eimer.getHeight()));		
		reihenfolge = new ArrayList<>();		
		this.panel.add(bild);		
	}
	
	//Getter und Setter
	
	public List<WuerfelGUI> getReihenfolge() {
		return reihenfolge;
	}

	public void setReihenfolge(List<WuerfelGUI> reihenfolge) {
		this.reihenfolge = reihenfolge;
	}	
}
