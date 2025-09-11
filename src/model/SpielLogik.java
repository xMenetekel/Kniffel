package model;

import java.util.ArrayList;
import java.util.List;

public class SpielLogik {

	private List<SpielerLogik> spieler = new ArrayList<>();
	private int aktiverSpielerIndex = 0;
	private int wuerfeAnzahl = 0;
	private int runde = 1;
	
	public void eintragen(MoeglichkeitLogik moeglichkeit) { //? Do we need this
		
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

	public List<SpielerLogik> getSpieler() {
		return spieler;
	}

	public void setSpieler(List<SpielerLogik> spieler) {
		this.spieler = spieler;
	}
	
	public SpielerLogik getAktiverSpieler() {
		if(spieler.isEmpty())return null;
		return spieler.get(aktiverSpielerIndex);
	}
	
	public void naechsterSpieler() {
		if(spieler.isEmpty()) {
			return;
		}
		aktiverSpielerIndex = (aktiverSpielerIndex +1) % spieler.size();
		if(aktiverSpielerIndex == 0) {
			runde++;
		}
		wuerfeAnzahl = 0;
	}
	
	public void erhoeheWurfAnzahl() {
		wuerfeAnzahl ++;
	}
	
	public int getRunde() {
		return runde;
	}
	
	public void setRunde(int runde) {
		this.runde = runde;
	}
	
	public int getSpielerAnzahl() {
		return spieler.size();
	}
	
}
