package presenter;

import java.awt.Container;

import gui.WuerfelflaecheGUI;

public class WuerfelflaechePresenter {
	
	private WuerfelflaecheGUI gui;
	
	/**
	 * Erzeugt einen Presenter mit GUI und Logik
	 * 
	 * @param eimer Container der in GUI plaziert wird 
	 */
	public WuerfelflaechePresenter(Container eimer) {
		this.gui = new WuerfelflaecheGUI(eimer);
	}

	
	public WuerfelflaecheGUI getGui() {
		return gui;
	}

	public void setGui(WuerfelflaecheGUI gui) {
		this.gui = gui;
	}
}
