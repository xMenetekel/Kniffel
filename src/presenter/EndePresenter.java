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
	
	private Container Eimer;
	
	public EndePresenter(Container Eimer, SpielLogik spielLogik, StartmenueLogik menueDaten) {
		this.Eimer = Eimer;
		this.spielLogik = spielLogik;
		this.menueDaten = menueDaten;
		
	}
}
