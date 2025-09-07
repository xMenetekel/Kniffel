package presenter;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.PunkteTabelleGUI;
import gui.WuerfelGUI;
import model.Kategorie;
import model.PunktetabelleLogik;
import model.SpielLogik;
import model.StartmenueLogik;
import model.WuerfelLogik;

public class PunktetabellePresenter implements DummeChatGPTLoesungIchHasseAlles{
	private PunkteTabelleGUI gui;
	private PunktetabelleLogik logik;
	private MoeglicheZuege moeglicheZuege;
	 private Kategorie ausgewaehlteKategorie = null; 
	

	int wurfAnzahl = 0;
	int maxWuerfe = 3;

	
	public PunktetabellePresenter(StartmenueLogik menueDaten) {
		gui = new PunkteTabelleGUI(menueDaten);
		logik = new PunktetabelleLogik();
		this.moeglicheZuege = new MoeglicheZuege();
		
	}

	
	
	
	public void aktualisierePunkteTabelle() {
        Map<Kategorie, Integer> moeglichePunkte = moeglicheZuege.getAktiveMoeglichePunkte();
        System.out.println("Mögliche Punkte: " + moeglichePunkte); // DEBUG
        if (gui != null) {
            gui.zeigeMoeglichePunkte(moeglichePunkte);
        }
    }
	 public void speichereWuerfelWerte(List<Integer> werte) {	        
	        moeglicheZuege.setWuerfelWerte(werte);
	        System.out.println("Würfelwerte gespeichert: " + werte); // DEBUG
	    }
	 /* public void neueRunde() {
		  	wurfAnzahl = 0; //warum muss das doppelt gesetzt werden? -anne
	        spielLogik.setWuerfeAnzahl(0); 
	        for (WuerfelLogik wuerfel : alleWuerfelLogik) {
	            wuerfel.setBehalten(false);
	            wuerfel.wuerfeln();
	        }
	      
	        moeglicheZuege.getAktiveMoeglichePunkte().clear();
	        if (gui != null) {
	        	gui.zeigeMoeglichePunkte(new HashMap<>());
	        }
	        
	        System.out.println("Neue Runde Gestartet");
	    }
	  
	  */
	
	
	
	
	
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
	public void wasPassiertWennWirEinenWuerfelWerfen(List<Integer> wuerfel) {
		speichereWuerfelWerte(wuerfel);
		aktualisierePunkteTabelle();
		
	}




	@Override
	public void wasPassiertWennWirEineNeueRundeAusloesen(List<WuerfelLogik> alleWuerfelLogik) {
		wurfAnzahl = 0; //warum muss das doppelt gesetzt werden? -anne
        for (WuerfelLogik wuerfel : alleWuerfelLogik) {
            wuerfel.setBehalten(false);
            wuerfel.wuerfeln();
        }
      
        moeglicheZuege.getAktiveMoeglichePunkte().clear();
        if (gui != null) {
        	gui.zeigeMoeglichePunkte(new HashMap<>());
        }
        
        System.out.println("Neue Runde Gestartet");
		
	}




	@Override
	public void zeigeMoeglichePunkte(HashMap hashMap) {
		// TODO Auto-generated method stub
		
	}
	
		
}
