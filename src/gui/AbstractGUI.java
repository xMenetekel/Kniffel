package gui;

import java.awt.Container;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractGUI{
	
	JPanel panel;
	JLabel bild;
	ImageIcon bildIcon;
	JButton knopf;
	Container eimer;
	
	private final double prozentFlaecheX;
	private final double prozentFlaecheHoehe;
	private final double prozentFlaecheY;
	private final double prozentFlaecheBreite;	
	
	/**
	 * Initialisiert ein GUI Element mit relativer Positionierung, in allen Klassen die diese Klasse erben sind die Werte mit X, Y, B, H als 0.0-1.0 Werte festgesetzt um 
	 * sich dann individuell an den Container berechnen zu können.
	 * 
	 * @param x			x Position
	 * @param y			y Position
	 * @param breite	Breite
	 * @param hoehe		Hoehe
	 * @param Eimer		uebergeordneter Container auf den sich die Werte beziehen
	 */
	protected AbstractGUI(double x, double y, double breite, double hoehe, Container eimer) {
		this.eimer = eimer;
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
	
	/**
	 * hilfsfunktion für setzeNeueGroesse, welche das gespeicherte Icon des Objects in ein Image zwischenspeichert und dann an die neuen breiten/hoehen Werte skaliert
	 * Danach wird das Bild auf den Button gesetzt, wenn vorhanden. Ansonsten wird einfach die Hintergrundfläche angepasst.
	 * 
	 * @param breite
	 * @param hoehe
	 */
	private void skaliereBild(int breite, int hoehe) {
		Image bildii = bildIcon.getImage();
		Image skaliertesBild = bildii.getScaledInstance(breite, hoehe, Image.SCALE_SMOOTH);
		if(knopf != null) {
			knopf.setIcon(new ImageIcon(skaliertesBild));
		} else {
		bild.setIcon(new ImageIcon(skaliertesBild));
		}
	}

	/**
	 * setzt das Panel indem alle Komponenten der übergeordneten Komponente auf seine neue Größe und passt danach das Bild des Panels auf entsprechend neue Groesse an.
	 * 
	 * @param breite
	 * @param hoehe
	 */
	public void setzeNeueGroesse(int breite, int hoehe) {
		panel.setSize(breite, hoehe);
		bild.setBounds(0,0, breite, hoehe);
		skaliereBild(breite, hoehe);			
	}
	
	
	//Getter und Setter
	
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
	
	public ImageIcon getBildIcon() {
		return bildIcon;
	}
	
	public void setBildIcon(ImageIcon bildIcon) {
		this.bildIcon = bildIcon;
	}
	
	public Container getEimer() {
		return eimer;
	}
	
	public void setEimer(Container eimer) {
		this.eimer = eimer;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getKnopf() {
		return knopf;
	}
	
	public void setKnopf(JButton knopf) {
		this.knopf = knopf;
	}

}

