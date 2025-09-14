package presenter;

import java.awt.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.PunkteTabelleGUI;

import model.Kategorie;
import model.PunktetabelleLogik;
import model.SpielLogik;
import model.StartmenueLogik;
import model.WuerfelLogik;
/**
*Vermittelt zwischen GUI und Logik der Punktetabelle, verwaltet Benutzerinteraktionen
*/
public class PunktetabellePresenter implements SpielEreignisListener{
	
	private PunkteTabelleGUI view;
	private PunktetabelleLogik model;
	private MoeglicheZuege moeglicheZuege;
	private SpielLogik spielLogik;
	private StartmenueLogik menueDaten;
	private Kategorie ausgewaehlteKategorie = null;
	
	public PunktetabellePresenter(Container eimer, StartmenueLogik menueDaten,SpielLogik spielLogik) {
		this.menueDaten = menueDaten;
		this.spielLogik = spielLogik;
		this.view = new PunkteTabelleGUI(eimer, menueDaten);
		this.model = new PunktetabelleLogik(menueDaten.getSpieleranzahl(), spielLogik.getAlleSpieler());
		this.moeglicheZuege = new MoeglicheZuege();	
		
		view.setPresenter(this);
		updateView();
	}
	
	/**
	 * Behandelt die Auswahl einer Kategorie durch den Benutzer
	 * Prüft Gültigkeit und aktualisiert die Anzeige
	 * 
	 * @param kategorie: die ausgewählte Kategorie
	 */
	public void onKategorieSelected(Kategorie kategorie) {
		int aktuellerSpieler = spielLogik.getAktiverSpielerIndex();
		
		boolean feldIstLeer = model.istFeldLeer(kategorie,aktuellerSpieler);
		boolean hatMoeglichePunkte = model.kannKategorieEingetragenWerden(kategorie,aktuellerSpieler);
		boolean dreiMalGewuerfelt = (spielLogik.getWuerfeAnzahl() >= 3);
		boolean istAktiverSpieler = (aktuellerSpieler == model.getAktuellerSpieler());
		
		if(this.ausgewaehlteKategorie == kategorie) {
			this.ausgewaehlteKategorie = null;
			System.out.println("Auswahl von " + kategorie + "aufgehoben");
			updateView();
			return;
		}
		
		if(feldIstLeer || (hatMoeglichePunkte && (dreiMalGewuerfelt && istAktiverSpieler))) {
			this.ausgewaehlteKategorie = kategorie;
			System.out.println("Kategorie " + kategorie + "ausgewählt");
			if(hatMoeglichePunkte) {
				Integer punkte = model.getMoeglichePunkteFuerKategorie(kategorie);
				System.out.println("MöglichePunkte: "+ punkte);
			} else if (!feldIstLeer) {
				System.out.println("Feld bereit belegt - kann nicht ausgewählt werden");
			} else {
				System.out.println("Kategorie kann nicht ausgewählt werden");
			}
			updateView();
		}
	}
	
	/**
	 *Trägt die Punkte für die gewählte Kategorie ein
	 *Berechnet Punkte basierend auf Würfelwerten
	 *
	 *@param spielerIndex: Index des aktuellen Spielers
	 *@param wuerfelWerte: Liste der aktuellen Würfelwerte
	 *@return true wenn Eintrag erfolgreich, false bei Fehlern
	 */
	public boolean gewaehltePunkteEin(int spielerIndex, List<Integer>wuerfelWerte) {
		if(ausgewaehlteKategorie == null){
			System.out.println("keine Kategorie ausgewählt!");
			return false;
		}
		
		boolean hatMoeglichePunkte = model.hatMoeglichePunkteFuerKategorie(ausgewaehlteKategorie);
		boolean dreiMalGewuerfelt = (spielLogik.getWuerfeAnzahl() >= 3);
		
		if(!hatMoeglichePunkte && !dreiMalGewuerfelt) {
			System.out.println("Null-Eintrag erst nach 3 Würfen möglich!");
			return false;
		}
		
		int punkte = 0;
		if(model.hatMoeglichePunkteFuerKategorie(ausgewaehlteKategorie)) {
			punkte = moeglicheZuege.berechnePunkte(ausgewaehlteKategorie,wuerfelWerte);
		}
		boolean erfolg = model.tragePunkteEin(ausgewaehlteKategorie, punkte, spielerIndex);
		
		if(erfolg) {
			System.out.println("punkte eingetragen: " + ausgewaehlteKategorie + " = " + punkte);
			ausgewaehlteKategorie = null;
			updateView();
			return true;
		}
		return false;
	}
	
	/**
	 *Liefert aktuell ausgewählte Kategorie
	 *
	 *@return ausgewählte Kategorie oder null wenn keine Auswahl
	 */
	public Kategorie getAusgewaehlteKategorie() {
		return ausgewaehlteKategorie;
	}
	
	/**
	 *Prüft, ob ein Punkt endgültig eingetragen ist
	 *
	 *@param kategorie zu prüfende Kategorie
	 *@param spielerIndex: Index des Spielers
	 *@return true, wenn Punkt endgültig, false wenn änderbar
	 */
	public boolean istPunktEndgueltig(Kategorie kategorie, int spielerIndex) {
		if (kategorie == null) return false;
		return model.istPunktEndgueltig(kategorie, spielerIndex);
	}
	
