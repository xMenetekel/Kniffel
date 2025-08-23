package model;

import java.util.ArrayList;
import java.util.List;

public class SpielDaten {

	private List<SpielerDaten> spieler = new ArrayList<>();
	
	private int aktiverSpielerIndex = 0;
	
	private int wuerfeAnzahl = 0;
	
	public void eintragen(MoeglichkeitDaten moeglichkeit) { //?
		
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

	public List<SpielerDaten> getSpieler() {
		return spieler;
	}

	public void setSpieler(List<SpielerDaten> spieler) {
		this.spieler = spieler;
	}
	
}
