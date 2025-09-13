package presenter;

import java.awt.Container;

import gui.EndeGUI;
import model.EndeLogik;
import model.SpielLogik;
import model.StartmenueLogik;

public class EndePresenter {
	private EndeGUI gui;
	private EndeLogik logik;
	
	private SpielLogik spielLogik;
	private StartmenueLogik menueDaten;
	
	private Container eimer;
	
	public EndePresenter(Container eimer, SpielLogik spielLogik, StartmenueLogik menueDaten) {
		this.eimer = eimer;
		this.spielLogik = spielLogik;
		this.menueDaten = menueDaten;
		
		this.logik = new EndeLogik(spielLogik, menueDaten);
		this.gui = new EndeGUI(eimer, this);
		
		
	}

	public String getGewinner() {
		int gewinnerIndex = logik.getGewinner();
		return spielLogik.getAlleSpieler().get(gewinnerIndex).toString();
	}

	public int getGewinnerPunkte() {
		int gewinnerIndex = logik.getGewinner();
		return spielLogik.getAlleSpieler().get(gewinnerIndex).getPunktestand();
	}
	
}