	/**
	 *Zeigt mögliche Punkte für den aktuellen Spieler an
	 *Aktualisiert die Tabellenanzeige entsprechend
	 *
	 *@param moeglichePunkte Map mit Kategorie-Punkte-Zuordnung
	 *@param spielerIndex des aktuellen Spielers
	 */
	public void zeigeMoeglichePunkte(Map<Kategorie,Integer> moeglichePunkte,int spielerIndex) {
		if(moeglichePunkte == null || moeglichePunkte.isEmpty()) {
			System.out.println("Keine möglichen Punkte zum Anzeigen!");
			model.clearMoeglichePunkte();
			updateView();
			return;
		}
		
		model.setMoeglichePunkte(moeglichePunkte,spielerIndex);
		System.out.println("Mögliche Punkte angezeigt: " + moeglichePunkte);
		updateView();
	}
	
	/**
	 * Prüft, ob das spiel beendet ist
	 * 
	 * @return true wenn alle Spieler fertig sind
	 */
	public boolean istSpielBeendet() {
		return model.istSpielBeendet();
	}
	
	/**
	 *Prüft, ob ein Nullwurf möglich ist
	 *Nullwurf ist ert nach dem dritten wurf erlaubt
	 *
	 *@return true wen Nullwurf möglich
	 */
	public boolean istNullwurfMoeglich() {
		return spielLogik.getWuerfeAnzahl() >= 3;
	}
	
	/**
	 *Liefert den Index des aktiven Spielers
	 *
	 *@return Index des aktuellen Spielers
	 */
	public int getAktiverSpielerIndex() {
		return spielLogik.getAktiverSpielerIndex();
	}
	
	/**
	 * Aktualisiert die View mit anderen Daten
	 * Extrahiert Daten aus Model und übergibt sie an die GUI
	 */
	private void updateView() {
		Object[][] tableData = extractTableData();
		String[]columnNames = createColumnNames();
		
		view.updateTableData(tableData, columnNames);
	}
	
	/**
	 * Prüft, ob ein tabellenfeld ein berechnete Feld ist
	 * Berechnete felder können nicht direkt bearbeitet werden
	 * 
	 * @param row: zeilenIndex, der zu prüfen ist
	 * @return true, wenn Feld automatisch berechnet wird
	 */
	public boolean istBerechnetesFeld(int row) {
		Kategorie kategorie = Kategorie.fromTabellenIndex(row);
		return kategorie == Kategorie.GESAMT_OBEN||
				kategorie == Kategorie.BONUS||
				kategorie == Kategorie.GESAMT_OBEN_BONUS||
				kategorie == Kategorie.GESAMT_UNTEN||
				kategorie == Kategorie.GESAMT_OBEN_ZWEI||
				kategorie == Kategorie.ENDSUMME||
				kategorie == null;
	}
	
	/**
	 * Extrahiert die aktuellen Tabellendaten aus dem Model
	 * 
	 * @return 2D Array mit allen Tabellendaten
	 */
	private Object [][] extractTableData(){
		int rows = model.getRowCount();
		int cols = model.getColumnCount();
		Object[][] data = new Object[rows][cols];
		
		for(int row = 0; row <rows; row++) {
			for(int col = 0; col<cols; col++) {
				data[row][col] = model.getValueAt(row,col);
			}
		}
		return data;
	}
	
	/**
	 *Erstellt die Spaltennamen für die Tabelle
	 *Verwendet Spielernamen aus den Menüdaten
	 *
	 *@return Array mit Spaltennamen
	 */
	private String [] createColumnNames() {
		int spielerAnzahl = model.getSpielerAnzahl();
		String[] columnNames = new String[2+spielerAnzahl];
		columnNames[0] = "Kategorie";
		columnNames[1] = "Kombinationen";
		
		for(int i = 0; i<spielerAnzahl; i++) {
			String spielerName = menueDaten.getSpieler(i).getName();
			columnNames [2+i] = spielerName;
		}
		return columnNames;
	}
	
	/**
	 * Callback wenn würfel geworfen werden 
	 * Berechnet mögliche Punkte und aktualisiert Anzeige
	 * 
	 * @param wuerfel_ Liste der gewürfelten werte
	 */
	@Override
	public void wennWuerfelGeworfenWerden(List<Integer>wuerfel) {
		moeglicheZuege.setWuerfelWerte(wuerfel);
		Map<Kategorie, Integer> punkte = moeglicheZuege.getAktiveMoeglichePunkte();
		zeigeMoeglichePunkte(punkte, spielLogik.getAktiverSpielerIndex());
		updateView();
	}
	
	/**
	 *Alternative Callback-Methode für mögliche Punkte
	 *
	 *@param hashmap: Hashmap mit möglichen Punkten
	 */
	@Override
	public void zeigeMoeglichePunkte(HashMap<Kategorie,Integer> hashmap) {
		if (hashmap != null) {
			model.setMoeglichePunkte(hashmap, spielLogik.getAktiverSpielerIndex());
			updateView();
		}
	}
	
	/**
	 * Callback, wenn eine neue Runde startet
	 * Löscht mögliche Punkte und aktualisiert anzeige
	 * 
	 * @param alleWuerfelLogik Liste aller Würfel-Logik-Objekte
	 */
	public void wennNeueRundeStartet(List<WuerfelLogik> alleWuerfelLogik) {
		model.clearMoeglichePunkte();
		updateView();
	}
	
	/**
	 * Liefert die View.Instanz
	 * 
	 * @return die verwaltete GUI instanz
	 */
	public PunkteTabelleGUI getView() {
		return view;
	}
	
	/**Liefert die Model instanz
	 * 
	 * @return die verwaltete Logik Instanz
	 *
	 */
	public PunktetabelleLogik getModel() {
		return model;
	}
}

