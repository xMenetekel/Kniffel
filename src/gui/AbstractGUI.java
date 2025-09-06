package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractGUI{
	JPanel panel;
	JLabel bild;
	ImageIcon bildIcon;
	
	private final double prozentFlaecheX;
	private final double prozentFlaecheHoehe;
	private final double prozentFlaecheY;
	private final double prozentFlaecheBreite;	
	
	protected AbstractGUI(double x, double y, double breite, double hoehe) {
		this.prozentFlaecheX = x;
		this.prozentFlaecheY = y;
		this.prozentFlaecheBreite = breite;
		this.prozentFlaecheHoehe = hoehe;
		this.panel = new JPanel();
		this.bild = new JLabel();
		bild.setLayout(null);
		panel.setLayout(null);
		panel.setVisible(true);
		bild.setVisible(true);
		panel.setOpaque(false);
		
	}
	private void skaliereBild(int breite, int hoehe) {
		Image bildii = bildIcon.getImage();
		Image skaliertesBild = bildii.getScaledInstance(breite, hoehe, Image.SCALE_SMOOTH);
		bild.setIcon(new ImageIcon(skaliertesBild));
	}
	public void setzeneueGroesse(int breite, int hoehe) {
		skaliereBild(breite, hoehe);
		panel.setSize(breite, hoehe);
		bild.setBounds(0,0, breite, hoehe);
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
	public JLabel getBild() {
		return bild;
	}

	public void setBild(JLabel bild) {
		this.bild = bild;
	}

}

