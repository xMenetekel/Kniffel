package presenter;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.EndeGUI;
import model.EndeLogik;
import model.SpielLogik;


public class EndePresenter {
	private EndeGUI gui;
	private EndeLogik logik;
	
	private SpielLogik spielLogik;
		
	private Container eimer;
	
	public EndePresenter(Container eimer, SpielLogik spielLogik) {
		this.eimer = eimer;
		this.spielLogik = spielLogik;
		
		
		this.logik = new EndeLogik(spielLogik);
		this.gui = new EndeGUI(eimer, this);
		
		gui.getNeustart().addActionListener(klick ->{starteNeuesSpiel();});
		
		
	}
	private void starteNeuesSpiel() {	
		JFrame neuesFenster = (JFrame) SwingUtilities.getWindowAncestor(eimer);
		Container neuerEimer = neuesFenster.getContentPane();
		neuerEimer.removeAll();
				
		new StartmenuePresenter(neuesFenster, neuerEimer);
		
		
	}

	public String getGewinner() {
		
		int gewinnerIndex = logik.getGewinner();
		return spielLogik.getAlleSpieler().get(gewinnerIndex).getName();
	}

	public int getGewinnerPunkte() {
		
		int gewinnerIndex = logik.getGewinner();
		return spielLogik.getAlleSpieler().get(gewinnerIndex).getPunktestand();
	}
	
}
