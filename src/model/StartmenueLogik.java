package model;

import java.util.ArrayList;
import java.util.List;

public class StartmenueLogik {
	
	private boolean spielGestartet = false;
	private boolean einstellungenOffen = false;
	private boolean debugingModus = false;
	
	private int spieleranzahl;
	private List<SpielerLogik> spielerListe = new ArrayList<>();

	private boolean endloseWuerfe = false;	
	
	private void aktualisiereSpielerListe() {
		spielerListe.clear();
		for(int i = 0; i < spieleranzahl; i++) {
			SpielerLogik spieler = SpielerFactory.createSpieler("ErsterSpieler");
			spieler.setName("Spieler" + (i + 1));
			spielerListe.add(spieler);
		}
	}

	public void setSpielerIcon(int index, int iconIndex) {
		spielerListe.get(index).setIconIndex(iconIndex);
	}
	
	public SpielerLogik getSpieler(int index) {
		return spielerListe.get(index);
	}
	
	public void setSpielerName(int index, String name) {
		spielerListe.get(index).setName(name);
	}
	
	//Getter und Setter
	public int getSpieleranzahl() {
		return spieleranzahl;
	}

	public void setSpieleranzahl(int spieleranzahl) {
		this.spieleranzahl = spieleranzahl;
		aktualisiereSpielerListe();
	}
		
	public List<SpielerLogik> getSpielerListe(){
		return spielerListe;
	}
	
	public boolean isSpielGestartet() {
		return spielGestartet;
	}

	public void setSpielGestartet(boolean spielGestartet) {
		this.spielGestartet = spielGestartet;
	}

	public boolean isEinstellungenOffen() {
		return einstellungenOffen;
	}

	public void setEinstellungenOffen(boolean einstellungenOffen) {
		this.einstellungenOffen = einstellungenOffen;
	}

	public boolean isDebugingModus() {
		return debugingModus;
	}
	
	public void setDebugingModus(boolean debugMode) {
		this.debugingModus = debugMode;
	}

	public boolean isEndloseWuerfe() {
		return endloseWuerfe;
	}

	public void setEndloseWuerfe(boolean endloseWuerfe) {
		this.endloseWuerfe = endloseWuerfe;
	}
	
}
