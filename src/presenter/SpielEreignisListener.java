package presenter;

import java.util.HashMap;
import java.util.List;

import model.WuerfelLogik;



public interface SpielEreignisListener {
	void wennWuerfelGeworfenWerden(List<Integer> wuerfel);
	
	void zeigeMoeglichePunkte(HashMap hashMap);
	
	void wennNeueRundeStartet(List<WuerfelLogik> alleWuerfelLogik);
}
