package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractGUI implements GUIProzente{
	JPanel panel;
	JLabel bild;
	private final double prozentFlaecheX;
	private final double prozentFlaecheHoehe;
	private final double prozentFlaecheY;
	private final double prozentFlaecheBreite;	
	
	protected AbstractGUI(double x, double y, double breite, double hoehe) {
		this.prozentFlaecheX = x;
		this.prozentFlaecheY = y;
		this.prozentFlaecheBreite = breite;
		this.prozentFlaecheHoehe = hoehe;
		
	}

	public double getProzentFlaecheX() {
		return this.prozentFlaecheX;
	}
	
	public double getProzentFlaecheHoehe() {
		return this.prozentFlaecheHoehe;
	}
	
	public double getProzentFlaecheY() {
		return this.prozentFlaecheY;
	}
	
	public double getProzentFlaecheBreite() {
		return this.prozentFlaecheBreite;
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
}

