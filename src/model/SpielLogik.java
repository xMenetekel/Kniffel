package model;

import java.util.ArrayList;
import java.util.List;

public class SpielLogik {

	private List<SpielerLogik> spieler = new ArrayList<>();
	private int aktiverSpielerIndex = 0;
	private int wuerfeAnzahl = 0;
	private int runde = 1;

	/**
	 * gibt den aktuellen Spieler oder null zurueck
	 * 
	 * @return	aktueller Spieler oder null
	 */
	public SpielerLogik getAktiverSpieler() {
		if(spieler.isEmpty())return null;
		return spieler.get(aktiverSpielerIndex);
	}
	
	/**
	 * setzt naechster spieler aktiv
	 * wenn Runde zu ende wird die Anzahl erhoeht
	 * Anzahl der Wuerfe wird zurueckgesetzt
	 */
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
	
	public int getSpielerAnzahl() {
		return spieler.size();
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

	public List<SpielerLogik> getAlleSpieler() {
		return spieler;
	}

	
	public void setAlleSpieler(List<SpielerLogik> spieler) {
		this.spieler = spieler;
	}
	
	public int getRunde() {
		return runde;
	}
	
	public void setRunde(int runde) {
		this.runde = runde;
	}

}
