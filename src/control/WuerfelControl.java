package control;


import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import gui.WuerfelGUI;
import model.WuerfelLogik;

public class WuerfelControl  {
	private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
	private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
	private List<WuerfelGUI> Wuerfelbank = new ArrayList<>();
	private Container container;
	
	private List<int[]> freiePlaetze;
	
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
		
			container.add(view.getButton());
		}
		freiePlaetze = generiereRasterPlaetze(50,200, 65, 5, 3);
		Collections.shuffle(freiePlaetze);
		
		container.revalidate();
		container.repaint();	
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
				
				int[] platz = freiePlaetze.remove(0);
				gui.setPositionUndRefresh(platz[0], platz[1]);
			}
		}
		wurfAnzahl++;
	}
		
	
		
		
		
		//Position auf Wuerfelflaeche korrigieren wenn Würfel kollidieren gott was für ein satz...
		//int[] korrigiertePosition = korrigierteKollision(gui, zielX, zielY);
		//zielX = korrigiertePosition[0];
		//zielY = korrigiertePosition[1];
		
		//Po speicherun und Neupos
		//gui.setProzentXAufFeld(zielX * 1.0 / container.getWidth());
		//gui.setProzentYAufFeld(zielY * 1.0 / container.getHeight());		
		
		//gui.getLogik().wuerfelAngeklickt(gui, false);
		
	
	
	
	
	
	public void setBehalten(int index, boolean behalten) {
		if(index >= 0 && index < alleWuerfelLogik.size()) {
		alleWuerfelLogik.get(index).setBehalten(behalten);
		}
	}
	
	private List<int[]> generiereRasterPlaetze(int startX, int startY,
			int rasterGroesse, int spalten, int zeilen){
		List<int[]> allePlaetze = new ArrayList<>();
		for(int x = 0; x < spalten; x++) {
			for(int y = 0; y < zeilen; y++) {
				int posX = startX + x * rasterGroesse;
				int posY = startY + y * rasterGroesse;
				allePlaetze.add(new int[] {posX, posY});
			}
		}
		return allePlaetze;
	}
		
	public void neueRunde() {
		freiePlaetze = generiereRasterPlaetze(50, 200, 65, 5, 3);
		Collections.shuffle(freiePlaetze);
		wurfAnzahl = 0;
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
