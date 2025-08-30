package control;


import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gui.WuerfelGUI;
import model.WuerfelLogik;

public class WuerfelControl  {
	private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
	private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
	private List<WuerfelGUI> Wuerfelbank = new ArrayList<>();
	private Container container;
	
	
	int wurfAnzahl = 0;
	int maxWuerfe = 3;
	
	public WuerfelControl(Container Eimer){
		
		this.container = Eimer;
		
		for(int i = 0; i < 5; i++) {		//das erstellt 5 würfel und fügt in jeder iteration den wuerfel den entsprechenden arrays hinzu
		WuerfelLogik model = new WuerfelLogik(6);
		WuerfelGUI view = new WuerfelGUI(model, i*55+80, 420, Eimer);
		alleWuerfelGUI.add(view);
		Wuerfelbank.add(view);
		alleWuerfelLogik.add(model);
		}
	}
	
	public Container getContainer() {
		return container;
	}
	
	public int getWurfAnzahl() {
		return wurfAnzahl;
	}

	public void setWurfAnzahl(int wurfAnzahl) {
		this.wurfAnzahl = wurfAnzahl;
	}

	public int getMaxWuerfe() {
		return maxWuerfe;
	}

	public void setMaxWuerfe(int maxWuerfe) {
		this.maxWuerfe = maxWuerfe;
	}
	
	//Methoden 
	public void wuerfeln() {
		if(wurfAnzahl >= maxWuerfe) {
			return;
		}
		for(int i = 0; i < alleWuerfelLogik.size(); i++) {
			WuerfelLogik logik = alleWuerfelLogik.get(i);
			WuerfelGUI gui = alleWuerfelGUI.get(i);
			
			if(!logik.istBehalten()) {
				logik.wuerfeln();
				gui.fresherButton();
				
				bewegeWuerfelInFeld(gui);
			}
		}
		wurfAnzahl++;
	}
		
	private void bewegeWuerfelInFeld(WuerfelGUI gui) {
		int zielX = (new Random().nextInt(250))+50;
		int zielY = (new Random().nextInt(150))+200;
		
		//Position auf Wuerfelflaeche korrigieren wenn Würfel kollidieren gott was für ein satz...
		int[] korrigiertePosition = korrigierteKollision(gui, zielX, zielY);
		zielX = korrigiertePosition[0];
		zielY = korrigiertePosition[1];
		
		//Po speicherun und Neupos
		gui.setProzentXAufFeld(zielX * 1.0 / container.getWidth());
		gui.setProzentYAufFeld(zielY * 1.0 / container.getHeight());		
		
		gui.getLogik().wuerfelAngeklickt(gui, false);
	}
	
	
	//TODO WICHTIG muss noch umgeschrieben werden zu verschachtelt und macht
	// noch nicht was es soll!!!!!!!!!!!!
	private int[] korrigierteKollision(WuerfelGUI aktuellerWuerfel, int zielX, int zielY) {
		final int WUERFEL_GROESSE = 50;
		final int ABSTAND = 15;
		int korrekturVersuch = 0;
		final int MAX_KORREKTUREN = 10; //sonst haben wir wenns blöd läuft ne endlosschleife
		
		boolean kollisionGefunden;
		do {
			kollisionGefunden = false;
			
			for(WuerfelGUI andererWuerfel : alleWuerfelGUI) {
				if(andererWuerfel == aktuellerWuerfel || andererWuerfel.getLogik().istBehalten()) {
					continue;
				}
			
				int andererX = andererWuerfel.getButton().getX();
				int andererY = andererWuerfel.getButton().getY();
			
				//Kollisions Prüfung
				boolean kollisionX = zielX < andererX + WUERFEL_GROESSE + ABSTAND && 
						zielX + WUERFEL_GROESSE + ABSTAND > andererX;
				boolean kollisionY = zielY < andererY + WUERFEL_GROESSE + ABSTAND &&
						zielY+ WUERFEL_GROESSE + ABSTAND > andererY;
			
				if(kollisionX && kollisionY) {
					kollisionGefunden = true;
					korrekturVersuch++;
				
					if(zielX > andererX) {
						zielX = andererX + WUERFEL_GROESSE + ABSTAND; //nach rechts schieben
					}else {
						zielX = andererX - WUERFEL_GROESSE - ABSTAND; //nach links schieben
				
					}
			
					if(zielY > andererY) {
						zielY = andererY + WUERFEL_GROESSE + ABSTAND; //nach oben schieben
					}else {
						zielY = andererY - WUERFEL_GROESSE - ABSTAND; //nach unten schieben
					}
					// Begrenzung WuerfelFläche
					zielX = Math.max(50,  Math.min(300, zielX));
					zielY = Math.max(200,  Math.min(350, zielY));
			
					break; 
				}
				
			}
		} while (kollisionGefunden && korrekturVersuch < MAX_KORREKTUREN);
			
		return new int[] {zielX, zielY};
	}
	
	public void setBehalten(int index, boolean behalten) {
		if(index >= 0 && index < alleWuerfelLogik.size()) {
		alleWuerfelLogik.get(index).setBehalten(behalten);
		}
	}
		
	public void neueRunde() {
			
	}
	
	public List<WuerfelGUI> getAlleWuerfelGUI() {
		return alleWuerfelGUI;
	}
	
	public void setAlleWuerfelGUI(List<WuerfelGUI> alleWuerfelGUI) {
		this.alleWuerfelGUI = alleWuerfelGUI;
	}
	
	public List<WuerfelLogik> getAlleWuerfelLogik() {
		return alleWuerfelLogik;
	}
	
	public void setAlleWuerfelLogik(List<WuerfelLogik> alleWuerfelLogik) {
		this.alleWuerfelLogik = alleWuerfelLogik;
	}
}
