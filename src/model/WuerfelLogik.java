package model;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

import gui.WuerfelGUI;


public class WuerfelLogik {

	private int seiten;
	private int augen;
	private boolean behalten;
	private Random zufall;
	private int bankXPosition;
	private int bankYPosition;
	private WuerfelGUI gui;
		
	/**
	 * Initialisiert den Wuerfel mit Seitenanzahl und wurfelt
	 * 
	 * @param seiten Wuerfelseiten
	 */
	public WuerfelLogik(int seiten) {
		this.seiten = seiten;
		this.behalten = false;
		
		this.zufall = new Random();
		wuerfeln();	
	}
	
	/**
	 * wuerfelt fallst nicht behalten
	 * +1 weil keine 0 seiten
	 */
	public void wuerfeln() {
		if(!behalten) {
			this.augen = zufall.nextInt(seiten)+1;
		}	
	}
	
	
	public int getAugen() {
		return augen;
	}
	
	public void setAugen(int augen) {
		this.augen = augen;
	}
	
	public int getSeiten() {
		return seiten;
	}
	
	public void setBehalten(boolean behalten) {
		this.behalten = behalten;
		
	}
	
	public boolean istBehalten() {
		return behalten;
	}
	
	public void nichtBehalten() {
		behalten = false;
	}

	public void anzahlZuege() {
		
	}
	
	public void positionWuerfel() {
		
	}

	public int getBankXPosition() {
		return bankXPosition;
	}

	public void setBankXPosition(int bankXPosition) {
		this.bankXPosition = bankXPosition;
	}

	public int getBankYPosition() {
		return bankYPosition;
	}

	public void setBankYPosition(int bankYPosition) {
		this.bankYPosition = bankYPosition;
	}

	public WuerfelGUI getGui() {
		return gui;
	}

	public void setGui(WuerfelGUI gui) {
		this.gui = gui;
	}
	
}

