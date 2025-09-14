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
	
	public Kategorie getAusgewaehlteKategorie() {
		return ausgewaehlteKategorie;
	}
	
	public boolean istPunktEndgueltig(Kategorie kategorie, int spielerIndex) {
		if (kategorie == null) return false;
		return model.istPunktEndgueltig(kategorie, spielerIndex);
	}
	
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
	
	public boolean istSpielBeendet() {
		return model.istSpielBeendet();
	}
	
	public boolean istNullwurfMoeglich() {
		return spielLogik.getWuerfeAnzahl() >= 3;
	}
	
	public int getAktiverSpielerIndex() {
		return spielLogik.getAktiverSpielerIndex();
	}
		
	private void updateView() {
		Object[][] tableData = extractTableData();
		String[]columnNames = createColumnNames();
		
		view.updateTableData(tableData, columnNames);
	}
	
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
	
	@Override
	
	public void wennWuerfelGeworfenWerden(List<Integer>wuerfel) {
		moeglicheZuege.setWuerfelWerte(wuerfel);
		Map<Kategorie, Integer> punkte = moeglicheZuege.getAktiveMoeglichePunkte();
		zeigeMoeglichePunkte(punkte, spielLogik.getAktiverSpielerIndex());
		updateView();
	}
	
	@Override
	public void zeigeMoeglichePunkte(HashMap<Kategorie,Integer> hashmap) {
		if (hashmap != null) {
			model.setMoeglichePunkte(hashmap, spielLogik.getAktiverSpielerIndex());
			updateView();
		}
	}
	
	public void wennNeueRundeStartet(List<WuerfelLogik> alleWuerfelLogik) {
		model.clearMoeglichePunkte();
		updateView();
	}
	
	public PunkteTabelleGUI getView() {
		return view;
	}
	
	public PunktetabelleLogik getModel() {
		return model;
	}
}

