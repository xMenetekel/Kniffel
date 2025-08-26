package model;

import java.awt.Color;

public class StartmenueLogik {
	
	private boolean spielGestartet = false;
	private boolean einstellungenOffen = false;
	
	private int spieleranzahl = 1;
	private String spielernamen = "Spieler";
	
	private Color hintergrundfarbe = Color.blue;
	
	
	public int getSpieleranzahl() {
		return spieleranzahl;
	}

	public void setSpieleranzahl(int spieleranzahl) {
		this.spieleranzahl = spieleranzahl;
	}

	public String getSpielernamen() {
		return spielernamen;
	}

	public void setSpielernamen(String spielernamen) {
		this.spielernamen = spielernamen;
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

	public Color getHintergrundfarbe() {
		return hintergrundfarbe;
	}

	public void setHintergrundfarbe(Color hintergrundfarbe) {
		this.hintergrundfarbe = hintergrundfarbe;
	}
	
}
