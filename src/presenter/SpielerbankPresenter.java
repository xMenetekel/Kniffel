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
	
	public SpielerbankPresenter(Container Eimer, List<SpielerLogik> spielerListe, SpielLogik logik) {
		this.spielerListe = spielerListe;		
		this.logik = logik;
		gui = new SpielerbankGUI(Eimer, this);		
	}
	public int getAktiverSpieler() { //lmao
		return logik.getAktiverSpielerIndex();
	}
	
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

	// aktuallisieren von Spieler Icon in GUI
	public void aktualisiereAktivesSpielerIcon(int spielerIndex) {
		gui.aktualisiereIcon(spielerIndex);
		
	}
		
	//Ready Get Set Go
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
