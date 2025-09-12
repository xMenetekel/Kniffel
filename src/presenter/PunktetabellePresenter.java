package presenter;



import java.awt.Container;
import java.util.ArrayList;
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
	
	private PunkteTabelleGUI gui;
	private PunktetabelleLogik logik;
	private MoeglicheZuege moeglicheZuege;
	private SpielLogik spielLogik;
	int wurfAnzahl = 0;
	int maxWuerfe = 3;

	
	public PunktetabellePresenter(Container Eimer, StartmenueLogik menueDaten,SpielLogik spielLogik) {
		this.spielLogik = spielLogik;
		gui = new PunkteTabelleGUI(Eimer, menueDaten,spielLogik);
		logik = new PunktetabelleLogik();
		this.moeglicheZuege = new MoeglicheZuege();	
	}
	
	public void PunkteTabelle() {


        Map<Kategorie, Integer> moeglichePunkte = moeglicheZuege.getAktiveMoeglichePunkte();
        
        if(moeglichePunkte == null || moeglichePunkte.isEmpty()) {
        	System.out.println("Keine möglichen Punkte zum Anzeigen"); // DEBUG
        return;
        }
        System.out.println("Mögliche Punkte: " + moeglichePunkte); // DEBUG
        
        if (gui != null) {
            gui.zeigeMoeglichePunkte(moeglichePunkte, spielLogik.getAktiverSpielerIndex());
            System.out.println("Punkte aktualisiert "+moeglichePunkte);
        }
    }
	/* public void speichereWuerfelWerte(List<Integer> werte) {	        
	        moeglicheZuege.setWuerfelWerte(werte);
	        System.out.println("Würfelwerte gespeichert: " + werte); // DEBUG
	    }*/

		//Gettlern und Settlern
		public PunkteTabelleGUI getGui() {
			return gui;
		}

		public void setGui(PunkteTabelleGUI gui) {
			this.gui = gui;
		}

		public PunktetabelleLogik getLogik() {
			return logik;
		}

		public void setLogik(PunktetabelleLogik logik) {
			this.logik = logik;
		}
		
		
		@Override
		public void wennNeueRundeStartet(List<WuerfelLogik> alleWuerfelLogik) {
			
			if(gui != null) {
				gui.zeigeMoeglichePunkte(new HashMap<>(),spielLogik.getAktiverSpielerIndex());
			}
			System.out.println("Neue Runde in PunktetabellePresenter gestartet");
		}

		@Override
		public void wennWuerfelGeworfenWerden(List<Integer> wuerfel) {
			moeglicheZuege.setWuerfelWerte(wuerfel);
			PunkteTabelle();		
		}

		@Override
		public void zeigeMoeglichePunkte(HashMap hashMap) {
			// TODO Auto-generated method stub			
		}			
	}
	 

