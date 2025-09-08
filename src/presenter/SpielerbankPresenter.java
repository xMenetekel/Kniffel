package presenter;

import java.awt.Container;

import javax.swing.JButton;

import gui.SpielerbankGUI;
import model.SpielerLogik;

public class SpielerbankPresenter {
	
	private SpielerbankGUI gui;
	private SpielerLogik logik;
	
	public SpielerbankPresenter(Container Eimer) {
		
		logik = new SpielerLogik();
		gui = new SpielerbankGUI(Eimer, this);
		
		
	}
	
	public String getBildname() {
		switch(logik.getIconIndex()) {
		case 0:
			return "bebi";					
		case 1:
			return "kiki";
		case 2:
			return "bubu";
		case 3:
			return "crystal";
		case 4:
			return "schrinke";
		case 5:
			return "transistor";
		case 6:
			return "integral";
		default: return "spielerbank";
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Ready Get Set Go
	
	public SpielerbankGUI getGui() {
		return gui;
	}

	public void setGui(SpielerbankGUI gui) {
		this.gui = gui;
	}

	public SpielerLogik getLogik() {
		return logik;
	}

	public void setLogik(SpielerLogik logik) {
		this.logik = logik;
	}
	
	
}
