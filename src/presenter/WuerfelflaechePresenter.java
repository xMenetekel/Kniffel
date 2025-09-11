package presenter;

import java.awt.Container;

import gui.WuerfelflaecheGUI;
import model.WuerfelflaecheLogik;

public class WuerfelflaechePresenter {
	private WuerfelflaecheGUI gui;
	private WuerfelflaecheLogik logik; //womöglich irrelevant lets see
	
	public WuerfelflaechePresenter(Container Eimer) {
		gui = new WuerfelflaecheGUI(Eimer);
		logik = new WuerfelflaecheLogik();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	//Better set better Getters
	
	public WuerfelflaecheGUI getGui() {
		return gui;
	}

	public void setGui(WuerfelflaecheGUI gui) {
		this.gui = gui;
	}

	public WuerfelflaecheLogik getLogik() {
		return logik;
	}

	public void setLogik(WuerfelflaecheLogik logik) {
		this.logik = logik;
	}
}
