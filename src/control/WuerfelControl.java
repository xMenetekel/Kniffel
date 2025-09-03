package control;


import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import gui.WuerfelGUI;
import gui.WuerfelflaecheGUI;
import model.WuerfelLogik;
import model.WuerfelbankLogik;

public class WuerfelControl  {
	private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
	private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
	private WuerfelbankLogik bank;
	private Container container;
	private WuerfelflaecheGUI flaeche;
	
	int wurfAnzahl = 0;
	int maxWuerfe = 3;
	
	public WuerfelControl(Container Eimer, WuerfelflaecheGUI flaeche){
		
		this.container = Eimer;
		this.bank = new WuerfelbankLogik(alleWuerfelGUI);
		this.setFlaeche(flaeche);
		
		for(int i = 0; i < 5; i++) {		//das erstellt 5 würfel und fügt in jeder iteration den wuerfel den entsprechenden arrays hinzu
			WuerfelLogik model = new WuerfelLogik(6);
			WuerfelGUI view = new WuerfelGUI(model, i*55+80, 420, Eimer);
			model.setGui(view);
			view.getButton().addActionListener(klick -> { boolean neuerZustand = !model.istBehalten();										
														model.setBehalten(neuerZustand);
														hochRunterhochRunter(model);
														} );
			alleWuerfelGUI.add(view);
			alleWuerfelLogik.add(model);		
			container.add(view.getButton());
		}
		container.revalidate();
		container.repaint();	
	}
	
	public void wuerfeln() {
		if(wurfAnzahl >= maxWuerfe) { return; } 				
		getAlleWuerfelLogik().forEach(w -> {if (!w.istBehalten()) {w.wuerfeln(); } } );
		getAlleWuerfelGUI().forEach(w -> {if (!w.getLogik().istBehalten()) {
												int x, y;
												x = getValidesX(flaeche);
												y = getValidesY(flaeche);
												int[] xy = korrigierteKollision(w, x, y);
											  w.zeichneLinie(w.getButton(), xy[0], xy[1]);											  										  
											  }	});		
		wurfAnzahl++;
	}
	
	public int getValidesX(WuerfelflaecheGUI flaeche) {
		int validesX;
		int xVon = (int) (flaeche.getProzentFlaecheX() * this.container.getWidth());
		int xBis = (int) ((flaeche.getProzentFlaecheBreite() + flaeche.getProzentFlaecheX()) * this.container.getWidth());
		validesX = new Random().nextInt(xBis-xVon)+xVon-50;		
		
		
		return validesX;
	}
	public int getValidesY(WuerfelflaecheGUI flaeche) {
		int validesY;
		int YVon = (int) (flaeche.getProzentFlaecheY() * this.container.getHeight());
		int YBis = (int) ((flaeche.getProzentFlaecheHoehe() + flaeche.getProzentFlaecheY()) * this.container.getHeight());
		validesY = new Random().nextInt(YBis-YVon)+YVon;		
		
		
		return validesY;
	}
	/**
	 * Der ganze Spaß korrigiert Kollisionen. Es muss die WuerfelGUI übergeben werden, da dort die fertigen x/y pos gespeichert werden. der JButton an sich hat durch
	 * den Timer sehr unterschiedliche x/y Werte und dann wird die Kollisionsprüfung absoluter Mist. 
	 * KI viel befragt aber letztenendes hat die KI auch nicht die Lösung des Problems erkannt 
	 * 
	 * @param aktuellerWuerfel 
	 * @param zielX
	 * @param zielY
	 * @return
	 */
		private int[] korrigierteKollision(WuerfelGUI aktuellerWuerfel, int zielX, int zielY) {
			final int WUERFEL_GROESSE = 50;			
			boolean kollisionGefunden;
			do {
				kollisionGefunden = false;
				for(WuerfelGUI andererWuerfel : alleWuerfelGUI) {
					if(andererWuerfel == aktuellerWuerfel || andererWuerfel.getLogik().istBehalten()) {	continue; }				
					int andererX = andererWuerfel.getxPos(); //Es muss das von der gui sein, weil das die zielpositionen sind die auf kollisionen gecheckt werden omg
					int andererY = andererWuerfel.getyPos(); // this mf cost me 3 hours of my life						
					boolean kollisionX = (zielX < andererX + WUERFEL_GROESSE)   &&   (zielX + WUERFEL_GROESSE > andererX);
					boolean kollisionY = (zielY < andererY + WUERFEL_GROESSE)   &&   (zielY + WUERFEL_GROESSE > andererY);										
					if(kollisionX && kollisionY) {
						kollisionGefunden = true;
						zielX = getValidesX(flaeche);
						zielY = getValidesY(flaeche);
						break;
					}
				}					
		} while (kollisionGefunden);
			return new int[] {zielX, zielY};
		}
		


	private void hochRunterhochRunter(WuerfelLogik wuerfel) { 
		
		if(wuerfel.istBehalten()) {
			this.runter(wuerfel.getGui());
		
		} else {
			this.hoch(wuerfel.getGui());
		}
		
	}
	
	private void runter(WuerfelGUI gui) {
		// TODO Auto-generated method stub
		
	}

	private void hoch(WuerfelGUI gui) {
		gui.zeichneLinie(gui.getButton(), getValidesX(flaeche), getValidesY(flaeche));
		
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
		
	public void neueRunde() {		
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

	public WuerfelflaecheGUI getFlaeche() {
		return flaeche;
	}

	public void setFlaeche(WuerfelflaecheGUI flaeche) {
		this.flaeche = flaeche;
	}
}
