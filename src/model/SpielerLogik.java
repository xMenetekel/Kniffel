package model;

public class SpielerLogik{
	private String name = "Spieler";
	private int punktestand = 0;
	private int reihenfolge = 0;
	private int iconIndex = 0;
	
	/**
	 * fügt punkte zum endpunktestand hinzu
	 * @param punkte
	 */
	public void addPunkte(int punkte) {
		this.punktestand += punkte;
	}
	
	//Getter und Setter
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPunktestand() {
		return punktestand;
	}
	public void setPunktestand(int punktestand) {
		this.punktestand = punktestand;
	}
	public int getReihenfolge() {
		return reihenfolge;
	}
	public void setReihenfolge(int reihenfolge) {
		this.reihenfolge = reihenfolge;
	}
	public int getIconIndex() {
		return iconIndex;
	}
	public void setIconIndex(int iconIndex) {
		this.iconIndex = iconIndex;
	}
	
}