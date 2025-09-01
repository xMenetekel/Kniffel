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
	
	
	//Konstruktor 
	public WuerfelLogik(int seiten) {
		this.seiten = seiten;
		this.behalten = false;
		this.zufall = new Random();
		wuerfeln();
	}
	
	
	public void wuerfeln() {
		if(!behalten) {
			augen = zufall.nextInt(seiten)+1; //keine 0 seiten
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
	
}

