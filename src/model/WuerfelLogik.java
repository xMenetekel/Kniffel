package model;

import java.util.Random;
import gui.WuerfelGUI;

public class WuerfelLogik {

	private int seiten;
	private int augen;
	private boolean behalten;
	private Random zufall;
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

	public WuerfelGUI getGui() {
		return gui;
	}

	public void setGui(WuerfelGUI gui) {
		this.gui = gui;
	}
	
}

