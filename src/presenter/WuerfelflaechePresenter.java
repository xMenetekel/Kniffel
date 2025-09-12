package presenter;

import java.awt.Container;

import gui.WuerfelflaecheGUI;
import model.WuerfelflaecheLogik;

public class WuerfelflaechePresenter {
	
	private WuerfelflaecheGUI gui;
	private WuerfelflaecheLogik logik;
	
	/**
	 * Erzeugt einen Presenter mit GUI und Logik
	 * 
	 * @param eimer Container der in GUI plaziert wird 
	 */
	public WuerfelflaechePresenter(Container eimer) {
		this.gui = new WuerfelflaecheGUI(eimer);
		this.logik = new WuerfelflaecheLogik();
	}

	
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
