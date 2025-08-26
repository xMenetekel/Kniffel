package control;

import java.util.ArrayList;
import java.util.List;

import model.WuerfelLogik;

public class WuerfelManagen {

	private List<WuerfelLogik> wuerfel;
	int wurfAnzahl = 0;
	int maxWuerfe = 3;
	
	public WuerfelManagen(List<WuerfelLogik> wuerfel) {
		this.wuerfel = new ArrayList<>(wuerfel); //Kopie 
	}

	//Getter und Setter Methoden generiert
	public List<WuerfelLogik> getWuerfel() {
		return wuerfel;
	}

	public void setWuerfel(List<WuerfelLogik> wuerfel) {
		this.wuerfel = wuerfel;
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
		for(WuerfelLogik w : wuerfel) {
			w.wuerfeln(); //wenn behalten bleibt
		}
		wurfAnzahl++;
	}
	
	public void setBehalten(int index, boolean behalten) {
		if(index >= 0 && index < wuerfel.size()) {
			wuerfel.get(index).setBehalten(behalten);
		}
	}
	
	public void neueRunde() {
		
	}
	

}
