package gui;

import java.awt.Container;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

import model.WuerfelLogik;
import presenter.WuerfelPresenter;

public class WuerfelGUI {
	JButton wuerfel;
	WuerfelLogik model;
	int xPos;
	int yPos;
	int startX;
	int startY;
	public double prozentXAufFeld;
	public double prozentYAufFeld;
	Container eimer;
	private Random zufall;
	private WuerfelPresenter presenter; 
	boolean istinBank;
	
	/**
	 * Konstruktor
	 * 
	 * @param presenter		Presenter fuer Interaktion mit GUI
	 * @param model			Logik der Wuerfels
	 * @param startX		Start Position X
	 * @param startY		Start Position Y
	 * @param eimer			Container fuer Darstellung
	 */
	public WuerfelGUI(WuerfelPresenter presenter, WuerfelLogik model, int startX, int startY, Container eimer){
		this.setPresenter(presenter);
		this.model = model;
		this.wuerfel = new JButton();
		this.startX = startX;
		this.startY = startY;
		this.zufall = new Random();
		this.eimer = eimer;
		this.xPos = startX;
		this.yPos = startY;
		fresherButton();
		this.prozentXAufFeld = startX * 1.0 / eimer.getWidth();
		this.prozentYAufFeld = startY * 1.0 / eimer.getHeight();
		wuerfel.addActionListener(klick -> presenter.wuerfelAngeklickt(this));
		this.istinBank = true;	
	}
	
	/**
	 * Setzt Icon entsprechend der Augenzahl 
	 */
	public void fresherButton() {
		ImageIcon bildi = new ImageIcon("src/other/wuerfel" + model.getAugen() + "und50.png" );
		wuerfel.setIcon(bildi);
		wuerfel.setVisible(true);
		wuerfel.setBounds(this.xPos, this.yPos, 50, 50);
	}	
	
	/**
	 * /**
	 * Rechnet mit der Gradengleichung die neuen Werte aus um dann Schrittweise in einer schöden Gerade den Würfel von A nach B zu transportieren
	 * 
	 * Bei flachem Anstieg muss man x Schrittweise berechnen und bei starken Anstieg y Schrittweise. Wenn m nicht mit 1.0 multipliziert wird dann
	 * wertet es nicht als Double aus und das ganze kriegt einen hässligen Knick, das plus Hilfe bei der Geschwindigkeit vom Timer (Also das eben erwähnte
	 * ob man x ODER y Schrittweise inkrementiert) und wie Timer generell funktionieren wurde mit KI Hilfe verstanden. 
	 * 
	 * Für langsamer / Schneller das x bei new Timer{x, e -> ...} vergrößern / verkleinern
	 * und TOL anpassen. je größer, desto schneller. aber auch desto unschöner wird die würfelbank, weils ungenauer zurückfliegt
	 * 
	 * @param wuerfel welche ich mitgebe um da die positionen zu ändern und da den würfelbutton rauszuziehen
	 * @param zielX der x wert wo es hingezeichnet werden soll
	 * @param zielY der y wert wo es hingezeichnet werden soll
	 */
	public void zeichneLinie (JButton wuerfel, int zielX, int zielY, boolean wuerfeln) {
		this.setxPos(zielX);
		this.setyPos(zielY);
		double m = (zielX - wuerfel.getX() == 0) ? 0 : (zielY - wuerfel.getY()) * 1.0 / (zielX - wuerfel.getX());
		double n = zielY - m * zielX;
		int[] moduloBoi = new int[] {
				wuerfeln ? 0 : 1
		};
		final int TOL = 4;
		
		Timer timer = new Timer(6, e -> animiereSchritt(wuerfel, zielX, zielY, m, n, moduloBoi, TOL, wuerfeln, (Timer)e.getSource()));
		timer.start();
	}
	
