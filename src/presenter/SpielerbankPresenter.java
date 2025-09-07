package presenter;

import java.awt.Container;

import gui.SpielerbankGUI;
import model.SpielerLogik;

public class SpielerbankPresenter {
	
	private SpielerbankGUI gui;
	private SpielerLogik logik;
	
	public SpielerbankPresenter(Container Eimer) {
		
		gui = new SpielerbankGUI(Eimer);
		logik = new SpielerLogik();
		
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
