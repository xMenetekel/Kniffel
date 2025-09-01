package control;


import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import gui.WuerfelGUI;
import model.WuerfelLogik;
import model.WuerfelbankLogik;

public class WuerfelControl  {
	private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
	private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
	private WuerfelbankLogik bank;
	private Container container;
	
	int wurfAnzahl = 0;
	int maxWuerfe = 3;
	
	public WuerfelControl(Container Eimer){
		
		this.container = Eimer;
		this.bank = new WuerfelbankLogik(alleWuerfelGUI);
		
		for(int i = 0; i < 5; i++) {		//das erstellt 5 würfel und fügt in jeder iteration den wuerfel den entsprechenden arrays hinzu
			WuerfelLogik model = new WuerfelLogik(6);
			WuerfelGUI view = new WuerfelGUI(model, i*55+80, 420, Eimer);
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
											  //w.zeichneLinie(w.getButton(), null, null);
											  }	});		
		wurfAnzahl++;
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
}