	/**
	 * Ausgelagerte Hilfsfunktion für zeichne Linie
	 * 
	 * @param wuerfel
	 * @param zielX
	 * @param zielY
	 * @param m gradengleichungs m
	 * @param n gradengleichungs n
	 * @param moduloBoi (Nimmt nur aller paar Timerschritte einen Bildwechsel auf, wichtig für Windows)
	 * @param TOL Toleranz (Wichtig damit es auf Windows schön aussieht)
	 * @param wuerfeln
	 * @param timer
	 */
	private void animiereSchritt(JButton wuerfel, int zielX, int zielY, 
			double m, double n, int[] moduloBoi, int TOL, 
			boolean wuerfeln, Timer timer) {
		int xJetzt = wuerfel.getX();
		int yJetzt = wuerfel.getY();
		moduloBoi[0]++;
		if (wuerfeln && moduloBoi[0] % 5 == 0) wuerfel.setIcon(new ImageIcon("src/other/wuerfel" + (zufall.nextInt(5)+1) + "und50.png"));
		
		if(Math.abs(m) <= 1) {
			xJetzt = bewegeRichtung(xJetzt, zielX, TOL);
			int yNeu = (int)(m * xJetzt + n);
			wuerfel.setLocation(xJetzt, yNeu);
			updateProzentAufFeld();
			pruefeZiel(wuerfel, xJetzt, yNeu, zielX, zielY, TOL, timer);
		} else {
			yJetzt = bewegeRichtung(yJetzt, zielY, TOL);
			int xNeu = (int)((yJetzt - n) / m);
			wuerfel.setLocation(xNeu, yJetzt);
			updateProzentAufFeld();
			pruefeZiel(wuerfel, xNeu, yJetzt, zielX, zielY, TOL, timer);
		}
	}
	
	/**
	 * Hilsfmethode
	 * Berechnet den nachsten Schritt
	 * wenn kleiner als Ziel dann wird erhoeht wenn großer verringert
	 * wenn gleich dann gleich
	 * 
	 * @param aktuell		die aktuelle Position
	 * @param ziel			die Ziel Position
	 * @param schritt		Schrittweite
	 * @return				gibt neue Position zurueck
	 */
	private int bewegeRichtung(int aktuell, int ziel, int schritt) {
		if(aktuell < ziel)
			return aktuell + schritt;
		if(aktuell > ziel)
			return aktuell - schritt;
		return aktuell;
	}
	
	/**
	 * Prueft ob der Wuerfel das Ziel erreicht hat
	 * 
	 * @param wuerfel	JButton des Wuerfelst
	 * @param x			aktuelle X Position
	 * @param y			aktuelle Y Position
	 * @param zielX		ziel in X
	 * @param zielY 	ziel in Y
	 * @param toleranz	erlaubte abweichung zum Ziel
	 * @param timer		steuert die Animation
	 */
	private void pruefeZiel(JButton wuerfel, int x, int y, int zielX, int zielY,
			int toleranz, Timer timer) {
		if(x >= zielX - toleranz && x <= zielX + toleranz && y >= zielY - toleranz && y <= zielY + toleranz) {
			timer.stop();
			wuerfel.setIcon(new ImageIcon ("src/other/wuerfel" + this.model.getAugen() + "und50.png"));
		}
	}
	/**
	 * hilfsmethode um die Positionen upzudaten
	 */
	private void updateProzentAufFeld() {
		this.setProzentXAufFeld(this.getxPos() * 1.0 / this.getEimer().getWidth());
		this.setProzentYAufFeld(this.getyPos() * 1.0 / this.getEimer().getHeight());
	}
	
	
	
	//Getter und Setter
	
	public JButton getButton() {
		return this.wuerfel;
	}
	
	public WuerfelLogik getLogik() {
		return this.model;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public double getProzentXAufFeld() {
		return prozentXAufFeld;
	}
	
	public void setProzentXAufFeld(double prozentXAufFeld) {
		this.prozentXAufFeld = prozentXAufFeld;
	}
	
	public double getProzentYAufFeld() {
		return prozentYAufFeld;
	}
	
	public void setProzentYAufFeld(double prozentYAufFeld) {
		this.prozentYAufFeld = prozentYAufFeld;
	}
	
	public Container getEimer() {
		return eimer;
	}
	
	public void setEimer(Container eimer) {
		this.eimer = eimer;
	}

	public WuerfelPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(WuerfelPresenter presenter) {
		this.presenter = presenter;
	}
	public boolean isIstinBank() {
		return istinBank;
	}

	public void setIstinBank(boolean istinBank) {
		this.istinBank = istinBank;
	}
}

