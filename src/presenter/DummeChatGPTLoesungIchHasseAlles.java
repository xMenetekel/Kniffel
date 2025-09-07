package presenter;

import java.util.HashMap;
import java.util.List;

import model.WuerfelLogik;



public interface DummeChatGPTLoesungIchHasseAlles {
	void wasPassiertWennWirEinenWuerfelWerfen(List<Integer> wuerfel);
	
	void zeigeMoeglichePunkte(HashMap hashMap);
	void wasPassiertWennWirEineNeueRundeAusloesen(List<WuerfelLogik> alleWuerfelLogik);
}
