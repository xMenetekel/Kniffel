package presenter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.JTextArea;

import gui.SpielfeldGUI;
import model.Kategorie;
import model.SpielLogik;
import model.StartmenueLogik;
import model.WuerfelLogik;

public class SpielfeldPresenter {
	public SpielfeldGUI gui;
	 
	public WuerfelPresenter wuerfel;	
	public WuerfelbankPresenter bank;
	public PunktetabellePresenter tabelle;
	public SpielerbankPresenter spieler;
	public WuerfelflaechePresenter flaeche;
	public WuerfelnButtonPresenter wuerfelnKlick;
	public PunkteEintragenButtonPresenter punkteKlick;
	 
	private Kategorie ausgewaehlteKategorie = null; 
	public JTextArea statusLabel;	
	private SpielLogik spielLogik; 
	private MoeglicheZuege moeglicheZuege = new MoeglicheZuege();
	private StartmenueLogik menueDaten;
	private Container eimer;
	
	/**
	 * Steuert das Spielfeld und koordiniert die Kommunikation zwischen GUI
	 * Logik und einzelnen Presentern 
	 * 
	 * @param eimer			uebergeordneter Container, werden alle GUI Komponenenten des Spielfelds plaziert
	 * @param spielLogik	SpielLogik verwaltet den akutellen Zustand des Spielers
	 * @param menueDaten	Logik des Startmenues 
	 */
	public SpielfeldPresenter(Container eimer, SpielLogik spielLogik, StartmenueLogik menueDaten) {
		
		 this.menueDaten = menueDaten;
		 this.spielLogik = spielLogik; 
		 
		 this.eimer = eimer;
		 
		 gui = new SpielfeldGUI(eimer, this);	 
		 
		 flaeche = new WuerfelflaechePresenter(eimer);
		 bank = new WuerfelbankPresenter(eimer);
		 tabelle = new PunktetabellePresenter(eimer, menueDaten, spielLogik); 	
		 wuerfel = new WuerfelPresenter(eimer, spielLogik, flaeche.getGui(), bank.getGui(), tabelle, menueDaten);  			 	 
		 spieler = new SpielerbankPresenter(eimer, menueDaten.getSpielerListe(), spielLogik);
	
		 wuerfelnKlick = new WuerfelnButtonPresenter(eimer, wuerfel, tabelle);
		 punkteKlick = new PunkteEintragenButtonPresenter(eimer, this);

		 wuerfelnKlick.getGui().getKnopf().addActionListener(e->onWuerfelnClick());
		 punkteKlick.getGui().getKnopf().addActionListener(e->eintragePunkteFuerAktuellenSpieler());
	 	 
		 statusLabel = new JTextArea();
		 setupStatusLabel();
				
		 eimer.add(statusLabel);
		 eimer.add(flaeche.getGui().getPanel());
		 eimer.add(bank.getGui().getPanel());
		 eimer.add(tabelle.getView().getPanel());
		 eimer.add(spieler.getGui().getPanel());
		 eimer.add(wuerfelnKlick.getGui().getPanel());
		 eimer.add(punkteKlick.getGui().getPanel());
		 eimer.add(gui.getPanel());
		 
		 rearrangiereAlles(eimer);
		 highlightAktuelleSpalte();
		
		eimer.revalidate();
		eimer.repaint();
	}
	 /**
	  * Initialisiert das Statuslabel (Sollte besser zu SpielerGUI)
	  */
	 private void setupStatusLabel() {
		statusLabel.setVisible(true);
		statusLabel.setEditable(false);
		statusLabel.setOpaque(false);
		statusLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		updateStatusLabel();
	}

