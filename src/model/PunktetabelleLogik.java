package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.awt.Point;


public class PunktetabelleLogik {
	
	private final int spielerAnzahl;
	private final List<SpielerLogik> alleSpieler;
	private Object [][] data;
	private Set<Point> festeEintraege = new HashSet<>();
	private Map<Kategorie,Integer> moeglichePunkte = new HashMap<>();
	private int aktuellerSpieler = -1;
	
	public PunktetabelleLogik(int spielerAnzahl, List<SpielerLogik> alleSpieler) {
		this.spielerAnzahl = spielerAnzahl;
		this.alleSpieler = alleSpieler;
		initializeTableData();
	}
	
	private void initializeTableData() {
		int columnCount = 2 + spielerAnzahl;
		data = new Object [19][columnCount];
		
		data[0][0] = Kategorie.EINSER;
		data[0][1] = "nur Einser zaehlen";
		data[1][0] = Kategorie.ZWEIER;
		data[1][1] = "nur Zweier zaehlen";
		data[2][0] = Kategorie.DREIER;
		data[2][1] = "nur Dreier zeahlen";
		data[3][0] = Kategorie.VIERER;
		data[3][1] = "nur Vierer zaehlen";
		data[4][0] = Kategorie.FUENFER;
		data[4][1] = "nur Fuenfer zaehlen";
		data[5][0] = Kategorie.SECHSER;
		data[5][1] = "nur Sechser zaehlen";
		data[6][0] = Kategorie.GESAMT_OBEN;
		data[6][1] = "--->";
		data[7][0] = Kategorie.BONUS;
		data[7][1] = "plus 35";
		data[8][0] = Kategorie.GESAMT_OBEN_BONUS;
		data[8][1] = "--->";
		data[9][0] = Kategorie.DREIER_PASCH;
		data[9][1] = "alle Augen zaehlen";
		data[10][0] = Kategorie.VIERER_PASCH;
		data[10][1] = "alle Augen zaehlen";
		data[11][0] = Kategorie.FULL_HOUSE;
		data[11][1] = "25 Punkte";
		data[12][0] = Kategorie.KLEINE_STRASSE;
		data[12][1] = "30 Punkte";
		data[13][0] = Kategorie.GROSSE_STRASSE;
		data[13][1] = "40 Punkte";
		data[14][0] = Kategorie.KNIFFEL;
		data[14][1] = "50 Punkte";
		data[15][0] = Kategorie.CHANCE;
		data[15][1] = "alle Augen zaehlen";
		data[16][0] = Kategorie.GESAMT_UNTEN;
		data[16][1] = "--->";
		data[17][0] = Kategorie.GESAMT_OBEN_ZWEI;
		data[17][1] = "--->";
		data[18][0] = Kategorie.ENDSUMME;
		data[18][1] = "--->";
		
	}
	
