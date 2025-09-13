package presenter;

import java.util.HashMap;
import java.util.List;

import model.Kategorie;
import model.WuerfelLogik;



public interface SpielEreignisListener {
	void wennWuerfelGeworfenWerden(List<Integer> wuerfel);
	
	void zeigeMoeglichePunkte(HashMap <Kategorie,Integer>hashmap); 
	
	void wennNeueRundeStartet(List<WuerfelLogik> alleWuerfelLogik);
}