	/**
	 * macht einen Wurf bei klick, aktualisiert die Wuerfelwerte,
	 * berechnet moegliche Punkte
	 * und aktualisiert die Tabelle
	 */
	public void onWuerfelnClick() { 
	     int aktuellesMaxWuerfeln = wuerfel.getMaxWuerfe();
	     
		 if(spielLogik.getWuerfeAnzahl() >= aktuellesMaxWuerfeln || wuerfel.getWurfAnzahl() >= aktuellesMaxWuerfeln) {
			 return;
		}
		
		 if(wuerfel.isInit()) {
			 
			 wuerfel.setInit(false);
			 resetBank();
		 }
		 
		wuerfel.wuerfeln();
		spielLogik.erhoeheWurfAnzahl();
		updateStatusLabel();
		
		List<Integer> wuerfelWerte = ermittleWuerfelWerte();
		System.out.println("Neue Würfelwerte: "+ wuerfelWerte);
			 

		 moeglicheZuege.setWuerfelWerte(wuerfelWerte);
		 Map<Kategorie,Integer> moeglichePunkte = moeglicheZuege.getAktiveMoeglichePunkte();
		 tabelle.zeigeMoeglichePunkte(moeglichePunkte, spielLogik.getAktiverSpielerIndex());
		 System.out.println("Wuerfelwerte nach Wurf: "+ wuerfelWerte);
		 
		 if(spielLogik.getWuerfeAnzahl() >= aktuellesMaxWuerfeln) {
			 System.out.println("Maximale Würfe erreicht, bitte Punkte eintragen");
		 }
	 }
 
	/**
	 * Resettet bei einer neuen Runde die Logik, mit der die Bank befüllt wird und immer den ersten freien Platz nimmt
	 */
	private void resetBank() {
		for(int i = 0; i < 5; i++) {
			bank.getGui().getReihenfolge().set(i, null);
		}
	}
	
	/**
	 * Updated was passiert wenn man eine Kategorie für die Punkte auswählt
	 * @param kategorie
	 */
	public void onKategorieAusgewaehlt(Kategorie kategorie) {
		 this.ausgewaehlteKategorie = kategorie;
		 System.out.println("Kategorie Ausgewählt: "+kategorie);
		 tabelle.onKategorieSelected(kategorie);
	 }
	 /**
	  * 
	  * @param fenstar
	  */
	 public void rearrangiereAlles(Component fenstar) {
		 gui.rearrangiere(fenstar, flaeche.getGui());
		 gui.rearrangiere(fenstar, tabelle.getView());
		 gui.rearrangiere(fenstar, spieler.getGui());		 
		 gui.rearrangiere(fenstar, wuerfelnKlick.getGui());
		 gui.rearrangiere(fenstar, punkteKlick.getGui());
		 gui.rearrangiere(fenstar, gui);
		 gui.rearrangiereWuerfel(fenstar, wuerfel, bank.getGui());		
		 statusLabel.setBounds((int)(eimer.getWidth() * 0.28), (int)(eimer.getHeight() * 0.81), (int)(eimer.getWidth() * 0.22),100);
	 }
	 	 

	/** 
	 * updated das statuslabel
	 */
	private void updateStatusLabel() {
		String maxWurf = String.valueOf(wuerfel.getMaxWuerfe());
		String status = "Runde " + spielLogik.getRunde() + 
						"\nSpieler: " + spielLogik.getAktiverSpieler().getName() + 
						"\nWürfe: " + wuerfel.getWurfAnzahl() + "/" + maxWurf;
		statusLabel.setText(status);
	}
	
	/**
	 * ermittelt aktuelle Augenwerte aller Wuerfe
	 * 
	 * @return	Liste der aktuellen Wuerfelwerte 
	 */
	private List<Integer> ermittleWuerfelWerte(){
		List<Integer> werte = new ArrayList<>();
		for(WuerfelLogik wuerfelLogik : wuerfel.getAlleWuerfelLogik()) {
			werte.add(wuerfelLogik.getAugen());
		}
		return werte;
	}

