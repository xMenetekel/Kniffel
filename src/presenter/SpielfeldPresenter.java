package presenter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

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
	 private JTextArea statusLabel;	
	 private SpielLogik spielLogik; 
	 private MoeglicheZuege moeglicheZuege = new MoeglicheZuege();
	 private StartmenueLogik menueDaten;
	 private Container eimer;
	
	public SpielfeldPresenter(Container eimer, SpielLogik spielLogik, StartmenueLogik menueDaten) {
		 
		 this.menueDaten = menueDaten;
		 this.spielLogik = spielLogik; 
		 this.eimer = eimer;
		 
		 gui = new SpielfeldGUI(eimer, this, menueDaten);
		 logik = new SpielfeldLogik();
		 
		 flaeche = new WuerfelflaechePresenter(eimer);
		 bank = new WuerfelbankPresenter(eimer);
		 tabelle = new PunktetabellePresenter(eimer, menueDaten, spielLogik, this); 	
		 wuerfel = new WuerfelPresenter(eimer, spielLogik, flaeche.getGui(), bank.getGui(), tabelle, menueDaten);  			 	 
		 spieler = new SpielerbankPresenter(eimer, menueDaten.getSpielerListe(), spielLogik);
	
		 wuerfelnKlick = new WuerfelnButtonPresenter(eimer, wuerfel, tabelle);
		 punkteKlick = new PunkteEintragenButtonPresenter(eimer, this);

		 wuerfelnKlick.getGui().getKnopf().addActionListener(e->onWuerfelnClick());
		 punkteKlick.getGui().getKnopf().addActionListener(e->eintragePunkteFuerAktuellenSpieler());
	 
		 if (tabelle.getGui() != null) {
			 tabelle.getGui().setKategorieSelectListener(this::onKategorieAusgewaehlt);
		 }
		 		 
		 statusLabel = new JTextArea();
		 setupStatusLabel();
			
			
		 eimer.add(statusLabel);
		 eimer.add(flaeche.getGui().getPanel());
		 eimer.add(bank.getGui().getPanel());
		 eimer.add(tabelle.getGui().getPanel());
		 eimer.add(spieler.getGui().getPanel());
		 eimer.add(wuerfelnKlick.getGui().getPanel());
		 eimer.add(punkteKlick.getGui().getPanel());
		 eimer.add(gui.getPanel());
		 
		 rearrangiereAlles(eimer);
		 highlightAktuelleSpalte();
		
		
			eimer.revalidate();
			eimer.repaint();
	}
	 
	 private void setupStatusLabel() {
		 statusLabel.setVisible(true);
			statusLabel.setEditable(false);
			statusLabel.setOpaque(false);
			statusLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			updateStatusLabel();
		
	}

	public void onWuerfelnClick() {
	     tabelle.PunkteTabelle();
	     
	     int aktuellesMaxWuerfeln = wuerfel.getMaxWuerfe();
		 if(spielLogik.getWuerfeAnzahl() < aktuellesMaxWuerfeln && wuerfel.getWurfAnzahl() < aktuellesMaxWuerfeln) {
	     
			 if(wuerfel.isInit()) { wuerfel.setInit(false);
		     	for(int i = 0; i < 5; i++) { 
		     		bank.getGui().getReihenfolge().set(i, null); 
		     	}
			 }
			 
			 wuerfel.wuerfeln();

			 
			
		 }	 

			 spielLogik.erhoeheWurfAnzahl();
			 updateStatusLabel();
			 
			 List<Integer> wuerfelWerte = new ArrayList<>();
			 for (WuerfelLogik wuerfelLogik : wuerfel.getAlleWuerfelLogik()) {
				 wuerfelWerte.add(wuerfelLogik.getAugen());
			 }
			 System.out.println("Neue Würfelwerte: "+ wuerfelWerte);
			 
			 moeglicheZuege.setWuerfelWerte(wuerfelWerte);
			 Map<Kategorie,Integer> moeglichePunkte = moeglicheZuege.getAktiveMoeglichePunkte();
			 tabelle.getGui().zeigeMoeglichePunkte(moeglichePunkte, spielLogik.getAktiverSpielerIndex());
			 System.out.println("Wuerfelwerte nach Wurf: "+ wuerfelWerte);
		 }
	 
	 
	 public void onKategorieAusgewaehlt(Kategorie kategorie) {
		 this.ausgewaehlteKategorie = kategorie;
		 System.out.println("Kategorie Ausgewählt: "+kategorie);
	 }
	 
	 public void rearrangiereAlles(Component fenstar) {
		 rearrangiere(fenstar, flaeche.getGui());
		 rearrangiere(fenstar, tabelle.getGui());
		 rearrangiere(fenstar, spieler.getGui());		 
		 rearrangiere(fenstar, wuerfelnKlick.getGui());
		 rearrangiere(fenstar, punkteKlick.getGui());
		 rearrangiere(fenstar, gui);
		 rearrangiereWuerfel(fenstar, wuerfel, bank.getGui());		
		 statusLabel.setBounds((int)(eimer.getWidth() * 0.28), (int)(eimer.getHeight() * 0.81), (int)(eimer.getWidth() * 0.3),100);
	 }
	 	 
	 protected void rearrangiereWuerfel(Component e, WuerfelPresenter w, WuerfelBankGUI bank) {
			int breite = e.getWidth();
			int hoehe = e.getHeight();
						
			bank.getPanel().setBounds((int)Math.floor(breite*bank.getProzentFlaecheX()), 
									  (int)Math.floor(hoehe*bank.getProzentFlaecheY()), 300, 80);
			
			w.getAlleWuerfelGUI().forEach(t -> { 
				if(t.isIstinBank()) {						
					t.setxPos((int) ((bank.getProzentFlaecheX() * breite ) + 15 ) + 55 * (bank.getReihenfolge().indexOf(t)));
					t.setyPos((int) ((bank.getProzentFlaecheY() * hoehe ) + 7 ) );
				} else {
					t.setxPos( (int) Math.round(t.prozentXAufFeld * breite));
					t.setyPos( (int) Math.round(t.prozentYAufFeld * hoehe));												
					}
				t.setProzentXAufFeld(t.getxPos() * 1.0 / breite);
				t.setProzentYAufFeld(t.getyPos() * 1.0 / hoehe);
				t.getButton().setBounds(t.getxPos(), t.getyPos(), 50, 50);	
			});				
		}
		
		public void rearrangiere(Component e, AbstractGUI gui) {
			int breite = e.getWidth();
			int hoehe = e.getHeight();
			
			gui.getPanel().setBounds((int)Math.floor(breite*gui.getProzentFlaecheX()), 
									(int)Math.floor(hoehe*gui.getProzentFlaecheY()), 
									(int)Math.floor(breite*gui.getProzentFlaecheBreite()),
									(int)Math.floor(hoehe*gui.getProzentFlaecheHoehe()));
			
			if(!(gui instanceof PunkteTabelleGUI)){
			gui.setzeNeueGroesse((int)(breite * gui.getProzentFlaecheBreite()), (int)(hoehe * gui.getProzentFlaecheHoehe()));
			}
		}
		
		private void highlightAktuelleSpalte() {
			if(tabelle != null && tabelle.getGui() != null) {
				tabelle.getGui().highlightSpalte(spielLogik.getAktiverSpielerIndex());
			}
		}
	 

		private void updateStatusLabel() {
			String maxWurf = menueDaten.isDebugingModus() ? "inf" : String.valueOf(wuerfel.getMaxWuerfe());
			String status = "Runde " + spielLogik.getRunde() + 
					"\nSpieler: " + spielLogik.getAktiverSpieler().getName() + 
					"\nWürfe: " + wuerfel.getWurfAnzahl() + "/" + maxWurf;
			statusLabel.setText(status);
		}
		
		public void eintragePunkteFuerAktuellenSpieler() {
			 System.out.println("==PUNKTE EINTRAGEN==");
			 System.out.println("AusgewählteKategorie: " + ausgewaehlteKategorie);
			 System.out.println("Wuerfeanzahl: " + spielLogik.getWuerfeAnzahl());
			 System.out.println("Aktiver Spieler: " + spielLogik.getAktiverSpielerIndex());
			 
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
				 spieler.aktualisiereAktivesSpielerIcon(spielLogik.getAktiverSpielerIndex());
				 wuerfel.neueRunde();
				 ausgewaehlteKategorie = null;
				 updateStatusLabel();
				 highlightAktuelleSpalte();
				 tabelle.getGui().zeigeMoeglichePunkte(new HashMap<>(),spielLogik.getAktiverSpielerIndex());
				 System.out.println("Punkte erfolgreich eingetragen!");
				 
				 }catch (Exception e) {
					 System.out.println("Fehler beim Punkteeintragen: " + e.getMessage());
					 e.printStackTrace();
				 }
			 }else {
				 System.out.println("Kann keine Punkte eintragen -" + (ausgewaehlteKategorie == null ? "Keine Kategorie ausgewaehlt" : "noch nicht gewürfelt"));
			 }
		 }
		
	public void spielBeendet() {
		System.out.println("ich bin hier gelandet\n\n\n yay");
		eimer.removeAll();
		eimer.revalidate();
		eimer.repaint();
		new EndePresenter(eimer, spielLogik, menueDaten);
	}
		
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
