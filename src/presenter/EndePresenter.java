package presenter;

import java.awt.Container;

import javax.swing.JFrame;

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
		
		gui.getNeustart().addActionListener(klick ->{starteNeuesSpiel();});
		
		
	}
	private void starteNeuesSpiel() {
		eimer.removeAll();
		eimer.revalidate();
		eimer.repaint();
		new StartmenuePresenter((JFrame)eimer.getParent(), eimer);
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