	public void eintragePunkteFuerAktuellenSpieler() {
		System.out.println("==PUNKTE EINTRAGEN BUTTON GEDRÜCKT==");
		
		if(spielLogik.getWuerfeAnzahl() == 0) {
			System.out.println("Kann keine Punkte eintragen - noch nicht gewürfelt!");
			return;
		}
		if(ausgewaehlteKategorie == null) {
			System.out.println("Kann keine Punkte eintragen - keine Kategorie ausgewählt!");
		}
		
		List<Integer> wuerfelWerte = new ArrayList<>();
		for(WuerfelLogik wuerfelLogik : wuerfel.getAlleWuerfelLogik()) {
			wuerfelWerte.add(wuerfelLogik.getAugen());
		}
		
		boolean erfolg = tabelle.gewaehltePunkteEin(spielLogik.getAktiverSpielerIndex(), wuerfelWerte);
		
		if(erfolg) {
			tabelle.getModel().clearMoeglichePunkte();
			spielLogik.setWuerfeAnzahl(0);
			
			if(tabelle.getModel().istSpielBeendet()) {
				System.out.println("Spiel beendet! Übergabe an EndePresenter...");
				spielBeendet();
				return;
			}
			spielLogik.naechsterSpieler();
			spieler.aktualisiereAktivesSpielerIcon(spielLogik.getAktiverSpielerIndex());
			wuerfel.neueRunde();
			updateStatusLabel();
			highlightAktuelleSpalte();
			
			tabelle.zeigeMoeglichePunkte(new HashMap<>(), spielLogik.getAktiverSpielerIndex());
			System.out.println("Punkte erfolgreich eingetragen!");
		}else {
			System.out.println("Punkte konnten nicht eingetragen werden - keine Kategorie ausgewählt!");
		}
	}
		
	public void highlightAktuelleSpalte() {
		if(tabelle!= null &&tabelle.getView() != null) {
			JTable table = tabelle.getView().getTable();
			int aktuelleSpielerSpalte = 2 + spielLogik.getAktiverSpielerIndex();
			System.out.println("Aktuelle Spalte für den Spieler" + spielLogik.getAktiverSpielerIndex() + " : " + aktuelleSpielerSpalte);
			table.repaint();
		}
	}
	
	public void spielBeendet() {
		new EndePresenter(eimer, spielLogik);
	}
	
	//Getter und Setter
		
	public SpielfeldGUI getGui() {
		return gui;
	}

	public void setGui(SpielfeldGUI gui) {
		this.gui = gui;
	}

	public WuerfelPresenter getWuerfel() {
		return wuerfel;
	}

	public void setWuerfel(WuerfelPresenter wuerfel) {
		this.wuerfel = wuerfel;
	}

	public WuerfelbankPresenter getBank() {
		return bank;
	}

	public void setBank(WuerfelbankPresenter bank) {
		this.bank = bank;
	}

	public PunktetabellePresenter getTabelle() {
		return tabelle;
	}

	public void setTabelle(PunktetabellePresenter tabelle) {
		this.tabelle = tabelle;
	}

	public SpielerbankPresenter getSpieler() {
		return spieler;
	}

	public void setSpieler(SpielerbankPresenter spieler) {
		this.spieler = spieler;
	}

	public WuerfelflaechePresenter getFlaeche() {
		return flaeche;
	}

	public void setFlaeche(WuerfelflaechePresenter flaeche) {
		this.flaeche = flaeche;
	}

	public WuerfelnButtonPresenter getWuerfelnKlick() {
		return wuerfelnKlick;
	}

	public void setWuerfelnKlick(WuerfelnButtonPresenter wuerfelnKlick) {
		this.wuerfelnKlick = wuerfelnKlick;
	}

	public PunkteEintragenButtonPresenter getPunkteKlick() {
		return punkteKlick;
	}

	public void setPunkteKlick(PunkteEintragenButtonPresenter punkteKlick) {
		this.punkteKlick = punkteKlick;
	}

	public void setSpielLogik(SpielLogik spielLogik) {
		this.spielLogik = spielLogik;
	}

	public SpielLogik getSpielLogik() {
		 return spielLogik;
	}
	
	public StartmenueLogik getMenueDaten() {
			return menueDaten;
	}
	
	public void setMenueDaten(StartmenueLogik menueDaten) {
		this.menueDaten = menueDaten;
	}

}
