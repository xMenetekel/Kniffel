package presenter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;

import gui.*;
import gui.SpielfeldGUI;
import gui.WuerfelBankGUI;
import model.SpielfeldLogik;
import model.StartmenueLogik;
import model.WuerfelLogik;
import model.Kategorie;
import model.SpielLogik;

public class SpielfeldPresenter {
	 private SpielfeldGUI gui;
	 private SpielfeldLogik logik;
	 
	 private WuerfelPresenter wuerfel;	
	 private WuerfelbankPresenter bank;
	 private PunktetabellePresenter tabelle;
	 private SpielerbankPresenter spieler;
	 private WuerfelflaechePresenter flaeche;
	 private WuerfelnButtonPresenter wuerfelnKlick;
	 private PunkteEintragenButtonPresenter punkteKlick;
	 
	 private Kategorie ausgewaehlteKategorie = null; 
	 private JLabel statusLabel;	
	 private SpielLogik spielLogik; 
	 private MoeglicheZuege moeglicheZuege = new MoeglicheZuege();
	 private StartmenueLogik menueDaten;
	 
	

	public SpielfeldPresenter(Container Eimer, SpielLogik spielLogik, StartmenueLogik menueDaten) {
		 
		 this.spielLogik = spielLogik; 
		 this.menueDaten = menueDaten;
		 
		 gui = new SpielfeldGUI(Eimer, this, menueDaten);
		 logik = new SpielfeldLogik();

		 flaeche = new WuerfelflaechePresenter(Eimer);
		 bank = new WuerfelbankPresenter(Eimer);
		 tabelle = new PunktetabellePresenter(Eimer, menueDaten, spielLogik); 	
		 wuerfel = new WuerfelPresenter(Eimer, spielLogik, flaeche.getGui(), bank.getGui(), tabelle);  			 	 
		 spieler = new SpielerbankPresenter(Eimer);
		
		 wuerfelnKlick = new WuerfelnButtonPresenter(Eimer, wuerfel, tabelle);
		 punkteKlick = new PunkteEintragenButtonPresenter(Eimer, this);


		 

		 wuerfelnKlick.getGui().getKnopf().addActionListener(e->onWuerfelnClick());
		 punkteKlick.getGui().getKnopf().addActionListener(e->eintragePunkteFuerAktuellenSpieler());

		 
		 if (tabelle.getGui() != null) {
			 tabelle.getGui().setKategorieSelectListener(this::onKategorieAusgewaehlt);
		 }
		
		 Eimer.add(flaeche.getGui().getPanel());
		 Eimer.add(bank.getGui().getPanel());
		 Eimer.add(tabelle.getGui().getPanel());
		 Eimer.add(spieler.getGui().getPanel());
		 Eimer.add(wuerfelnKlick.getGui().getPanel());
		 Eimer.add(punkteKlick.getGui().getPanel());
		 Eimer.add(gui.getPanel());
		 
		 rearrangiereAlles(Eimer);
		 highlightAktuelleSpalte();
		
		 statusLabel = new JLabel();
			statusLabel.setBounds((int)(Eimer.getWidth() * 0.1), (int)(Eimer.getHeight() * 0.05), (int)(Eimer.getWidth() * 0.3),30);
			//font entfernt, hat es auf meinem rechner nicht richtig aufgeschlüsselt, sorry anthony
			updateStatusLabel();
			
			if (spielLogik != null) {
				updateStatusLabel();
			}else {
				statusLabel.setText("Spiel wird initialisiert...");
			}
			Eimer.add(statusLabel); 
	}
	 
	 public void onWuerfelnClick() {
	{
	            tabelle.aktualisierePunkteTabelle();   //das müsste eigentlich nach jedem würfeln immer passieren, maybe consider removing the if?
		 }
		 if(spielLogik.getWuerfeAnzahl() < 3 && wuerfel.getWurfAnzahl() < wuerfel.getMaxWuerfe()) {
			 
			 wuerfel.wuerfeln();
			 spielLogik.erhoeheWurfAnzahl();
			 updateStatusLabel();
			 
			 List<Integer> wuerfelWerte = new ArrayList<>();
			 for (WuerfelLogik wuerfelLogik : wuerfel.getAlleWuerfelLogik()) {
				 wuerfelWerte.add(wuerfelLogik.getAugen());
			 }
			 System.out.println("Neue Würfelwerte: "+wuerfelWerte);
			 
			 moeglicheZuege.setWuerfelWerte(wuerfelWerte);
			 Map<Kategorie,Integer> moeglichePunkte = moeglicheZuege.getAktiveMoeglichePunkte();
			 tabelle.getGui().zeigeMoeglichePunkte(moeglichePunkte);
			 System.out.println("Wuerfelwerte nach Wurf: "+ wuerfelWerte);
		 }
	 }
	 
	 public void onKategorieAusgewaehlt(Kategorie kategorie) {
		 this.ausgewaehlteKategorie = kategorie;
		 System.out.println("Kategorie Ausgewählt: "+kategorie);
	 }
	 
	 

