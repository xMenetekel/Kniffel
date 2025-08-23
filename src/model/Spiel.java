package model;

import java.util.ArrayList;
import java.util.List;

public class Spiel {

	private List<Spieler> spieler = new ArrayList<>();
	
	private int aktiverSpielerIndex = 0;
	
	private int wuerfeAnzahl = 0;
	
	public void eintragen(Moeglichkeit moeglichkeit) {
		
	}

	public int getAktiverSpielerIndex() {
		return aktiverSpielerIndex;
	}

	public void setAktiverSpielerIndex(int aktiverSpielerIndex) {
		this.aktiverSpielerIndex = aktiverSpielerIndex;
	}

	public int getWuerfeAnzahl() {
		return wuerfeAnzahl;
	}

	public void setWuerfeAnzahl(int wuerfeAnzahl) {
		this.wuerfeAnzahl = wuerfeAnzahl;
	}

	public List<Spieler> getSpieler() {
		return spieler;
	}

	public void setSpieler(List<Spieler> spieler) {
		this.spieler = spieler;
	}
	
}
