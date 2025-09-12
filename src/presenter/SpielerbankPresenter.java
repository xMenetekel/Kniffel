package presenter;

import java.awt.Container;
import java.util.List;

import gui.SpielerbankGUI;
import model.SpielLogik;
import model.SpielerLogik;

public class SpielerbankPresenter {
	
	private SpielerbankGUI gui;
	private List<SpielerLogik> spielerListe;
	private SpielLogik logik;
	
	/**
	 * Konstruktor erzeugt Presenter mit Spielerliste und Logik
	 * 
	 * @param eimer			Container, in dem die GUI plaziert wird
	 * @param spielerListe	Liste der SpielerModelle
	 * @param logik			SpielLogik
	 */
	public SpielerbankPresenter(Container eimer, List<SpielerLogik> spielerListe, SpielLogik logik) {
		this.spielerListe = spielerListe;		
		this.logik = logik;
		this.gui = new SpielerbankGUI(eimer, this);		
	}
	
	/**
	 * Index des aktiven Spielers 
	 * 
	 * @return	Index des aktiven Spielers
	 */
	public int getAktiverSpieler() {
		return logik.getAktiverSpielerIndex();
	}
	
	/**
	 * Namen der SpielerIcons anhand des Indexes
	 * 
	 * @param i		Index des Spielser in der Liste
	 * @return		Name des Icons fuer Darstellung in GUI
	 */
	public String getBildname(int i) {
		switch(spielerListe.get(i).getIconIndex()) {
		case 0:
			return "bebi";					
		case 1:
			return "kiki";
		case 2:
			return "bubu";
		case 3:
			return "crystal";
		case 4:
			return "schrinke";
		case 5:
			return "transistor";
		case 6:
			return "integral";
		case 7: 
			return "fey";
		default: return "spielerbank";
		}
	}

	/**
	 * aktuallisieren von Spieler Icon in GUI
	 * 
	 * @param spielerIndex 	Index des Spieler dessen Icon aktualisiert wird
	 */
	public void aktualisiereAktivesSpielerIcon(int spielerIndex) {
		gui.aktualisiereIcon(spielerIndex);
	}
		

	public SpielerbankGUI getGui() {
		return gui;
	}

	public void setGui(SpielerbankGUI gui) {
		this.gui = gui;
	}
	
	public List<SpielerLogik> getSpielerListe() {
		return spielerListe;
	}

	public void setSpielerListe(List<SpielerLogik> spielerListe) {
		this.spielerListe = spielerListe;
	}
	
	public SpielLogik getLogik() {
		return logik;
	}
	
	public void setLogik(SpielLogik logik) {
		this.logik = logik;
	}
}