	 public void rearrangiereAlles(Component fenstar) {
		 rearrangiere(fenstar, flaeche.getGui());
		 rearrangiere(fenstar, bank.getGui());
		 rearrangiere(fenstar, tabelle.getGui());
		 rearrangiere(fenstar, spieler.getGui());		 
		 rearrangiere(fenstar, wuerfelnKlick.getGui());
		 rearrangiere(fenstar, punkteKlick.getGui());
		 rearrangiereWuerfel(fenstar, wuerfel, bank.getGui());
		 
	 }
	 
	 
	 
	 protected void rearrangiereWuerfel(Component e, WuerfelPresenter w, WuerfelBankGUI bank) {
			int breite = e.getWidth();
			int hoehe = e.getHeight();
			
			w.getAlleWuerfelGUI().forEach(t -> { t.setxPos( (int) Math.round(t.prozentXAufFeld * breite)  );
												 t.setyPos( (int) Math.round(t.prozentYAufFeld * hoehe));
												 t.setProzentXAufFeld(t.getxPos() *1.0 / breite);
												 t.setProzentYAufFeld(t.getyPos() *1.0 / hoehe);
												 t.getButton().setBounds(t.getxPos(), t.getyPos(), 50, 50);
												 });
			bank.getPanel().setBounds((int)Math.floor(breite*bank.getProzentFlaecheX()), 
									  (int)Math.floor(hoehe*bank.getProzentFlaecheY()), 280, 60);
				
		}
		
		public void rearrangiere(Component e, AbstractGUI gui) {
			int breite = e.getWidth();
			int hoehe = e.getHeight();
			
			gui.getPanel().setBounds((int)Math.floor(breite*gui.getProzentFlaecheX()), 
									(int)Math.floor(hoehe*gui.getProzentFlaecheY()), 
									(int)Math.floor(breite*gui.getProzentFlaecheBreite()),
									(int)Math.floor(hoehe*gui.getProzentFlaecheHoehe()));
			
			if(!(gui instanceof PunkteTabelleGUI)){
			gui.setzeneueGroesse((int)(breite * gui.getProzentFlaecheBreite()), (int)(hoehe * gui.getProzentFlaecheHoehe()));
			}
		}
		
		private void highlightAktuelleSpalte() {
			if(tabelle != null && tabelle.getGui() != null) {
				tabelle.getGui().highlightSpalte(spielLogik.getAktiverSpielerIndex());
			}
		}
	 

		private void updateStatusLabel() {
			String status = "Runde " + spielLogik.getRunde() + 
					"| Spieler: " + spielLogik.getAktiverSpieler().getName() + 
					"| Würfe: " + spielLogik.getWuerfeAnzahl() + "/3";
			statusLabel.setText(status);
		}
		
		public void eintragePunkteFuerAktuellenSpieler() {
			 System.out.println("==PUNKTE EINTRAGEN==");
			 System.out.println("AusgewählteKategorie: "+ausgewaehlteKategorie);
			 System.out.println("Wuerfeanzahl: "+ spielLogik.getWuerfeAnzahl());
			 System.out.println("Aktiver Spieler: "+spielLogik.getAktiverSpielerIndex());
			 
			 if(ausgewaehlteKategorie != null && spielLogik.getWuerfeAnzahl()>0) {
				 try {
				 List<Integer>wuerfelWerte = new ArrayList<>();
				 for (WuerfelLogik wuerfelLogik : wuerfel.getAlleWuerfelLogik()) {
					 wuerfelWerte.add(wuerfelLogik.getAugen());
				 }
				 int punkte = moeglicheZuege.berechnePunkte(ausgewaehlteKategorie, wuerfelWerte);
				 System.out.println("Berechnete Punkte für "+ausgewaehlteKategorie + " : " + punkte	);
				 tabelle.getGui().tragePunkteEin(ausgewaehlteKategorie, punkte, spielLogik.getAktiverSpielerIndex());
				 
				 spielLogik.setWuerfeAnzahl(0);
				 
				 spielLogik.naechsterSpieler();
				 wuerfel.neueRunde();
				 ausgewaehlteKategorie = null;
				 updateStatusLabel();
				 highlightAktuelleSpalte();
				 tabelle.getGui().zeigeMoeglichePunkte(new HashMap<>());
				 System.out.println("Punkte erfolgreich eingetragen!");
				 
				 }catch (Exception e) {
					 System.out.println("Fehler beim Punkteeintragen: " + e.getMessage());
					 e.printStackTrace();
				 }
			 }else {
				 System.out.println("Kann keine Punkte eintragen -" + (ausgewaehlteKategorie == null ? "Keine Kategorie ausgewaehlt" : "noch nicht gewürfelt"));
			 }
		 }
		
		
		
	
	 
	//Getting and Setting  
	 
	 public SpielfeldGUI getGui() {
		return gui;
	}

	public void setGui(SpielfeldGUI gui) {
		this.gui = gui;
	}

	public SpielfeldLogik getLogik() {
		return logik;
	}

	public void setLogik(SpielfeldLogik logik) {
		this.logik = logik;
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
