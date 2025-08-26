package control;


import java.util.ArrayList;
import java.util.List;

import gui.WuerfelGUI;
import model.WuerfelLogik;

public class WuerfelControl  {
	private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
	private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
	
	
	int wurfAnzahl = 0;
	int maxWuerfe = 3;
	
	public WuerfelControl(){
		
		for(int i = 0; i < 5; i++) {		//das erstellt 5 würfel und fügt in jeder iteration den wuerfel den entsprechenden arrays hinzu
		WuerfelLogik model = new WuerfelLogik(6);
		WuerfelGUI view = new WuerfelGUI(model, i*55, 350);
		alleWuerfelGUI.add(view);
		alleWuerfelLogik.add(model);
		}
		
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
			for(WuerfelLogik w : alleWuerfelLogik) {
				w.wuerfeln(); //wenn behalten bleibt
			}
			wurfAnzahl++;
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
	
	