	public boolean tragePunkteEin(Kategorie kategorie, int punkte, int spielerIndex) {
		try {
			int row = kategorie.getTabellenIndex();
			int column = 2+spielerIndex;
			
			if(data[row][column] != null) {
				System.out.println("Feld bereit belegt für Kategorie " + kategorie + "bei Spieler " + spielerIndex);
				return false;
			}
			festeEintraege.add(new Point(row,column));
			data[row][column] = punkte;
			System.out.println("Punkte eingetragen: " + kategorie + " = " + punkte + "für Spieler " + spielerIndex);
			
			if (spielerIndex >= 0 && spielerIndex < alleSpieler.size()) {
				SpielerLogik spieler = alleSpieler.get(spielerIndex);
				spieler.addPunkte(punkte);
				System.out.println("Spieler "+ spieler.getName() + "hat jetzt" + spieler.getPunktestand() + "Punkte");
			}

			if(kategorie.ordinal() <= Kategorie.SECHSER.ordinal()) {
				berechneUndSetzeGesamtOben(spielerIndex);
			}
			if(kategorie.ordinal() >= Kategorie.DREIER_PASCH.ordinal() && kategorie.ordinal()<= Kategorie.CHANCE.ordinal()) {
				berechneUndSetzeGesamtUnten(spielerIndex);
			}
			return true;
		}catch(Exception e) {
			System.err.println("Fehler beim Eintragen der Punkte: "+ e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public void setMoeglichePunkte(Map<Kategorie,Integer>moeglichePunkte, int spielerIndex) {
		Map<Kategorie,Integer> gefiltertePunkte = new HashMap<>();
		
		for(Map.Entry<Kategorie,Integer>entry : moeglichePunkte.entrySet()) {
			Kategorie kategorie = entry.getKey();
			if(istFeldLeer(kategorie, spielerIndex)) {
				gefiltertePunkte.put(kategorie,entry.getValue());
			}
		}
		this.moeglichePunkte = gefiltertePunkte;
		this.aktuellerSpieler = spielerIndex;
		
		System.out.println("Mögliche Punkte gesetzt für Spieler " + spielerIndex + " : "+ gefiltertePunkte);
	}
	
	public void clearMoeglichePunkte() {
		this.moeglichePunkte.clear();
		this.aktuellerSpieler = -1;
		System.out.println("Moegliche Punkte gelöscht");
	}
	
	public boolean kannKategorieEingetragenWerden(Kategorie kategorie, int spielerIndex) {
		if(!istFeldLeer(kategorie,spielerIndex)) {
			return false;
		}
		if (spielerIndex == aktuellerSpieler){
			return moeglichePunkte.containsKey(kategorie);
		}
		return true;
	}
	
	public boolean istFeldLeer(Kategorie kategorie, int spielerIndex) {
		int row = kategorie.getTabellenIndex();
		int column = 2 + spielerIndex;
		return data[row][column] == null;
	}
	
	public boolean hatMoeglichePunkteFuerKategorie(Kategorie kategorie) {
		return moeglichePunkte.containsKey(kategorie);
	}
	
	public Integer getMoeglichePunkteFuerKategorie(Kategorie kategorie) {
		return moeglichePunkte.get(kategorie);
	}
	
	private void berechneUndSetzeGesamtOben(int spielerIndex) {
		int spielerSpalte = 2 + spielerIndex;
		int gesamtOben = 0;
		for (int row = 0; row <= 5; row++) {
			if(data[row][spielerSpalte] instanceof Integer) {
				gesamtOben += (Integer) data[row][spielerSpalte];
			}
		}
		data[6][spielerSpalte] = gesamtOben;
		
		if(istObereHaelfteVoll(spielerIndex)) {
			int bonus = (gesamtOben >= 63) ? 35 : 0;
			data[7][spielerSpalte] = bonus; 
			data[8][spielerSpalte] = gesamtOben + bonus;
			berechneEndsumme(spielerIndex);
		}else {
			data[7][spielerSpalte] = null;
			data[8][spielerSpalte] = null;
			berechneEndsumme(spielerIndex);
		}
	}
	
	private void berechneUndSetzeGesamtUnten(int spielerIndex) {
		int spielerSpalte = 2 + spielerIndex;
		int gesamtUnten = 0;
		
		for(int row = 9; row <= 15; row++) {
			if (data[row][spielerSpalte]instanceof Integer) {
				gesamtUnten +=(Integer) data[row][spielerSpalte];
			}
		}
		data[16][spielerSpalte] = gesamtUnten;
		data[17][spielerSpalte] = data[8][spielerSpalte];
		
		berechneEndsumme(spielerIndex);
	}
	
	private void berechneEndsumme(int spielerIndex) {
		int spielerSpalte = 2 + spielerIndex;
		Integer gesamtObenMitBonus = (data[8][spielerSpalte] instanceof Integer) ? (Integer)data[8][spielerSpalte] : null;
		Integer gesamtUnten = (data[16][spielerSpalte] instanceof Integer) ? (Integer)data[16][spielerSpalte] : null;
		
		if(gesamtObenMitBonus != null && gesamtUnten != null) {
			data[18][spielerSpalte] = gesamtObenMitBonus + gesamtUnten;
		}else {
			data[18][spielerSpalte] = null;
		}
	}
	
	public boolean istObereHaelfteVoll(int spielerIndex) {
		int spielerSpalte = 2 + spielerIndex;
		for(int row = 0; row <= 5; row++) {
			if(data[row][spielerSpalte] == null) {
				return false;
			}
		}
		return true;
	}

	public boolean istUntereHaelfteVoll(int spielerIndex) {
		int spielerSpalte = 2 + spielerIndex;
		for(int row = 9; row <= 15; row++) {
			if(data[row][spielerSpalte] == null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean istSpielBeendet() {
		for(int spielerIndex = 0; spielerIndex < spielerAnzahl;spielerIndex++) {
			if(!istObereHaelfteVoll(spielerIndex)|| !istUntereHaelfteVoll(spielerIndex)) {
				return false;
			}
		}
		return true;
	}
	
	public Object getValueAt(int row, int column) {
		if(row <0|| row >= data.length|| column < 0 || column >= data[0].length) {
			return null;
		}
		if(data[row][column]!= null) {
			return data[row][column];
		}
		if(column == 2 + aktuellerSpieler && !moeglichePunkte.isEmpty()) {
			Kategorie kategorie = Kategorie.fromTabellenIndex(row);
			if(kategorie != null && moeglichePunkte.containsKey(kategorie)) {
				return moeglichePunkte.get(kategorie);
			}
		}
		return null;
	}
	
	public boolean istPunktEndgueltig(Kategorie kategorie, int spielerIndex) {
		if(kategorie == null)return false;
		int row = kategorie.getTabellenIndex();
		int column = 2+ spielerIndex;
		Point point = new Point (row, column);
		return festeEintraege.contains(point);
	}
	
	public Object [][] getTableData(){
		Object[][] result = new Object[data.length][data[0].length];
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < data[0].length; col++) {
				result[row][col] = getValueAt(row,col);
			}
		}
		return result;
	}
	
	public int getRowCount() {
		return data.length;
	}
	
	
	public int getColumnCount() {
		return data[0].length;
	}
	
	public Set<Point> getFesteEintraege(){
		return new HashSet<>(festeEintraege);
	}
	
	public int getAktuellerSpieler() {
		return aktuellerSpieler;
	}
	
	public int getSpielerAnzahl() {
		return spielerAnzahl;
	}
}



