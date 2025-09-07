package presenter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTable;

import gui.AbstractGUI;
import gui.SpielfeldGUI;
import gui.WuerfelBankGUI;
import model.SpielfeldLogik;
import model.StartmenueLogik;
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
	 
	 public SpielfeldPresenter(Container Eimer, StartmenueLogik menueDaten, SpielLogik spielLogik) {
		 
		 this.spielLogik = spielLogik; 
		 
		 gui = new SpielfeldGUI(Eimer, this);
		 logik = new SpielfeldLogik();
		 
		 flaeche = new WuerfelflaechePresenter();
		 bank = new WuerfelbankPresenter();
		 tabelle = new PunktetabellePresenter(menueDaten, spielLogik); 	
		 wuerfel = new WuerfelPresenter(Eimer, spielLogik, flaeche.getGui(), bank.getGui(), tabelle);  			 	 
		 spieler = new SpielerbankPresenter();
		
		 wuerfelnKlick = new WuerfelnButtonPresenter(wuerfel, tabelle);
		 punkteKlick = new PunkteEintragenButtonPresenter(this);
		 
		
		 Eimer.add(flaeche.getGui().getPanel());
		 Eimer.add(bank.getGui().getPanel());
		 Eimer.add(tabelle.getGui().getPanel());
		 Eimer.add(spieler.getGui().getPanel());
		 Eimer.add(wuerfelnKlick.getGui().getPanel());
		 Eimer.add(punkteKlick.getGui().getPanel());
		 Eimer.add(gui.getPanel());
		 
		 rearrangiereAlles(Eimer);
		
		 
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
			
		}
		
	 
	 

		private void updateStatusLabel() {
			String status = "Runde " + spielLogik.getRunde() + 
					"| Spieler: " + spielLogik.getAktiverSpieler().getName() + 
					"| Würfe: " + spielLogik.getWuerfeAnzahl() + "/3";
			statusLabel.setText(status);
		}
		
		public void eintragePunkteFuerAktuellenSpieler() {
			if(ausgewaehlteKategorie != null) {
				try {
					int punkte = wuerfel.getMoeglicheZuege().berechnePunkte(ausgewaehlteKategorie);
					int aktiverSpielerIndex = spielLogik.getAktiverSpielerIndex();
					tabelle.getGui().tragePunkteEin(ausgewaehlteKategorie, punkte, aktiverSpielerIndex);
					wuerfel.getMoeglicheZuege().entferneKategorie(ausgewaehlteKategorie);
					tabelle.getGui().zeigeMoeglichePunkte(new HashMap<>());
					ausgewaehlteKategorie = null;
					JTable table = tabelle.getGui().getTable();
					if(table != null) {
						table.clearSelection();
					}
					spielLogik.naechsterSpieler();
					tabelle.getGui().zeigeMoeglichePunkte(new HashMap<>());
					if(tabelle.getGui().getTable() != null) {
						tabelle.getGui().getTable().repaint();
					}
					tabelle.wasPassiertWennWirEineNeueRundeAusloesen(wuerfel.getAlleWuerfelLogik());
					
					updateStatusLabel();
					System.out.println("Spieler " + (spielLogik.getAktiverSpielerIndex() + 1)+ "ist jetzt an der Reihe. Runde: " + spielLogik.getRunde());
					
				}catch(Exception e) {
					System.out.println("Fehler beim Eintragen der Punkte" + e.getMessage());
					e.printStackTrace();
				}
			} else {
				System.out.println("Keine Kategorie ausgewählt!");
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

}
